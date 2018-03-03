import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PatronController {

    @FXML
    private TextField searchtext;
    @FXML
    private Button search;
    @FXML
    private Label note;
    @FXML
    private Button back;
    @FXML
    public Scene tableScene;

    @FXML
    private void search() {
        Documents document;
        String order = searchtext.getText();
        try {
            document = new Documents(Integer.parseInt(order));
        } catch (Exception e) {
            document = new Documents(order);
        }

        if (document == null) {
            note.setText("Sorry, there is no such document");
            note.setStyle("-fx-text-fill: red");
        } else {
            int weeks = Login.current.bookADocument(document);
            if (weeks != 0) {
                note.setStyle("-fx-text-fill: green");
                note.setText("This document was successfully booked for " + weeks + " weeks");
            } else {
                note.setStyle("-fx-text-fill: red");
                note.setText("You have already booked this document");
            }
        }
    }

    @FXML
    private void back() throws Exception {
        Main.window.setScene(Main.login);
    }

    @FXML
    private void info() throws Exception{
        Parent tab = FXMLLoader.load(getClass().getResource("/table.fxml"));
        tableScene = new Scene(tab, 600, 550);
        Main.window.setScene(tableScene);
    }
}
