import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
 
public class Patron extends Users{
    private int currentFine;
    private String rank;
    private FcukBase base = new FcukBase();
 
    public Patron(int userID){
        ResultSet res = base.getUserByID(userID);
 
 
        try {
            // if usedID is not correct then return null
            if (!res.next()) {
                return;
            }
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
 
    public boolean bookADocument(Documents document){
        // if user with id = userID already booked document then return false
        ArrayList<Integer> arr = base.findBookedDocuments(userID);
        for (Integer i : arr) {
            if (i == document.getDocID()) {
                return false;
            }
        }
        // else put in data base a new note that user with id = userID is
        // going to get document with id = document.docID
        base.bookADocument(document.getDocID(), userID);
        return true;
 
    }
 
    public Documents[] bookedDocuments(){
 
        // get array of docIDs
        ArrayList<Integer> arr = base.findBookedDocuments(userID);
        Documents[] res = new Documents[arr.size()];
 
        // create Documents array
        // use docIDs and setter in Documents class to get final arrat Documents and return it
        for (Integer i : arr) {
            res[i] = new Documents(arr.get(i));
        }
 
        return res;
    }
 
    public Patron() {
        /*Renew Fine for current user*/
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
}
