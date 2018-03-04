import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Checkout {

    @FXML
    private TextField docID;
    @FXML
    public Button checkOut;
    @FXML
    public Label error;

    @FXML
    private void checkOut() {
        error.setText("");
        int docid = -1;
        try {
            docid = Integer.parseInt(docID.getText());
        }catch (Exception e) {
            error.setText("Wrong ID");
        }
        Librarian lb = new Librarian(Login.current.getID());
        Documents doc = new Documents(docid);
        boolean success = lb.checkOut(LibrarianController.userId, doc);
        if (success) {
            LibrarianController.dialog.close();
        }
        else error.setText("Something wrong");
    }
}
