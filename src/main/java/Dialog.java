import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Dialog {
    @FXML
    private Label id;
    @FXML
    private Label pass;
    @FXML
    private Label message;
    private PatronController pc = new PatronController();

    @FXML
    private void initialize() {
        if (Login.current instanceof Librarian) {
            message.setText(LibrarianController.object + " was successfully " + LibrarianController.action);
            if (LibrarianController.action.equals("added") && LibrarianController.object.equals("User")) {
                id.setText("ID: " + UserInfo.id);
                pass.setText("Password: " + UserInfo.pass);
            } else {
                id.setText("");
                pass.setText("");
            }
        }
        else {
            id.setText("");
            pass.setText("");
            switch (pc.checkCode.getInt()) {
                case 0: message.setText("Unfortunately, document is unavailable. You were added to the queue. ");
                case 1: message.setText("You've already checked out this document. Don't forget to return it in time.");
                case 2: message.setText("Document is still not available");
                case 3: {
                    message.setText("This document was successfully checked out for you due to");
                    id.setText(pc.checkCode.getString());
                }
                case 4: {
                    message.setText("Thank you for waiting. Document was checked out due to");
                    id.setText(pc.checkCode.getString());
                }
            }
        }
    }

}
