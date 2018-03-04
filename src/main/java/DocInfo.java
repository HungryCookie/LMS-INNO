import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class DocInfo {
    @FXML
    private TextField name;
    @FXML
    private TextField author;
    @FXML
    private TextField copies;
    @FXML
    private Label nameError;
    @FXML
    private Label copiesError;
    @FXML
    private Label authorError;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private CheckBox bestseller;
    @FXML
    private Label typeError;
    @FXML
    private TextField cost;
    @FXML
    private Label costError;
    public int id;

    @FXML
    private void initialize() {
        type.getItems().addAll("Book", "JournalArticle", "AudioVideoMaterial");
        if (LibrarianController.docId == 0) {
            type.getSelectionModel().select(0);
        }
        else {
            Documents doc = new Documents(LibrarianController.docId);
            type.getSelectionModel().select(doc.getType());
            author.setText(doc.getAuthor());
            name.setText(doc.getName());
            copies.setText(""+doc.getCopies());
            bestseller.setSelected(doc.isBestseller());
        }
    }

    @FXML
    private void cancel() {
        Main.window.setScene(Login.librarianScene);
    }

    @FXML
    private void apply() throws SQLException {
        boolean success = true;
        if (name.getText().equals("")) {
            success = false;
            nameError.setText("Enter title");
        }
        if (author.getText().equals("")) {
            success = false;
            authorError.setText("Enter authors");
        }
        if (copies.getText().equals("")) {
            success = false;
            copiesError.setText("Enter number of copies");
        }
        try {
            Integer.parseInt(copies.getText());
        } catch (Exception e) {
            success = false;
            copiesError.setText("Only number is allowed");
        }
        if (type.getSelectionModel().getSelectedItem().equals("")) {
            success = false;
            typeError.setText("Select type");
        }
        try {
            Integer.parseInt(cost.getText());
        } catch (Exception e) {
            success = false;
            costError.setText("Enter number");
        }
        if (success) {
            int cop = Integer.parseInt(copies.getText());
            int cos = Integer.parseInt(cost.getText());
            String ref;
            if (cop < 2) ref = "T";
            else ref = "F";
            String bs;
            if (bestseller.isSelected()) bs = "T";
            else bs = "F";
            if (LibrarianController.docId == 0) {
                ((Librarian)Login.current).addDocument(name.getText(), author.getText(), cop, cos, ref, bs);
            }
            else ((Librarian)Login.current).modify(LibrarianController.docId, name.getText(), author.getText(), cop, cos, ref, bs);
        }
    }

}
