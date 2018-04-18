import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class DocInfo {
    @FXML
    private TextField name;
    @FXML
    private TextField year;
    @FXML
    private TextField author;
    @FXML
    private TextField copies;
    @FXML
    private Label nameError;
    @FXML
    private Label pubLabel;
    @FXML
    private Label bsLabel;
    @FXML
    private Label copyLabel;
    @FXML
    private Label costLabel;
    @FXML
    private Label copiesError;
    @FXML
    private Label authorError;
    @FXML
    private Label yearLabel;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private CheckBox bestseller;
    @FXML
    private Label typeError;
    @FXML
    private TextField cost;
    @FXML
    private TextField publisher;
    @FXML
    private Label costError;
    public int id;

    @FXML
    private void initialize() {
        type.getItems().addAll("Book", "JournalArticle", "AudioVideoMaterial");
        if (LibrarianController.docId == 0) {
            type.getSelectionModel().select(0);
            type.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> selectType(newValue)));
        }
        else {
            Documents doc = new Documents(LibrarianController.docId);
            type.getSelectionModel().select(doc.getType());
            type.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> selectType(newValue)));
            type.setDisable(true);
            author.setText(doc.getAuthor());
            name.setText(doc.getName());
            publisher.setText(doc.getPublisher());
            copies.setText(""+doc.getCopies());
            bestseller.setSelected(doc.isBestseller());
            if (LibrarianController.action.equals("modified")) copies.setDisable(true);
        }
    }

    private void selectType(String type) {
        if (type.equals("AudioVideoMaterial")) {
            publisher.setDisable(true);
            copies.setDisable(true);
            bestseller.setDisable(true);
            publisher.setVisible(false);
            copies.setVisible(false);
            bestseller.setVisible(false);
            pubLabel.setText("");
            copyLabel.setText("");
            bsLabel.setText("");
            yearLabel.setText("");
            year.setVisible(false);
            year.setDisable(true);
        } else if (type.equals("Journal")) {
            publisher.setDisable(false);
            copies.setDisable(false);
            bestseller.setDisable(false);
            publisher.setVisible(true);
            copies.setVisible(true);
            bestseller.setVisible(true);
            pubLabel.setText("Publisher:");
            copyLabel.setText("Number of copies:");
            bsLabel.setText("Bestseller:");
            yearLabel.setText("Year:");
            year.setVisible(true);
            year.setDisable(false);
        }
        else {
            publisher.setDisable(false);
            copies.setDisable(false);
            bestseller.setDisable(false);
            publisher.setVisible(true);
            copies.setVisible(true);
            bestseller.setVisible(true);
            pubLabel.setText("Publisher:");
            copyLabel.setText("Number of copies:");
            bsLabel.setText("Bestseller:");
            yearLabel.setText("Year:");
            year.setVisible(true);
            year.setDisable(false);
        }
    }

    @FXML
    private void cancel() {
        Main.window.setScene(Login.librarianScene);
    }

    @FXML
    private void apply() throws SQLException, IOException {
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
            /*if (LibrarianController.docId == 0) {
                ((Librarian)Login.current).addDocument(name.getText(), author.getText(), publisher.getText(), year.getText(), cop, cos, "", type.getSelectionModel().getSelectedItem(), bs, ref);
            }
            else ((Librarian)Login.current).modify(LibrarianController.docId, name.getText(), author.getText(), publisher.getText(), year.getText(), cop, cos, "", type.getSelectionModel().getSelectedItem(), bs, ref);*/
            Stage dialog = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
            dialog.setTitle(LibrarianController.object + " " + LibrarianController.action);
            dialog.setScene(new Scene(root));
            Main.window.setScene(Login.librarianScene);
            dialog.show();
        }
    }

}
