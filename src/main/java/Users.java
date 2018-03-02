public abstract class Users {
    protected String name;
    protected String password;
    protected int userID; //Card number of each user
    protected String address;
    protected String phoneNumber;

    public int bookADocument (Documents document) {return 0; }

    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public int getID() {
        return userID;
    }
 
    public void setID(int ID) {
        this.userID = ID;
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
