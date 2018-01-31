/*
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        startLogin();
    }

    public static void startLogin() {
        System.out.println("Enter your ID: ");
        String id = sc.next();
        System.out.println("Enter password");
        checkID(id, sc.next());
    }

    public static Users findUser (String id) {
        Users user;
        //For Oleg: if no user with such ID, return null.
        //userStatus is either "Librarian" or others (patrons)
        String userStatus = "";
        if (userStatus == "Librarian") {
            user = new Librarian(id);
        }
        else {
            user = new Patron(id);
        }
        return user;
    }

    public static void checkID(String id, String password) {
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
                    String order = sc.next();
                    try {
                        Integer.parseInt(order);
                    }catch (Exception e) {}
                    Current.bookOrder(order);
                }
            }
        }
    }
}
*/
