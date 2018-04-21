import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;

public class Patron extends Users{
    private int currentFine;
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
                this.userID = new SimpleIntegerProperty(userID);
                // else get result by userID and set all the fields
                name = new SimpleStringProperty(res.getString("name"));
                status = res.getString("status");
                address = res.getString("address");
                password = res.getString("password");
                phoneNumber = res.getString("phoneNumber");
                status = res.getString("status");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void init(){
        ResultSet res = base.getUserByID(userID.get());
        try {
            // if usedID is not correct then return null
            /*if (!res.next()) {
                return;
            }*/
            // else get result by userID and set all the fields
            name = new SimpleStringProperty(res.getString("name"));
            status = res.getString("status");
            address = res.getString("address");
            password = res.getString("password");
            phoneNumber = res.getString("phoneNumber");
            status = res.getString("status");

        } catch (SQLException e) {
            e.printStackTrace();
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

    private void notifyUser(int userID, int docID){             //need to modify user
        base.setDateToCheckOut(docID, userID, getDate());
        base.addNotification(docID, userID, "T");
    }

    private void notifyUserTest(int userID, int docID, String dateS){//need to nodify user

        base.setDateToCheckOut(docID, userID, dateS);
        base.addNotification(userID, docID, "T");
    }

    public Documents [] getNotifications() throws SQLException {  //Notifications about client's documents available for checking out

        ResultSet res = base.getUserNotification(userID.get());
        Documents [] anse = new Documents[1000000];
        int n = 0;

        while (res.next()){
            int docID = res.getInt("docID");

            deleteOldBookings(new Documents(docID));

            ResultSet res1 = base.getUserNotification(userID.get());
            boolean c = false;

            while (res1.next()){
                if (res1.getInt("docID") == docID && res1.getString("available").equals("T"))
                    c = true;
            }

            if (c){
                anse[n] = new Documents(docID);
                n++;
                base.deleteNotification(userID.get(), docID);
            }
        }

        Documents [] ans = new Documents[n];
        for (int i = 0; i < n; i++)
            ans[i] = anse[i];

        return ans;
    }

    public Documents [] getNotificationsTest(String dateS) throws SQLException {  //Notifications about client's documents available for checking out

        ResultSet res = base.getUserNotification(userID.get());
        Documents [] anse = new Documents[1000000];
        int n = 0;

        while (res.next()){
            int docID = res.getInt("docID");

            deleteOldBookingsTest(new Documents(docID), dateS);

            ResultSet res1 = base.getUserNotification(userID.get());
            boolean c = false;

            while (res1.next()){
                if (res1.getInt("docID") == docID && res1.getString("available").equals("T"))
                    c = true;
            }

            if (c){
                anse[n] = new Documents(docID);
                n++;
                base.deleteNotification(userID.get(), docID);
            }
        }

        Documents [] ans = new Documents[n];
        for (int i = 0; i < n; i++)
            ans[i] = anse[i];

        return ans;
    }

    public Documents [] getBadNotifications() throws SQLException {  //Notifications, user was in waiting list, and he is removed

        ResultSet res = base.getUserNotification(userID.get());
        Documents [] anse = new Documents[1000000];
        int n = 0;


        while (res.next()){
            int docID = res.getInt("docID");

            deleteOldBookings(new Documents(docID));

            ResultSet res1 = base.getUserNotification(userID.get());
            boolean c = false;

            while (res1.next()){
                if (res1.getInt("docID") == docID && res1.getString("available").equals("F"))
                    c = true;
            }

            if (c){
                anse[n] = new Documents(docID);
                n++;
                base.deleteNotification(userID.get(), docID);
            }
        }

        Documents [] ans = new Documents[n];
        for (int i = 0; i < n; i++)
            ans[i] = anse[i];

        return ans;
    }

    public Documents [] getBadNotificationsTest(String dateS) throws SQLException {  //Notifications, user was in waiting list, and he is removed

        ResultSet res = base.getUserNotification(userID.get());
        Documents [] anse = new Documents[1000000];
        int n = 0;


        while (res.next()){
            int docID = res.getInt("docID");

            deleteOldBookingsTest(new Documents(docID), dateS);

            ResultSet res1 = base.getUserNotification(userID.get());
            boolean c = false;

            while (res1.next()){
                if (res1.getInt("docID") == docID && res1.getString("available").equals("F"))
                    c = true;
            }

            if (c){
                anse[n] = new Documents(docID);
                n++;
                base.deleteNotification(userID.get(), docID);
            }
        }

        Documents [] ans = new Documents[n];
        for (int i = 0; i < n; i++)
            ans[i] = anse[i];

        return ans;
    }

    public Documents [] getFailedBookingNotifications() throws SQLException { //Notification that user was removed from waiting list by librarian's outstanding request

        ResultSet res = base.getUserNotification(userID.get());
        Documents [] anse = new Documents[1000000];
        int n = 0;


        while (res.next()){
            int docID = res.getInt("docID");


            deleteOldBookings(new Documents(docID));

            ResultSet res1 = base.getUserNotification(userID.get());
            boolean c = false;

            while (res1.next()){
                if (res1.getInt("docID") == docID && res1.getString("available").equals("G"))
                    c = true;
            }

            if (c){
                anse[n] = new Documents(docID);
                n++;
                base.deleteNotification(userID.get(), docID);
            }
        }

        Documents [] ans = new Documents[n];
        for (int i = 0; i < n; i++)
            ans[i] = anse[i];

        return ans;
    }

    public Documents [] getFailedBookingNotificationsTest(String dateS) throws SQLException { //Notification that user was removed from waiting list by librarian's outstanding request

        ResultSet res = base.getUserNotification(userID.get());
        Documents [] anse = new Documents[1000000];
        int n = 0;


        while (res.next()){
            int docID = res.getInt("docID");


            deleteOldBookingsTest(new Documents(docID), dateS);

            ResultSet res1 = base.getUserNotification(userID.get());
            boolean c = false;

            while (res1.next()){
                if (res1.getInt("docID") == docID && res1.getString("available").equals("G"))
                    c = true;
            }

            if (c){
                anse[n] = new Documents(docID);
                n++;
                base.deleteNotification(userID.get(), docID);
            }
        }

        Documents [] ans = new Documents[n];
        for (int i = 0; i < n; i++)
            ans[i] = anse[i];

        return ans;
    }

    public Documents [] getHaveToReturnNotifications() throws SQLException {  //Notification that user have to return book into library since librarian sent outstanding request

        ResultSet res = base.getUserNotification(userID.get());
        Documents [] anse = new Documents[1000000];
        int n = 0;


        while (res.next()){
            int docID = res.getInt("docID");

            deleteOldBookings(new Documents(docID));

            ResultSet res1 = base.getUserNotification(userID.get());
            boolean c = false;

            while (res1.next()){
                if (res1.getInt("docID") == docID && res1.getString("available").equals("X"))
                    c = true;
            }

            if (c){
                anse[n] = new Documents(docID);
                n++;
            }
        }

        Documents [] ans = new Documents[n];
        for (int i = 0; i < n; i++)
            ans[i] = anse[i];

        return ans;
    }

    public Documents [] getHaveToReturnNotificationsTest(String dateS) throws SQLException {  //Notification that user have to return book into library since librarian sent outstanding request

        ResultSet res = base.getUserNotification(userID.get());
        Documents [] anse = new Documents[1000000];
        int n = 0;


        while (res.next()){
            int docID = res.getInt("docID");

            deleteOldBookingsTest(new Documents(docID), dateS);

            ResultSet res1 = base.getUserNotification(userID.get());
            boolean c = false;

            while (res1.next()){
                if (res1.getInt("docID") == docID && res1.getString("available").equals("X"))
                    c = true;
            }

            if (c){
                anse[n] = new Documents(docID);
                n++;
            }
        }

        Documents [] ans = new Documents[n];
        for (int i = 0; i < n; i++)
            ans[i] = anse[i];

        return ans;
    }

    private void deleteOldBookings(Documents document) throws SQLException {

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

    private void deleteOldBookingsTest(Documents document, String dateS) throws SQLException {

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

    public String getDateToReturn(Documents document){

        int d = 7;

        Documents doc = new Documents(document.getDocID());

        if (!getStatus().equals("Visiting Professor")) {
            d += 7;

            if (getStatus().equals("Professor"))
                d += 14;
            else {
                if (!doc.getType().equals("AV") && !doc.getType().equals("Journal") && !doc.isBestseller()) {

                    if (getStatus().equals("Instructor"))
                        d += 14;
                    else
                        d += 7;
                }
            }
        }


        Date date = new Date();

        while(d > 0){
            d--;

            Calendar c = Calendar.getInstance();        // Checking if booking is old
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
        }

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        return f.format(date);
    }

    public String getDateToReturnTest(Documents document, String dateS){

        int d = 7;

        Documents doc = new Documents(document.getDocID());

        if (!getStatus().equals("Visiting Professor")) {
            d += 7;

            if (getStatus().equals("Professor"))
                d += 14;
            else {
                if (!doc.getType().equals("AV") && !doc.getType().equals("Journal") && !doc.isBestseller()) {

                    if (getStatus().equals("Instructor"))
                        d += 14;
                    else
                        d += 7;
                }
            }
        }


        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


        try {
            date = formatter.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        while(d > 0){
            d--;

            Calendar c = Calendar.getInstance();        // Checking if booking is old
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
        }

        return f.format(date);
    }

    public IntAndString checkOutTest(Documents document, String dateS) throws SQLException {  //returns 0 if user can't take this document, user was added to queue
        //returns 1 if document is already checked out by user
        //returns 2 if user is already in a queue at the moment
        //returns 3 if user can checkOut book
        //returns 4 if user already booked this document and now he can take it
        //return 5 if user has a fine
        init();

        String date = "";

        if (checkFine() > 0)
            return new IntAndString(5, date);

        document = new Documents(document.getDocID());

        deleteOldBookingsTest(document, dateS);

        ResultSet res = base.checkedOutByUserID(userID.get());

        while (res.next()){
            ResultSet res1 = base.copyInfo(res.getInt("copyID"));

            if (res1.getInt("commonID") == document.getDocID())
                return new IntAndString(1, date);
        }

        res = base.getQueue(document.getDocID());

        int current_counter = document.getCounter();

        while (res.next()){
            if (res.getInt("userID") == userID.get()) {

                if (current_counter > 1){

                    int [] copies = base.findCopyID(document.getDocID());

                    base.checkOut(userID.get(), copies[0], getDateToReturnTest(document, dateS));
                    base.deleteBooking(document.getDocID(), userID.get());
                    base.counterDown(document.getDocID());

                    return new IntAndString(4, getDateToReturnTest(document, dateS));
                }

                return new IntAndString(2, date);
            }

            current_counter--;
        }

        base.book(document.getDocID(), userID.get(), getPriority(), dateS);

        res = base.getQueue(document.getDocID());

        current_counter = document.getCounter();

        while (res.next()){
            if (res.getInt("userID") == userID.get())
                break;

            current_counter--;
        }
        if (current_counter < 2){
            return new IntAndString(0, date);
        }

        int [] copies = base.findCopyID(document.getDocID());

        base.checkOut(userID.get(), copies[0], getDateToReturnTest(document, dateS));
        base.deleteBooking(document.getDocID(), userID.get());
        base.counterDown(document.getDocID());

        return new IntAndString(3, getDateToReturnTest(document, dateS));
    }

    public IntAndString checkOut(Documents document) throws SQLException {  //returns 0 if user can't take this document, user was added to queue
        //returns 1 if document is already checked out by user
        //returns 2 if user is already in a queue at the moment
        //returns 3 if user can checkOut book
        //returns 4 if user already booked this document and now he can take it
        //return 5 if user has a fine
        init();

        String date = "";

        if (checkFine() > 0)
            return new IntAndString(5, date);

        document = new Documents(document.getDocID());

        deleteOldBookings(document);

        ResultSet res = base.checkedOutByUserID(userID.get());

        while (res.next()){
            ResultSet res1 = base.copyInfo(res.getInt("copyID"));

            if (res1.getInt("commonID") == document.getDocID())
                return new IntAndString(1, date);
        }

        res = base.getQueue(document.getDocID());

        int current_counter = document.getCounter();

        while (res.next()){
            if (res.getInt("userID") == userID.get()) {

                if (current_counter > 1){

                    int [] copies = base.findCopyID(document.getDocID());

                    base.checkOut(userID.get(), copies[0], getDateToReturn(document));
                    base.deleteBooking(document.getDocID(), userID.get());
                    base.counterDown(document.getDocID());

                    return new IntAndString(4, getDateToReturn(document));
                }

                return new IntAndString(2, date);
            }

            current_counter--;
        }

        base.book(document.getDocID(), userID.get(), getPriority(), getDate());

        res = base.getQueue(document.getDocID());

        current_counter = document.getCounter();

        while (res.next()){
            if (res.getInt("userID") == userID.get())
                break;

            current_counter--;
        }

        if (current_counter < 2){
            return new IntAndString(0, date);
        }

        int [] copies = base.findCopyID(document.getDocID());

        base.checkOut(userID.get(), copies[0], getDateToReturn(document));
        base.deleteBooking(document.getDocID(), userID.get());
        base.counterDown(document.getDocID());

        return new IntAndString(3, getDateToReturn(document));
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

    public IntAndString renew(Documents document) throws SQLException {//return 0 if this user didn't check out this document
        //return 1 if user have to return document to library, he reached a fine and can't renew this document
        //return 2 if successful
        //return 3 if this user already renewed this document

        init();

        String ans = "";

        ResultSet res = checkedOut(userID.get());

        boolean t = false;
        int copyID = 0;
        String date = "";

        while(res.next()){
            ResultSet res1 = base.copyInfo(res.getInt("copyID"));

            if (document.getDocID() == res1.getInt("commonID")) {
                t = true;
                date = res1.getString("date");
                copyID = res.getInt("copyID");
            }
        }

        if (!t)
            return new IntAndString(0, ans);

        Documents [] n = getHaveToReturnNotifications();
        for (Documents nn : n){
            if (nn.getDocID() == document.getDocID())
                return new IntAndString(4, ans);
        }

        if (calculateFine(userID.get(), document.getDocID(), date) > 0)
            return new IntAndString(1, ans);


        ans = getDateToReturn(document);

        if (getStatus().equals("Visiting Professor")){

            base.renew(copyID, userID.get(), ans, "F");

            return new IntAndString(2, ans);
        }

        if (base.renew(copyID, userID.get(), ans, "T"))
            return new IntAndString(2, ans);

        return new IntAndString(3, ans);


    }

    public IntAndString renewTest(Documents document, String dateS) throws SQLException {
        //return 0 if this user didn't check out this document
        //return 1 if user have to return document to library, he reached a fine and can't renew this document
        //return 2 if successful
        //return 3 if this user already renewed this document

        init();

        String ans = "";

        ResultSet res = checkedOut(userID.get());

        boolean t = false;
        int copyID = 0;
        String date = "";

        while(res.next()){
            ResultSet res1 = base.copyInfo(res.getInt("copyID"));

            if (document.getDocID() == res1.getInt("commonID")) {
                t = true;
                date = res1.getString("date");
                copyID = res.getInt("copyID");
            }
        }

        if (!t)
            return new IntAndString(0, ans);

        Documents [] n = getHaveToReturnNotificationsTest(dateS);
        for (Documents nn : n){
            if (nn.getDocID() == document.getDocID())
                return new IntAndString(4, ans);
        }

        if (calculateFineTest(userID.get(), document.getDocID(), date, dateS).getSecond() > 0)
            return new IntAndString(1, ans);


        ans = getDateToReturnTest(document, dateS);
        

        if (base.renew(copyID, userID.get(), ans, "T"))
            return new IntAndString(2, ans);

        return new IntAndString(3, ans);


    }

    public ResultSet checkedOut(int userID){ //Method returns a list of checked out Documents with number of copy he took of user with userID
        init();

        if (!base.checkUserID(userID))
            return null;

        return base.checkedOutByUserID(userID);
    }

    public String getDate(){
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        return f.format(d);
    }

    public Documents[] bookedDocuments(){

        // get array of docIDs
        ArrayList<Integer> arr = base.findBookedDocuments(userID.get());
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

    public int checkFine() throws SQLException{

        init();

        return currentFine;
    }

    public IntAndInt checkFineTest(Documents document, String dateS) throws SQLException {
        ResultSet res = checkedOut(userID.get());

        String date = "";

        while(res.next()) {

            if (document.getDocID() == res.getInt("commonID"))
                date = res.getString("date");
        }

        IntAndInt ans = calculateFineTest(userID.get(), document.getDocID(), date, dateS);

        return ans;
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

}
