import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class Users {
    protected SimpleStringProperty name;
    protected String password;
    protected SimpleIntegerProperty userID; //Card number of each user
    protected String address;
    protected String phoneNumber;
    protected String status;

    public int bookADocument (Documents document) {return 0; }

    public String getName() {
        return name.get();
    }

    public String getStatus() { return status; }
 
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SimpleStringProperty getNameProperty() {return name;}

    public SimpleIntegerProperty getIDProperty() {return userID;}

    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public int getID() {
        return userID.get();
    }
 
    public void setID(int ID) {
        this.userID = new SimpleIntegerProperty(ID);
    }
 
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getPhoneNumber() {
        return phoneNumber;
    }
 
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
