import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LibrarianController {

    @FXML
    private Button back;
    @FXML
    private Button addUser;
    @FXML
    private Button modifyUser;
    @FXML
    private Button checkOut;
    @FXML
    private Button bookings;
    @FXML
    private Button addDoc;
    @FXML
    private Button modifyDoc;
    @FXML
    private Button deleteDoc;
    @FXML
    private Button returnDoc;
    @FXML
    private Button Copy;
    @FXML
    public TextField userID;
    @FXML
    private Label userError;
    @FXML
    private Label docError;

    public static int userId;

    public static int docId;

    public static String action;

    public static String object;

    @FXML
    public TextField docID;
    public static Stage dialog = new Stage();
    @FXML
    public Scene tableScene;

    @FXML
    private void back() {
        Main.window.setScene(Main.login);
    }

    @FXML
    private void checkOut() throws Exception{
        try {
        dialog.setTitle("Check out document");
        userId = Integer.parseInt(userID.getText());
        Parent root = FXMLLoader.load(getClass().getResource("/Checkout.fxml"));
        dialog.setScene(new Scene(root, 315, 155));
        dialog.show();
    } catch (Exception e) {
            userError.setText("Number is required");
        }
    }

    @FXML
    private void returnDoc() throws IOException {
        try {
            userId = Integer.parseInt(userID.getText());
            Parent root = FXMLLoader.load(getClass().getResource("/Returndoc.fxml"));
            Main.window.setScene(new Scene(root, 600, 400));
        } catch (Exception e) {
            userError.setText("Number is required");
        }
    }

    @FXML
    private void addUser() throws IOException {
        userId = 0;
        action = "added";
        object = "User";
        Parent root = FXMLLoader.load(getClass().getResource("/Userinfo.fxml"));
        Main.window.setScene(new Scene(root, 600, 400));
    }

    @FXML
    private void addDoc() throws IOException {
        docId = 0;
        action = "added";
        object = "Document";
        Parent root = FXMLLoader.load(getClass().getResource("/Docinfo.fxml"));
        Main.window.setScene(new Scene(root, 600, 400));}

    @FXML
    private void modifyUser() throws IOException {
        try {
            action = "modified";
            object = "User";
            userId = Integer.parseInt(userID.getText());
            Parent root = FXMLLoader.load(getClass().getResource("/Userinfo.fxml"));
            Main.window.setScene(new Scene(root, 600, 400));
        }catch (Exception e) {
            userError.setText("Number is required");
        }
    }

    @FXML
    private void modifyDoc() throws IOException {
        try {
            action = "modified";
            object = "Document";
            docId = Integer.parseInt(docID.getText());
            Parent root = FXMLLoader.load(getClass().getResource("/Docinfo.fxml"));
            Main.window.setScene(new Scene(root, 600, 400));
        }catch (Exception e) {
            docError.setText("Number is required");
        }
    }


    @FXML
    private void deleteUser() {
        try {
            object = "User";
            action = "deleted";
            docId = 0;
            userId = Integer.parseInt(userID.getText());
            if (((Librarian)Login.current).deleteUser(userId)) {
                dialog = new Stage();
                dialog.setTitle("User deleted");
                Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
                dialog.setScene(new Scene(root, 315, 155));
                dialog.show();
            }
        }catch (Exception e) {
            userError.setText("Number is required");
        }
    }

    @FXML
    private void deleteDoc() {
//        try {
//            object = "Document";
//            action = "deleted";
//            docId = Integer.parseInt(docID.getText());
//            userId = 0;
//            if (((Librarian)Login.current).deleteDocument(docId)) {
//                dialog = new Stage();
//                dialog.setTitle("Document deleted");
//                Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
//                dialog.setScene(new Scene(root, 315, 155));
//                dialog.show();
//            }
//        }catch (Exception e) {
//            docError.setText("Number is required");
//        }
    }

    @FXML
    private void Copy() {
        try {
            docId = Integer.parseInt(docID.getText());
            Parent root = FXMLLoader.load(getClass().getResource("/CopiesActions.fxml"));
            Main.window.setScene(new Scene(root, 600, 400));
        }catch (Exception e) {
            docError.setText("Number is required");
        }
    }

    @FXML
    private void bookings() throws Exception {
        try {
            userId = Integer.parseInt(userID.getText());
            Parent tab = FXMLLoader.load(getClass().getResource("/table.fxml"));
            tableScene = new Scene(tab, 600, 550);
            Main.window.setScene(tableScene);
        }catch (Exception e) {
            userError.setText("Number is required");
        }
    }
}
