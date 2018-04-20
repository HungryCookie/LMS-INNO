import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Admin extends Users{
    private FcukBase base = new FcukBase();

    public Admin(int userID){

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
            status = "Admin";

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public IntAndString addLibrarian(String name, String phoneNumber, String address, String status) {

        //Method adds new Librarian into data base. Status could be "Priv1", "Priv2" or "Priv3". And it returns userID and password

        String pass = "";

        for (int i = 0; i < 5; i++){
            Random r = new Random();

            int randomNum = r.nextInt(123 - 48 + 1) + 48;
            pass += Character.toString((char)randomNum);
        }

        IntAndString res = new IntAndString(base.addNewUser(name, phoneNumber, address, status, pass), pass);
        return res;
    }

    public Documents[] bookedDocuments(int userID){

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

    private void deleteBooking(int userID){

        Documents [] d = bookedDocuments(userID);

        for (Documents cur : d) {
            base.deleteBooking(cur.getDocID(), userID);
        }
    }

    public boolean deleteLibrarian(int userID){ //Method deletes user and if there isn't user with userID or this user has unreturned documents

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


    public int modifyLibrarian(int userID, String name, String phoneNumber, String address, String status, String password) throws SQLException {
        //Method modifies fields of user with userID
        // returns 0 if wrong userID
        // returns 1 if it is not Librarian
        // returns 2 if ok

        if (!base.checkUserID(userID))
            return 0;

        ResultSet res = base.getUserByID(userID);

        String st = res.getString("status");

        if (!st.equals("Priv1") && !st.equals("Priv2") && !st.equals("Priv3"))
            return 1;
        //System.out.println(name + phoneNumber + address + status +password);

        base.userModify(userID, name, phoneNumber, address, status, password);

        return 2;
    }
    
    public String getDate(){
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        return f.format(d);
    }
    
    public void addLog(String log, String time){
    
        if (time.equals(""))
            base.addInfo(getDate(), log);
        else
            base.addInfo(time, log);
    }
    
    public String[][] getLogs() throws Exception { //Returns hystory of operations, first is a log, second is a date
        
        int n = 0;
        String [][] s = new String[1000000][2];
        
        ResultSet res = base.getInfo();
        
        while(res.next()){
            s[n][0] = res.getString("string");
            s[n][1] = res.getString("time");
            n++;
        }
        
        String [][] ans = new String[n][2];
        
        for (int i = 0; i < n; i++){
            ans[i][0] = s[i][0];
            ans[i][1] = s[i][1];
        }
        
        return ans;
    }
}
