import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        startLogin();
    }
    
    public static void patronInfo() {
        System.out.println("Enter patron's ID");
        Patron patron = Current.getPatronInfo(sc.next());
        if (Current == null) {
            System.out.println("There is no such an user, try again");
        }
        else {
            System.out.println("Name: " + patron.name + "\n" + "Address: " + patron.address + "\n" + "Phone number: " + patron.phoneNumber);
            System.out.println("List of orders: ");
            Documents [] order = patron.bookedDocuments();
            for (Documents i : order) {
                System.out.println("    " + i.getName());
            }
        }
        endOfProgram();
    }
    public static void startLogin() throws Exception {
        System.out.println("Enter your ID: ");
        String id = sc.next();
        System.out.println("Enter password");
        checkID(id, sc.next());
    }

    public static Users findUser (String id) throws Exception {
        Users user;
        FcukBase base = new FcukBase();
        String userStatus = base.getUserByID(Integer.parseInt(id)).getString("status");

        if (userStatus.equals("Librarian")) {
            user = new Librarian(Integer.parseInt(id));
        }
        else {
            user = new Patron(Integer.parseInt(id));
        }
        return user;
    }

    public static void checkID(String id, String password) throws Exception{
        Users Current = findUser(id);
        if (Current == null) {
            System.out.println("There is no such an user, try again");
            startLogin();
        }
        else {
            if (!Current.getPassword().equals(password)) {
                System.out.println("Wrong password, try again");
                startLogin();
            }
            else {
                if (Current instanceof Librarian) {
                    patronInfo();
                    endOfProgram();
                }
                else if (Current instanceof Patron){
                    System.out.println("Enter '1' to search for a new document, enter '2' to see list of your orders, enter '0' to exit");
                    char ans = sc.next().charAt(0);
                    if (ans.equalsTo('0')) System.exit(0);
                    else if (ans.equalsTo('1') {
                        System.out.println("Enter title or ID of document to search");
                    
                        Documents document;
                    
                        String order = sc.next();
                        try {
                         document = new Documents(Integer.parseInt(order));
                        }catch (Exception e) {
                            System.out.println("+++++++" + order);
                            document = new Documents(order);}

                        if (document == null){
                            System.out.println("Sorry, there is no such a document, would you like to search for another one?");
                        }
                        else{
                            if (Current.bookADocument(document)) {
                                System.out.println("Your document is successfully booked");
                            }
                            else{
                                System.out.println("You have already booked this document before, repeating is prohibited");
                            }
                        }
                    }
                   else if (ans.equalsTo('2') {
                       Documents[] order = Current.bookedDocuments();
                       for (Documents i : order) {
                            System.out.println(i.getName());
                       }
                   }
                   endOfProgram();
            }
        }
    }
    public static void endOfProgram(String id, String password) {
        System.out.println("To continue session enter '1', to exit program enter '0'");
        char ans = sc.next().charAt(0);
        if (ans == '1') try {
            checkID(id, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        else if (ans == '0') System.exit(0);
        else {
            System.out.println("Unavailable input");
            endOfProgram(id, password);
        }
    }
}
