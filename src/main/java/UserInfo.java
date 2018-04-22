import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
    private PasswordField passField;
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
            name.setText(p.name.get());
            phone.setText(p.phoneNumber);
            passField.setText(p.getPassword());
            address.setText(p.address);
            if (Login.current instanceof Admin)
                status.getItems().addAll("Librarian1", "Librarian2", "Librarian3");
            else
                status.getItems().addAll("Student", "Visiting Professor", "Instructor", "Professor");
            status.getSelectionModel().select(p.getStatus());
        }
        else {
            if (Login.current instanceof Admin)
                status.getItems().addAll("Librarian1", "Librarian2", "Librarian3");
            else
                status.getItems().addAll("Student", "Visiting Professor", "Instructor", "Professor");
            status.getSelectionModel().select(0);
            passField.setVisible(false);
            passLabel.setVisible(false);
        }
    }

    @FXML
    private void cancel() {
        if (Login.current instanceof Librarian) Main.window.setScene(Login.librarianScene);
        else Main.window.setScene(Login.adminScene);
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
                IntAndString is;
                if (!status.getSelectionModel().getSelectedItem().contains("Librarian")) {
                    is = ((Librarian2) Login.current).addUser(name.getText(), phone.getText(), address.getText(), status.getSelectionModel().selectedItemProperty().get());
                    (new Admin(1)).addLog(Login.current.getName() + " added user " + name.getText(), "");

                }
                else {
                    is = ((Admin) Login.current).addLibrarian(name.getText(), phone.getText(), address.getText(), status.getSelectionModel().getSelectedItem());
                    (new Admin(1)).addLog(Login.current.getName() + " added user " + name.getText(), "");
                }
                id = is.getInt();
                pass = is.getString();
            }
            else {
                if (passField.getText().equals("")) passError.setText("Enter new password");
                else
                    if (!status.getSelectionModel().getSelectedItem().contains("Librarian")) {
                        (new Admin(1)).addLog(Login.current.getName() +
                                " modified user " + (new Patron(LibrarianController.userId)).getName(), "");
                        ((Librarian1) Login.current).modify(LibrarianController.userId, name.getText(), phone.getText(),
                                address.getText(), status.getSelectionModel().selectedItemProperty().get(), passField.getText());
                    }
                    else {
                        (new Admin(1)).addLog(Login.current.getName() + " modified librarian " + name.getText(), "");
                        ((Admin) Login.current).modifyLibrarian(LibrarianController.userId, name.getText(),
                                phone.getText(), address.getText(), status.getSelectionModel().getSelectedItem(), passField.getText());
                    }
            }
            Stage dialog = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
            dialog.setTitle(LibrarianController.object + " " + LibrarianController.action);
            dialog.setScene(new Scene(root));
            if (Login.current instanceof Librarian) {
                root = FXMLLoader.load(getClass().getResource("/Librarian.fxml"));
                Login.librarianScene = new Scene(root);
                Login.librarianScene.getStylesheets().add("/material-fx-v0_3.css");
                Main.window.setScene(Login.librarianScene);
            }
            else {
                root = FXMLLoader.load(getClass().getResource("/Admin.fxml"));
                Login.adminScene = new Scene(root);
                Login.adminScene.getStylesheets().add("/material-fx-v0_3.css");
                Main.window.setScene(Login.adminScene);
            }
            dialog.show();
        }
    }
}
