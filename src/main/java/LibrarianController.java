import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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
    private Button addCopy;
    @FXML
    private Button modifyDoc;
    @FXML
    private Button deleteDoc;
    @FXML
    private Button returnDoc;
    @FXML
    public TextField userID;

    public static int userId;
    public static int docId;
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
        dialog.setTitle("Check out document");
        userId = Integer.parseInt(userID.getText());
        Parent root = FXMLLoader.load(getClass().getResource("/checkout.fxml"));
        dialog.setScene(new Scene(root, 315, 155));
        dialog.show();
    }

    @FXML
    private void returnDoc() throws IOException {
        userId = Integer.parseInt(userID.getText());
        Parent root = FXMLLoader.load(getClass().getResource("/returndoc.fxml"));
        Main.window.setScene(new Scene(root, 600, 400));
    }

    @FXML
    private void addUser() throws IOException {
        userId = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/userinfo.fxml"));
        Main.window.setScene(new Scene(root, 600, 400));
    }

    @FXML
    private void addDoc() throws IOException {
        userId = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/docinfo.fxml"));
        Main.window.setScene(new Scene(root, 600, 400));}

    @FXML
    private void addCopy() {}

    @FXML
    private void modifyUser() throws IOException {
        userId = Integer.parseInt(userID.getText());
        Parent root = FXMLLoader.load(getClass().getResource("/userinfo.fxml"));
        Main.window.setScene(new Scene(root, 600, 400));
    }

    @FXML
    private void modifyDoc() throws IOException {
        docId = Integer.parseInt(docID.getText());
        Parent root = FXMLLoader.load(getClass().getResource("/docinfo.fxml"));
        Main.window.setScene(new Scene(root, 600, 400));

    }

    @FXML
    private void deleteUser() {}

    @FXML
    private void deleteDoc() {}

    @FXML
    private void deleteCopy() {}

    @FXML
    private void bookings() throws Exception{
        userId = Integer.parseInt(userID.getText());
        Parent tab = FXMLLoader.load(getClass().getResource("/table.fxml"));
        tableScene = new Scene(tab, 600, 550);
        Main.window.setScene(tableScene);
    }
}
