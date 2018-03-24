import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Patron extends Users{
    private int currentFine;
    private String status;
    private FcukBase base = new FcukBase();


    /*public static void main(String[] args) {
        Patron patron = new Patron(2);
        System.out.println(patron.name);
        //patron.bookADocument(new Documents(1));
        for (Documents doc : patron.bookedDocuments()) {
            System.out.print(doc.getName() + " ");
        }
    }*/

    public Patron() {
        /*Renew Fine for current user*/
    }

    public Patron(int userID){
        if (!base.checkUserID(userID)) {
            return;
        } else {
            ResultSet res = base.getUserByID(userID);
            try {
                // if usedID is not correct then return null
            /*if (!res.next()) {
                return;
            }*/
                this.userID = userID;
                // else get result by userID and set all the fields
                name = res.getString("name");
                status = res.getString("status");
                address = res.getString("address");
                password = res.getString("password");
                phoneNumber = res.getString("phoneNumber");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int bookADocument(Documents document){
        if (!document.chechName())
            return 0;

        /*if (document.getType() == "AV") {
            base.bookAV(document.getDocID(), userID);
            return 4;
        }
        // if user with id = userID already booked document then return false
        ArrayList<Integer> arr = base.findBookedDocuments(userID);
        for (Integer i : arr) {
            if (i == document.getDocID()) {
                return 0;
            }
        }
        // else put in data base a new note that user with id = userID is
        // going to get document with id = document.docID

        if (document.isReference() ||  !base.bookADocument(document.getDocID(), userID))
            return 0;*/

        if (document.isBestseller())
            return 2;

        if (status.equals("Student"))
            return 3;

        return 4;
    }

    private int getPriority(){

        if (status == "Student")
            return 3;

        if (status == "Instructor")
            return 4;

        if (status == "Visiting Professor")
            return 5;

        if (status == "Professor")
            return 6;

        return 3;
    }

    private void deleteOldBookings(Documents document) throws SQLException {

        ResultSet res = base.getQueue(document.getDocID());

        while (res.next()){
            if (res.getInt("priority") == 2) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                Date date = new Date();

                try {
                    date = formatter.parse(res.getString("deleteBookingDate"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar c = Calendar.getInstance();        // Checking if booking is old
                c.setTime(date);
                c.add(Calendar.DATE, 1);
                date = c.getTime();


                Date todayDate = new Date();

                if (todayDate.after(date))
                    base.deleteBooking(document.getDocID(), userID);   //Deleting old booking
            }
        }
    }

    public int checkOut(Documents document) throws SQLException {  //returns 0 if user can't take this document, user was added to queue
                                                                     //returns 1 if document is already checkedOut by user
                                                                    //returns 2 if user is already in a queue at the moment
                                                                    //returns 3 if user can checkOut book
                                                                    //returns 4 if user already booked this document and now he can take it
       document = new Documents(document.getDocID());

        deleteOldBookings(document);

        ResultSet res = base.checkedOutByUserID(userID);

        while (res.next()){
            ResultSet res1 = base.copyInfo(res.getInt("copyID"));

            if (res1.getInt("сommonID") == document.getDocID())
                return 1;
        }

        res = base.getQueue(document.getDocID());

        int current_counter = document.getCounter();

        while (res.next()){
            if (res.getInt("userID") == userID) {

                if (current_counter > 1){

                    int [] copies = base.findCopyID(document.getDocID());

                    base.checkOut(userID, copies[0], getDate());
                    base.deleteBooking(document.getDocID(), userID);
                    base.counterDown(document.getDocID());

                    return 4;
                }

                return 2;
            }

            current_counter--;
        }

        base.book(document.getDocID(), userID, getPriority(), getDate());

        res = base.getQueue(document.getDocID());

        current_counter = document.getCounter();

        while (res.next()){
            if (res.getInt("userID") == userID)
                break;

            current_counter--;
        }

        if (current_counter < 2){
            return 0;
        }

        int [] copies = base.findCopyID(document.getDocID());

        base.checkOut(userID, copies[0], getDate());
        base.deleteBooking(document.getDocID(), userID);
        base.counterDown(document.getDocID());

        return 3;
    }

    public String getDate(){
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        return f.format(d);
    }

    public Documents[] bookedDocuments(){

        // get array of docIDs
        ArrayList<Integer> arr = base.findBookedDocuments(userID);
        //System.out.println("+++++" + arr.size());
        Documents[] res = new Documents[arr.size()];

        // create Documents array
        // use docIDs and setter in Documents class to get final arrat Documents and return it
        for (int i = 0; i < arr.size(); i++) {
            res[i] = new Documents(arr.get(i));
        }
        return res;
    }

    public void bookOrder(int bookID) {
        /* Note that you ordered a book in database
        * */
    }

    public void bookOrder(String name) {
        /*Find bookID by name
        * Use method bookOrder(ID) to order a book by ID*/
    }

    public int checkFine() {
        return currentFine;
    }

    public String getStatus(){
        return status;
    }
}
