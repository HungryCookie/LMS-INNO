import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatronController {

    public TextField searchtext;
    public Button search;
    public Label note;
    public Button back;
    public TableView<Documents> table;
    public TableColumn<Documents, String> title;
    public TableColumn<Documents, String> author;
    public TableColumn<Documents, String> type;
    public Scene tableScene;

    public void search() {
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

    public void back() throws Exception {
        Main.window.setScene(Main.login);
    }

    public void info() throws Exception{
        Stage win = new Stage();

        ObservableList<Documents> docs = FXCollections.observableArrayList();
        Documents[] order = ((Patron) Login.current).bookedDocuments();
        for (Documents i : order) {
            docs.add(i);
        }
        title.setCellValueFactory(new PropertyValueFactory<Documents, String>("name"));
        author.setCellValueFactory(new PropertyValueFactory<Documents, String>("author"));
        type.setCellValueFactory(new PropertyValueFactory<Documents, String>("type"));
        table.setItems(docs);
        Parent tab = FXMLLoader.load(getClass().getResource("/table.fxml"));
        tableScene = new Scene(tab, 600, 550);
        win.setScene(tableScene);
        win.show();
    }
}
