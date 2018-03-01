import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Date;
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
        
        Patron p = new Patron(userID);
        
        return p;
    }
    
    //returning system
    
    public void addUser(String name, String phoneNumber, String address, String status) {
        
        String pass = "";
            
        for (int i = 0; i < 5; i++){
            int randomNum = ThreadLocalRandom.current().nextInt(48, 123);
            pass += Character.toString((char)randomNum);
        }
        
        base.addNewUser(name, phoneNumber, adress, status, pass);
    }

    public void addDocument(String name, String author, int counter, int cost, String bestseller){
    
        base.addDocument(name, author, counter, cost, bestseller);
    }
    
    public boolean checkOut(int userID, String docName){ //Method returns false then userId or docName is wrong
                                                         //Also if this user didn't booked this document. Otherwise true
        if (base.checkUserID(userID) && base.checkDocumentByName(docName))
            return false;
        
        Document doc = new Document(docName);
        int [] a = base.findCopyID(doc.getDocID());
        
        if (a.length == 0)
            return false;
        
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            
        base.checkOut(userID, a[0], f.format(d));
        
        return true;
    }
    
    public void changeName(String name) {
        /*Change user's name with new one*/
    }

    public void changeNumber() {
        /*Change user's phone number*/
    }

    public void changeAddress() {
        /*Change user's address*/
    }
}
