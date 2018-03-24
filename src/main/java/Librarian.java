import jdk.nashorn.internal.ir.annotations.Immutable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Date;
import java.lang.Object;



import java.text.SimpleDateFormat;

public class Librarian extends Users {
    private FcukBase base = new FcukBase();

    public Librarian(int userID){

        ResultSet res = base.getUserByID(userID);
        try {
            // if usedID is not correct then return null
            /*if (!res.next()) {
                return;
            }*/
            this.userID = userID;
            // else get result by userID and set all the fields
            name = res.getString("name");
            address = res.getString("address");
            password = res.getString("password");
            phoneNumber = res.getString("phoneNumber");

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

    //returning system

    public IntAndString addUser(String name, String phoneNumber, String address, String status) { //Method adds new User into data base. And it returns userID and password

        String pass = "";

        for (int i = 0; i < 5; i++){
            Random r = new Random();

            int randomNum = r.nextInt(123 - 48 + 1) + 48;
            pass += Character.toString((char)randomNum);
        }

        IntAndString res = new IntAndString(base.addNewUser(name, phoneNumber, address, status, pass), pass);
        return res;
    }

    public void addDocument(String name, String author, String publisher, int year, int counter, int cost, String edition, String type, String bestseller, String reference){ //Method adds document into data base

        if (type == "AV")
            base.addNewDocument(name, author);
        else
            base.addNewDocument(name, publisher, year, edition, author, counter, cost, reference, bestseller);
    }

    private void deleteBooking(int userID){

        Patron p = new Patron(userID);

        Documents [] d = p.bookedDocuments();

        for (Documents cur : d) {
            base.counterUp(cur.getDocID(), 1);
            base.deleteBooking(cur.getDocID(), userID);
        }
    }

    public boolean modify(int userID, String name, String phoneNumber, String address, String status, String password){ //Method modifies fields of user with userID
        if (!base.checkUserID(userID))
            return false;

        System.out.println(name + phoneNumber + address + status +password);

        base.userModify(userID, name, phoneNumber, address, status, password);

        return true;
    }

    public boolean modify(int docID, String name, String author, String publisher, int year, int counter, int cost, String edition, String type, String bestseller, String reference) throws SQLException { //Method modifies fields of user with userID

        if (!base.checkDocumentByID(docID))
            return false;

        if (type == "AV")
            base.documentModify(docID, name, author);
        else {

            base.documentModify(docID, name, publisher, year, edition, author, cost, reference, bestseller);

            Documents d = new Documents(docID);

            if (counter < d.getCopies()) {

                ResultSet res = base.copiesOfDocument(docID);

                while (res.next()) {
                    if (counter == d.getCopies())
                        break;

                    if (base.deleteCopy(res.getInt("copyID")))
                        counter += 1;
                }
            } else {
                while (counter > d.getCopies()) {
                    base.addCopy(d.getDocID());
                    counter -= 1;
                }
            }

        }

        return true;
    }

    public int checkOut(Documents doc){  //Returns 0 if there isn't any free books for librarian except reference, librarian was added to queue
                                            //Returns 1 if book is availible
        doc = new Documents(doc.getDocID());

        if (doc.isReference()) {
            base.book(doc.getDocID(), userID, getDate());
            return 0;
        }

        int [] copies = base.findCopyID(doc.getDocID());

        base.checkOut(userID, copies[0], getDate());
        
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


    public boolean returnDoc(int copyID){  //Method returns document to library by ID of copy. True if alright, false if it is wrong

        int res = base.returnDoc(copyID);

        if (res == 0)
            return false;


        base.counterUp(res, 1);

        return true;
    }

    /*
    public boolean deleteDocument(int copyID){ //Method deletes one copy of document from data base. Ant returns false if copyID is wrong or this copy is not in library at the moment
        return base.deleteDocument
    }*/


    public boolean deleteUser(int userID){ //Method deletes user and if there isn't user with userID or this user has unreturned documents

        if (!base.checkUserID(userID))
            return false;

        ResultSet res = base.checkedOutByUserID(userID);

        try {
            if (res.next())
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        deleteBooking(userID);
        base.deleteUser(userID);

        return true;
    }

    public static void main(String[] args) throws SQLException {

        Librarian l = new Librarian(2);

        Patron p = new Patron(1);

        Documents d = new Documents(2);

        l.checkOut(p.getID(), d);

        ResultSet s = l.checkedOut(p.getID());


        while (s.next()){
            System.out.println(s.getInt("copyID"));
            System.out.println(s.getString("name"));
        }
    }
}
