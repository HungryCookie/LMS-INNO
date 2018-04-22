import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BookingsController {

    @FXML
    private TableView<Documents> table;
    @FXML
    private TableColumn<Documents, String> title;
    @FXML
    private TableColumn<Documents, String> author;
    @FXML
    private Label titleLabel;
    @FXML
    private Label authorLabel;

    @FXML
    private void initialize() {
        titleLabel.setText("");
        authorLabel.setText("");
        ObservableList<Documents> docs = FXCollections.observableArrayList();
        Documents[] order = ((Patron)Login.current).bookedDocuments();
        int i = 0;
        while (i < order.length) {
            docs.add(order[i]);
            i++;
        }
        title.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        table.setItems(docs);
        table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showInfo(newValue)));
    }

    @FXML
    private void back() {
        Main.window.setScene(Login.patronScene);
    }

    @FXML
    private void showInfo(Documents doc) {
        authorLabel.setText(doc.getAuthor());
        titleLabel.setText(doc.getName());
    }

}
