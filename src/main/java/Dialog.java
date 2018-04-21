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
        id.setText("");
        pass.setText("");
        if ((Login.current instanceof Librarian) || (Login.current instanceof Admin)) {
            if ((LibrarianController.checkCode != null) && (LibrarianController.checkCode.getInt() == 0))
                message.setText("Unfortunately, document is unavailable. You were added to the queue. ");
            else if ((LibrarianController.checkCode != null) && (LibrarianController.checkCode.getInt() == 2))
                message.setText("Magazine is not available for checking out");
            else {
                message.setText(LibrarianController.object + " was successfully " + LibrarianController.action);
                if (LibrarianController.action.equals("added") && LibrarianController.object.equals("User")) {
                    id.setText("ID: " + UserInfo.id);
                    pass.setText("Password: " + UserInfo.pass);
                } else {
                    id.setText("");
                    pass.setText("");
                }
            }
            //(new Admin(1)).addLog(Login.current.getName() + " " + LibrarianController.action + " " + LibrarianController.object, "");
        }
        else {
            id.setText("");
            pass.setText("");
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
                    (new Admin(1)).addLog(Login.current.getName() +
                            " checked out" + (new Documents(PatronController.docID)).getName(), "");
                    break;
                }
                case 4: {
                    message.setText("Thank you for waiting. Document was checked out due to");
                    id.setText(PatronController.checkCode.getString());
                    (new Admin(1)).addLog(Login.current.getName() +
                            " checked out" + (new Documents(PatronController.docID)).getName(), "");
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
                    (new Admin(1)).addLog(Login.current.getName() + " renewed "
                            + new Documents(PatronController.docID).getName(), "");
                    break;
                }
                case 8: {
                    message.setText("You have already renewed it once");
                    break;
                }
                case 9: {
                    message.setText("You have to return book");
                    break;
                }
                case 10: {
                    message.setText("Now " + PatronController.checkCode.getString() + " available to you");
                    break;
                }
                case 11: {
                    message.setText("You was deleted from the queue for " + PatronController.checkCode.getString() + " because of outstanding request");
                    break;
                }
                case 12: {
                    message.setText("Please, return " + PatronController.checkCode.getString() + " today because of outstanding request");
                    break;
                }
                case 13: {
                    message.setText("Magazines are not available for checking out");
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
