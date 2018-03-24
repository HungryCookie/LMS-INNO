import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ReturnDoc {

    @FXML
    private TableView<Copy> table;
    @FXML
    private TableColumn<Copy, String> title;
    @FXML
    private TableColumn<Copy, String> author;
    @FXML
    private Button back;
    @FXML
    private Button returnDoc;
    @FXML
    private Label error;
    private LibrarianController lc;
    private int copyID;

    @FXML
    private void initialize() throws SQLException {
        ObservableList<Copy> docs = FXCollections.observableArrayList();
        ResultSet order = ((Librarian) Login.current).checkedOut(LibrarianController.userId);
        while (order.next()) {
            Copy copy = new Copy(order.getString("author"), order.getString("name"), order.getInt("copyID"));
            docs.add(copy);
        }
        title.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        table.setItems(docs);
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedDoc(newValue));
    }

    private void selectedDoc(Copy copy) {
        if (copy == null) copyID = -1;
        else copyID = Integer.parseInt(copy.copyIDProperty().get());
    }

    @FXML
    public void returnDoc() {
        if (copyID == -1) error.setText("Select document to return");
        else {
            /*String success = ((Librarian)Login.current).returnDoc(copyID);
            if (!success.equals("")) {
                int index = table.getSelectionModel().getSelectedIndex();
                table.getItems().remove(index);
            }
            else {
                error.setText("Something goes wrong");
            }*/
        }
    }

    @FXML
    private void back() {
        Main.window.setScene(Login.librarianScene);
    }
}
