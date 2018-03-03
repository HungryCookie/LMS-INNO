import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private void initialize() {
        ObservableList<Documents> docs = FXCollections.observableArrayList();
        Documents[] order = ((Patron) Login.current).bookedDocuments();
        for (Documents i : order) {
            docs.add(i);
        }
        title.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        table.setItems(docs);
    }

    @FXML
    private void back() {
        Main.window.setScene(Login.patronScene);
    }
}
