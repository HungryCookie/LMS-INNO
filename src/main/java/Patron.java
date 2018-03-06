import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
 
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

        if (document.getType() == "AV") {
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
            return 0;
        
        if (document.isBestseller())
           return 2;

        if (status.equals("Student"))
            return 3;

        return 4;
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
