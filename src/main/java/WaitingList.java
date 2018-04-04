import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class WaitingList {

    @FXML
    private TableView<Users> table;
    @FXML
    private TableColumn<Users, String> title;
    @FXML
    private TableColumn<Users, String> author;
    @FXML
    private TableColumn<Users, String> type;
    @FXML
    private Button back;
    @FXML
    private Label titleLabel;
    @FXML
    private Label authorLabel;
    private int docID;

    @FXML
    private void initialize() throws SQLException {
        ObservableList<Users> docs = FXCollections.observableArrayList();
        int[] order = ((Librarian)Login.current).getWaitingList(LibrarianController.docId);
        int i = 0;
        while (i < order.length) {
            docs.add(new Patron(order[i]));
            i++;
        }
        title.setCellValueFactory(cellData -> cellData.getValue().name);
        author.setCellValueFactory(cellData -> cellData.getValue().getIDProperty().asString());
        table.setItems(docs);
        table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showInfo(newValue)));
    }

    private void showInfo(Users doc) {
        if (doc != null) {
            titleLabel.setText(doc.getName());
            authorLabel.setText(doc.getStatus());
            docID = doc.getID();
        }
    }

    @FXML
    private void back() {
        if (Login.current instanceof Patron) Main.window.setScene(Login.patronScene);
        else Main.window.setScene(Login.librarianScene);
    }
}
