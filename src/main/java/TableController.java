import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class TableController {

    public TableColumn title;
    public TableColumn author;
    public TableColumn type;
    public TableColumn time;
    public TableView<Documents> table;

    public void table() {
        ObservableList<Documents> docs = FXCollections.observableArrayList();
        Documents[] order = ((Patron) Login.current).bookedDocuments();
        for (Documents i : order) {
            docs.add(i);
        }
    }
}
