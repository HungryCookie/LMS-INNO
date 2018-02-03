import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        startLogin();
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
            user = new Librarian();
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
                    System.out.println("Librarian class is in development, closing program...");
                    System.exit(0);
                }
                else if (Current instanceof Patron){
                    System.out.println("Enter title or ID of document to search");
                    
                    Documents document;
                    
                    String order = sc.next();
                    try {
                        document = new Documents(Integer.parseInt(order));
                    }catch (Exception e) {document = new Documents(order);}
                    
                    if (document == null){
                        System.out.println("Sorry, there is no such a document, would you like to search for another one?");
                        endOfProgram(id, password);
                    }
                    else{
                        if (Current.bookADocument(document)) {
                            System.out.println("Your document is successfully booked");
                            endOfProgram(id, password);
                        }
                        else{
                            System.out.println("You have already booked this document before, repeating is prohibited");
                            endOfProgram(id, password);
                        }
                    }
                }
            }
        }
    }
    public static void endOfProgram(String id, String password) {
        System.out.println("To return to searching enter '1', to exit program enter '0'");
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
