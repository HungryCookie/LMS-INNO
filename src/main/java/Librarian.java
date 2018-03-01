import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Random.nextInt;
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
            Random r = new Random()
          
            int randomNum = r.nextInt(123 - 48 + 1) + 48;
            pass += Character.toString((char)randomNum);
        }
        
        base.addNewUser(name, phoneNumber, address, status, pass);
    }

    public void addDocument(String name, String author, int counter, int cost, String reference, String bestseller){
    
        base.addNewDocument(name, author, counter, cost, reference, bestseller);
    }
    
    public boolean checkOut(int userID, String docName){ //Method returns false then userId or docName is wrong
                                                         //Also if this user didn't booked this document. Otherwise true
        if (base.checkUserID(userID) && base.checkDocumentByName(docName))
            return false;
        
        Documents doc = new Documents(docName);
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
