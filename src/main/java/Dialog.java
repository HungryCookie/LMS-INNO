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
            System.out.println(PatronController.checkCode.getInt());
            switch (PatronController.checkCode.getInt()) {
                case 0: {
                    message.setText("Unfortunately, document is unavailable. You were added to the queue. ");
                    break;
                }
                case 1: {
                    message.setText("You've already checked out this document. Don't forget to return it in time.");
                    break;
                }
                case 2: {
                    message.setText("Document is still not available");
                    break;
                }
                case 3: {
                    message.setText("This document was successfully checked out for you due to");
                    id.setText(PatronController.checkCode.getString());
                    break;
                }
                case 4: {
                    message.setText("Thank you for waiting. Document was checked out due to");
                    id.setText(PatronController.checkCode.getString());
                    break;
                }
                case 5: {
                    message.setText("Sorry, you have unpaid fine");
                    break;
                }
                case 6: {
                    message.setText("You have already overused document, please return it");
                    break;
                }
                case 7: {
                    message.setText("Successfully renewed, new date:");
                    id.setText(PatronController.checkCode.getString());
                    break;
                }
                case 8: {
                    message.setText("You have already renewed it once");
                    break;
                }
                case 9: {
                    message.setText("Now " + PatronController.checkCode.getString() + " are available to you");
                    break;
                }
                case 10: {
                    message.setText("You was deleted from the queue for " + PatronController.checkCode.getString() + " because of outstanding request");
                    break;
                }
                case 11: {
                    message.setText("Please, return " + PatronController.checkCode.getString() + " today because of outstanding request");
                    break;
                }
                default: {
                    message.setText("No code");
                    break;
                }
            }
        }
    }

}
