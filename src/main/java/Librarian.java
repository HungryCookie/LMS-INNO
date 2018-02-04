import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    public void addUser(String name, String address, String phoneNumber) {
        /*Add user to database
        * Create password
        * Return Login and Password for new User*/

        return;
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
