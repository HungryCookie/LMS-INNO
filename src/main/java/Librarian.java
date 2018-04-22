import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.print.Doc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Random;
import java.util.Date;
import java.lang.Object;



import java.text.SimpleDateFormat;

import static java.lang.Math.min;

public abstract class Librarian extends Users {
    protected FcukBase base = new FcukBase();

    public Librarian(){}

    public Librarian(int userID){

        ResultSet res = base.getUserByID(userID);
        try {
            // if usedID is not correct then return null
            /*if (!res.next()) {
                return;
            }*/
            this.userID = new SimpleIntegerProperty(userID);
            // else get result by userID and set all the fields
            name = new SimpleStringProperty(res.getString("name"));
            address = res.getString("address");
            password = res.getString("password");
            phoneNumber = res.getString("phoneNumber");
            status = "Librarian";

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Patron getPatronInfo(int userID){

        if (!base.checkUserID(userID))
            return null;

        Patron p = new Patron(userID);

        return p;
    }



    protected void deleteBooking(int userID){

        Patron p = new Patron(userID);

        Documents [] d = p.bookedDocuments();

        for (Documents cur : d) {
            base.deleteBooking(cur.getDocID(), userID);
        }
    }



    public void outstandingRequest(Documents doc) throws SQLException {  //Returns 0 if there isn't any free books for librarian except reference, librarian was added to queue
        //Returns 1 if book is availible
        doc = new Documents(doc.getDocID());

        ResultSet res = base.getQueue(doc.getDocID());

        while (res.next())
            base.addNotification(res.getInt("userID"), doc.getDocID(), "G");

        res = base.checkedOutByDocID(doc.getDocID());

        while (res.next()){
            base.addNotification(res.getInt("userID"), doc.getDocID(), "X");
            base.changeDateToReturn(res.getInt("userID"), doc.getDocID(), getDate());
            base.changeRenew(res.getInt("copyID"), res.getInt("userID"), "T");
        }

        base.clearQueue(doc.getDocID());
    }

    public void outstandingRequestTest(Documents doc, String date) throws SQLException {

        doc = new Documents(doc.getDocID());

        ResultSet res = base.getQueue(doc.getDocID());

        while (res.next())
            base.addNotification(res.getInt("userID"), doc.getDocID(), "G");

        res = base.checkedOutByDocID(doc.getDocID());

        while (res.next()){
            base.addNotification(res.getInt("userID"), doc.getDocID(), "X");
            base.changeDateToReturn(res.getInt("userID"), doc.getDocID(), date);
            base.changeRenew(res.getInt("copyID"), res.getInt("userID"), "T");
        }


        base.clearQueue(doc.getDocID());
    }

    public int checkOut(Documents doc) throws SQLException {
        //return 0 if librarian was added to waiting list
        // return 1 if ok
        //return 2;
        
        doc = new Documents(doc.getDocID());

        if (doc.isReference()){
            base.book(doc.getDocID(), userID.get(), 1, getDate());
            return 0;
        }
        
        if (doc.getType().equals("Magazine"))
            return 2;

        int [] copies = base.findCopyID(doc.getDocID());

        base.checkOut(userID.get(), copies[0], "1999-03-09");
        base.counterDown(doc.getDocID());

        return 1;
    }

    public String getDate(){
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        return f.format(d);
    }




    public ResultSet checkedOut(int userID){ //Method returns a list of checked out Documents with number of copy he took of user with userID
        if (!base.checkUserID(userID))
            return null;

        return base.checkedOutByUserID(userID);
    }

    public ResultSet copiesOfDocument(int docID){

        if (!base.checkDocumentByID(docID))
            return null;

        return base.copiesOfDocument(docID);
    }

    public void deleteCopy(int copyID){
        base.deleteCopy(copyID);
    }


    protected void notifyUser(int userID, int docID){//need to nodify user

        base.setDateToCheckOut(docID, userID, getDate());
        base.addNotification(userID, docID, "T");
    }

    protected void notifyUserTest(int userID, int docID, String dateS){//need to nodify user

        base.setDateToCheckOut(docID, userID, dateS);
        base.addNotification(userID, docID, "T");
    }

    public int payFine(int userID, int cash) throws SQLException {

        if (!base.checkUserID(userID))
            return -1000000;

        Patron p = new Patron(userID);
        
        int t = min(cash, p.checkFine());
        
        base.increaseFine(userID, -t);

        return cash - t;
    }

    protected String getLocation(int copyID){

        int room = (copyID - 1) % 64 + 1;

        copyID -= 64 * (room - 1);

        int bookcase = (copyID - 1) % 32 + 1;

        copyID -= 32 * (bookcase - 1);

        int bookshelf = (copyID - 1) % 8 + 1;

        copyID -= 8 * (bookshelf - 1);

        int place = copyID;

        String ans = "in room №" + String.valueOf(room) + ", bookcase №" + String.valueOf(bookcase) + ", bookshelf №" + String.valueOf(bookshelf) + ", document №" + String.valueOf(place);

        return ans;
    }

    public String[] whereIsCopies(Documents doc) throws SQLException {

        ResultSet res = base.copiesOfDocument(doc.getDocID());

        String [] st = new String[100000];
        int n = 0;

        while(res.next()){
            if (res.getString("availability").equals("T")){
                st[n] = getLocation(res.getInt("copyID"));
            }
            else{
                st[n] = String.valueOf(res.getInt("userID"));
            }
            n++;
        }

        String s[] = new String[n];

        for (int i = 0; i < n; i++)
            s[i] = st[i];

        return s;
    }

    int calculateFine(int userID, int docID, String dateS){

        int gone = 0;
        Documents doc = new Documents(docID);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();


        try {
            date = formatter.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date todayDate = new Date();

        if (!todayDate.after(date))
            return 0;

        while(todayDate.after(date)){
            gone++;

            Calendar c = Calendar.getInstance();        // Checking if booking is old
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
        }

        return min(doc.getCost(), 100 * gone);
    }

    IntAndInt calculateFineTest(int userID, int docID, String dateS, String dateE){

        int gone = 0;
        Documents doc = new Documents(docID);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();


        try {
            date = formatter.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date todayDate = new Date();

        try {
            todayDate = formatter.parse(dateE);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!todayDate.after(date))
            return new IntAndInt(0, 0);

        while(todayDate.after(date)){
            gone++;

            Calendar c = Calendar.getInstance();        // Checking if booking is old
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
        }

        return new IntAndInt(min(doc.getCost(), 100 * gone), gone);
    }

    protected void deleteOldBookings(Documents document) throws SQLException {

        ResultSet res = base.getQueue(document.getDocID());

        while (res.next()){
            if (res.getInt("priority") == 2) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                Date date = new Date();

                try {
                    date = formatter.parse(res.getString("date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar c = Calendar.getInstance();        // Checking if booking is old
                c.setTime(date);
                c.add(Calendar.DATE, 1);
                date = c.getTime();


                Date todayDate = new Date();

                if (todayDate.after(date)) {
                    base.deleteBooking(document.getDocID(), res.getInt("userID"));   //Deleting old booking
                    base.deleteNotification(res.getInt("userID"), document.getDocID());
                    base.addNotification(res.getInt("userID"), res.getInt("docID"), "F");

                    ResultSet queue = base.getQueue(document.getDocID());

                    if (queue.next())
                        notifyUser(queue.getInt(userID.get()), document.getDocID());
                }
            }
        }
    }

    protected void deleteOldBookingsTest(Documents document, String dateS) throws SQLException {

        ResultSet res = base.getQueue(document.getDocID());

        while (res.next()){
            if (res.getInt("priority") == 2) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                Date date = new Date();

                try {
                    date = formatter.parse(res.getString("date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar c = Calendar.getInstance();        // Checking if booking is old
                c.setTime(date);
                c.add(Calendar.DATE, 1);
                date = c.getTime();


                Date todayDate = new Date();
                try {
                    todayDate = formatter.parse(dateS);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if (todayDate.after(date)) {
                    base.deleteBooking(document.getDocID(), res.getInt("userID"));   //Deleting old booking
                    base.deleteNotification(res.getInt("userID"), document.getDocID());
                    base.addNotification(res.getInt("userID"), res.getInt("docID"), "F");

                    ResultSet queue = base.getQueue(document.getDocID());

                    if (queue.next())
                        notifyUserTest(queue.getInt(userID.get()), document.getDocID(), dateS);
                }
            }
        }
    }

    public boolean returnDoc(int copyID) throws SQLException {  //Method returns document to library by ID of copy. True if alright, false if it is wrong

        ResultSet r = base.copyInfo(copyID);

        int res = base.returnDoc(copyID);

        if (res == 0)
            return false;


        base.counterUp(res, 1);

        int userID = r.getInt("userID");

        if (!r.getString("date").equals("1999-03-09"))
            base.increaseFine(r.getInt("userID"), calculateFine(r.getInt("userID"), r.getInt("commonID"), r.getString("date")));

        r = base.copyInfo(copyID);

        int docID = r.getInt("commonID");

        Documents [] n = (new Patron(userID)).getHaveToReturnNotifications();
        for (Documents nn : n){
            if (nn.getDocID() == docID)
                base.deleteNotification(userID, docID);
        }


        deleteOldBookings(new Documents(docID));
        ResultSet queue = base.getQueue(docID);

        if (queue.next()) {
            //System.out.println(queue.getInt("userID") + " " + docID);

            notifyUser(queue.getInt("userID"), docID);
        }
        return true;
    }

    public boolean returnDocTest(int copyID, String dateS) throws SQLException {  //Method returns document to library by ID of copy. True if alright, false if it is wrong

        ResultSet r = base.copyInfo(copyID);

        int res = base.returnDoc(copyID);

        if (res == 0)
            return false;


        base.counterUp(res, 1);

        if (!r.getString("date").equals("1999-03-09"))
            base.increaseFine(r.getInt("userID"), calculateFineTest(r.getInt("userID"), r.getInt("commonID"), r.getString("date"), dateS).getFirst());

        r = base.copyInfo(copyID);

        int docID = r.getInt("commonID");

        deleteOldBookingsTest(new Documents(docID), dateS);
        ResultSet queue = base.getQueue(docID);

        if (queue.next()) {
            //System.out.println(queue.getInt("userID") + " " + docID);

            notifyUserTest(queue.getInt("userID"), docID, dateS);
        }
        return true;
    }
    /*
    public boolean deleteDocument(int copyID){ //Method deletes one copy of document from data base. Ant returns false if copyID is wrong or this copy is not in library at the moment
        return base.deleteDocument
    }*/




    public void clearDB() throws Exception {
        base.clear();
    }

    public int [] getWaitingList(int docID) throws SQLException {
        ResultSet res = base.getQueue(docID);

        int [] a = new int[1000000];
        int n = 0;

        while(res.next()){
            a[n] = res.getInt("userID");
            n++;
        }

        int [] ans = new int[n];
        for (int i = 0; i < n; i++)
            ans[i] = a[i];

        return ans;
    }

    /*public static void main(String[] args) throws SQLException {

        Librarian l = new Librarian(2);

        Patron p = new Patron(1);

        Documents d = new Documents(2);

        //l.checkOut(p.getID(), d);

        ResultSet s = l.checkedOut(p.getID());


        while (s.next()){
            System.out.println(s.getInt("copyID"));
            System.out.println(s.getString("name"));
        }
    }*/
}
