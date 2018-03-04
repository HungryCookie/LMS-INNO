import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserInfo {
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private Label nameError;
    @FXML
    private Label phoneError;
    @FXML
    private Label addressError;
    @FXML
    private ChoiceBox<String> status;
    @FXML
    private Label passLabel;
    @FXML
    private TextField passField;
    @FXML
    private Label passError;
    @FXML
    private Stage dialog;
    public static int id;
    public static String pass;

    @FXML
    private void initialize() {
        if (LibrarianController.userId != 0) {
            Patron p = new Patron(LibrarianController.userId);
            name.setText(p.name);
            phone.setText(p.phoneNumber);
            address.setText(p.address);
            status.getItems().addAll("Student", "FacultyMember", "Librarian");
            status.getSelectionModel().select(p.getStatus());
        }
        else {
            status.getItems().addAll("Student", "FacultyMember", "Librarian");
            status.getSelectionModel().select(0);
            passField.setVisible(false);
            passLabel.setVisible(false);
        }
    }

    @FXML
    private void cancel() {
        Main.window.setScene(Login.librarianScene);
    }

    @FXML
    private void apply() throws Exception{
        boolean success = true;
        if (name.getText().equals("")) {
            success = false;
            nameError.setText("Enter name");
        }
        if (phone.getText().equals("")) {
            success = false;
            phoneError.setText("Enter phone");
        }
        if (address.getText().equals("")) {
            success = false;
            addressError.setText("Enter address");
        }
        if (success) {
            if (LibrarianController.userId == 0) {
                IntAndString is = ((Librarian)Login.current).addUser(name.getText(), phone.getText(), address.getText(), status.getSelectionModel().selectedItemProperty().get());
                id = is.getInt();
                pass = is.getString();
                dialog = new Stage();
                dialog.setTitle("User added");
                Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
                dialog.setScene(new Scene(root, 315, 155));
                dialog.show();
            }
            else {
                if (passField.getText().equals("")) passError.setText("Enter new password");
                else ((Librarian)Login.current).modify(LibrarianController.userId, name.getText(), phone.getText(), address.getText(), status.getSelectionModel().selectedItemProperty().get(), passField.getText());
            }
        }
    }
}
