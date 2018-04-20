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

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


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
    private Button renew;
    @FXML
    private Button remove;
    @FXML
    private Label titleLabel;
    @FXML
    private Label fine;
    @FXML
    private Label overdue;
    @FXML
    private Label authorLabel;
    private Patron p;
    private int docID;

    @FXML
    private void initialize() throws SQLException {
        ObservableList<Documents> docs = FXCollections.observableArrayList();
        ResultSet order;
        remove.setDisable(true);
        remove.setVisible(false);
        renew.setVisible(false);
        renew.setDisable(true);
        overdue.setText("");
        fine.setText("");
            fine.setText("Current fine: " + (new Patron(LibrarianController.userId)).checkFine() + "");
        if (Login.current instanceof Patron) {
            order = ((Patron) Login.current).checkedOut(Login.current.getID());
            renew.setVisible(true);
            renew.setDisable(false);
        }
        else {
            p = new Patron(LibrarianController.userId);
            order = p.checkedOut(p.getID());
            remove.setDisable(false);
            remove.setVisible(true);
            remove.setText("Return");
        }
        while (order.next()) {
            docs.add(new Documents(order.getString("name")));
        }
        title.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        table.setItems(docs);
        table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                showInfo(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }

    @FXML
    private void renew() throws SQLException, IOException {
        IntAndString res = ((Patron)Login.current).renew(table.getSelectionModel().getSelectedItem());
        PatronController.docID = table.getSelectionModel().getSelectedItem().getDocID();
        PatronController.checkCode = new IntAndString(5 + res.getInt(), res.getString());
        (new Admin(1)).addLog(Login.current.getName() + " renewed the document " + table.getSelectionModel().getSelectedItem().getName(), "");
        Stage dialog = new Stage();
        dialog.setTitle("Renew document");
        Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
        dialog.setScene(new Scene(root));
        dialog.show();
    }

    private void showInfo(Documents doc) throws SQLException {
        if (doc != null) {
        titleLabel.setText(doc.getName());
        authorLabel.setText(doc.getAuthor());
        docID = doc.getDocID();
        }
    }

    @FXML
    private void remove() throws SQLException {
        ResultSet rs = ((Librarian)Login.current).checkedOut(LibrarianController.userId);
        String title = table.getSelectionModel().getSelectedItem().getName();
        int copyID = 0;
        while (rs.next()) {
            if (rs.getString("name").equals(title)) copyID = rs.getInt("copyID");
        }
        ((Librarian)Login.current).returnDoc(copyID);
        (new Admin(1)).addLog((new Patron(LibrarianController.userId).getName())
                + " returned " + table.getSelectionModel().getSelectedItem().getName(), "");
        int index = table.getSelectionModel().getSelectedIndex();
        table.getItems().remove(index);
    }

    @FXML
    private void back() {
        if (Login.current instanceof Patron) Main.window.setScene(Login.patronScene);
        else Main.window.setScene(Login.librarianScene);
    }
}
