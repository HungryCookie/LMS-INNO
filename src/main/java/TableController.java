import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class TableController {

    @FXML
    private TableView<Documents> table;
    @FXML
    private TableColumn<Documents, String> title;
    @FXML
    private TableColumn<Documents, String> author;
    @FXML
    private TableColumn<Documents, String> type;
    @FXML
    private Button back;
    @FXML
    private Button remove;
    @FXML
    private Label titleLabel;
    @FXML
    private Label authorLabel;
    private Patron p;
    private int docID;

    @FXML
    private void initialize() {
        ObservableList<Documents> docs = FXCollections.observableArrayList();
        Documents[] order;
        remove.setDisable(true);
        remove.setVisible(false);
        if (Login.current instanceof Patron) {
            order = ((Patron) Login.current).bookedDocuments();
        }
        else {
            p = new Patron(LibrarianController.userId);
            order = p.bookedDocuments();
        }
        for (Documents i : order) {
            docs.add(i);
        }
        title.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        table.setItems(docs);
        table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showInfo(newValue)));
    }

    private void showInfo(Documents doc) {
        if (doc != null) {
        titleLabel.setText(doc.getName());
        authorLabel.setText(doc.getAuthor());
        docID = doc.getDocID();
        }
    }

    @FXML
    private void remove() {

    }

    @FXML
    private void checkOut() {
        Documents doc = new Documents(docID);
        /*boolean success = ((Librarian)Login.current).checkOut(LibrarianController.userId, doc);
        if (success) {
            table.getItems().remove(table.getSelectionModel().selectedIndexProperty().get());
        }*/
    }

    @FXML
    private void back() {
        if (Login.current instanceof Patron) Main.window.setScene(Login.patronScene);
        else Main.window.setScene(Login.librarianScene);
    }
}
