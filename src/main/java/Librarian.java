import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
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
    
    public void addUser(String name, String phoneNumber, String address, String status) { //Method adds new User into data base. And it returns userID and password
        
        String pass = "";
            
        for (int i = 0; i < 5; i++){
            Random r = new Random();
          
            int randomNum = r.nextInt(123 - 48 + 1) + 48;
            pass += Character.toString((char)randomNum);
        }
        System.out.print(pass);
        
        base.addNewUser(name, phoneNumber, address, status, pass);
    }

    public void addDocument(String name, String author, int counter, int cost, String reference, String bestseller){ //Method adds document into data base
    
        base.addNewDocument(name, author, counter, cost, reference, bestseller);
    }

    public boolean modify(int userID, String name, String phoneNumber, String address, String status){ //Method modifies fields of user with userID
        if (!base.checkUserID(userID))
            return false;

        base.userModify(userID, name, phoneNumber, address, status);

        return true;
    }

    public boolean modify(int docID, String name, String author, int counter, int cost, String reference, String bestseller){ //Method modifies fields of user with userID

        if (!base.checkDocumentByID(docID))
            return false;

        base.documentModify(docID, name, author, counter, cost, reference, bestseller);

        return true;
    }

    public boolean checkOut(int userID, Documents doc){ //Method returns false if userId is wrong
                                                         //Also if this user didn't booked this document. Otherwise true
        if (base.checkUserID(userID))
            return false;


        Patron p = new Patron(userID);              //Checking whether user booked this document
        Documents [] booked = p.bookedDocuments();
        boolean ind = false;

        for(Documents x : booked)
            if (x.getDocID() == doc.getDocID())
                ind = true;
        if (!ind)
            return false;

        int [] a = base.findCopyID(doc.getDocID());         //Getting a list of free copies of this document

        if (a.length == 0)
            return false;
        
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            
        base.checkOut(userID, a[0], f.format(d));
        
        return true;
    }

    public boolean returnDoc(int copyID){  //Method returns document to library by ID of copy. True if alright, false if it is wrong

        base.returnDoc(copyID);
        return true;
    }

    public int[] checkedOut(int userID){ //Method returns a list of checked out Documents with number of copy he took of user with userID
        int [] res = {1};

        if(!base.checkUserID(userID))
            return res;
        return res;
    }

    /*
    public static void main(String[] args) throws SQLException {

        Librarian l = new Librarian(1);
        l.addUser("Oleg", "123", "xui", "Faculty");

    }*/
}
