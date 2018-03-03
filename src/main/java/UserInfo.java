import javafx.fxml.FXML;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private void apply() {
        boolean success = true;
        if (name.getText() == "") {
            success = false;
            nameError.setText("Enter name");
        }
        if (phone.getText() == "") {
            success = false;
            phoneError.setText("Enter phone");
        }
        if (address.getText() == "") {
            success = false;
            addressError.setText("Enter address");
        }
        if (success) {
            if (LibrarianController.userId == 0) ((Librarian)Login.current).addUser(name.getText(), phone.getText(), address.getText(), status.getSelectionModel().selectedItemProperty().get());
            else {
                if (passField.getText() == "") passError.setText("Enter new password");
                else ((Librarian)Login.current).modify(LibrarianController.userId, name.getText(), phone.getText(), address.getText(), status.getSelectionModel().selectedItemProperty().get(), passField.getText());
            }
        }
    }
}
