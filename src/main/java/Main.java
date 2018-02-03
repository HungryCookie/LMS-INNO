import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {

    public static Scanner sc = new Scanner(System.in);

    public static Stage window;
    public Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
    public Scene login = new Scene(root, 659, 400);

    public Main() throws IOException {
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

//    public static void startLogin() throws Exception {
//        System.out.println("Enter your ID: ");
//        String id = sc.next();
//        System.out.println("Enter password");
//        checkID(id, sc.next());
//    }

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

//    public void checkID(String id, String password) throws Exception{
//        Users Current = findUser(id);
//        if (Current == null) {
//            Login.wrongid.setText("This ID doesn't exist");
//        }
//        else {
//            if (!Current.getPassword().equals(password)) {
//                Login.wrongpass.setText("Wrong password");
//            }
//            else {
//                if (Current instanceof Librarian) {
//                }
//                else if (Current instanceof Patron){
//                    System.out.println("Enter title or ID of document to search");
//
//                    Documents document;
//
//                    String order = sc.next();
//                    try {
//                        document = new Documents(Integer.parseInt(order));
//                    }catch (Exception e) {document = new Documents(order);}
//
//                    if (document == null){
//                        System.out.println("Sorry, there is no such a document, would you like to search for another one?");
//                        endOfProgram(id, password);
//                    }
//                    else{
//                        if (Current.bookADocument(document)) {
//                            System.out.println("Your document is successfully booked");
//                            endOfProgram(id, password);
//                        }
//                        else{
//                            System.out.println("You have already booked this document before, repeating is prohibited");
//                            endOfProgram(id, password);
//                        }
//                    }
//                }
//            }
//        }
//    }

//    public void endOfProgram(String id, String password) throws Exception{
//        System.out.println("To return to searching enter '1', to exit program enter '0'");
//        char ans = sc.next().charAt(0);
//        if (ans == '1') checkID(id, password);
//        else if (ans == '0') System.exit(0);
//        else {
//            System.out.println("Unavailable input");
//            endOfProgram(id, password);
//        }
//    }

    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setScene(login);
        window.show();
    }
}
