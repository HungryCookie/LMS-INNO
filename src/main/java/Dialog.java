import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Dialog {
    @FXML
    private Label id;
    @FXML
    private Label pass;
    @FXML
    private Label message;

    @FXML
    private void initialize() {
        message.setText(LibrarianController.object + " was successfully " + LibrarianController.action);
        if (LibrarianController.action.equals("added") && LibrarianController.object.equals("User")) {
            id.setText("ID: " + UserInfo.id);
            pass.setText("Password: " + UserInfo.pass);
        } else {
            id.setText("");
            pass.setText("");
        }
    }

}
