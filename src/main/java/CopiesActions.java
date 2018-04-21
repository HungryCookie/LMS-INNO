import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CopiesActions {

    @FXML
    private TableView<Copy> table;
    @FXML
    private TableColumn<Copy, String> title;
    @FXML
    private TableColumn<Copy, String> copyID;
    @FXML
    private TableColumn<Copy, String> checkedOut;
    @FXML
    private TableColumn<Copy, String> locations;
    @FXML
    private Button back;
    @FXML
    private Button delete;
    @FXML
    private Button add;
    @FXML
    private Label error;
    @FXML
    private Button apply;
    @FXML
    private TextField count;
    @FXML
    private Label addError;

    private int copyId;

    @FXML
    private void initialize() throws SQLException {
        ObservableList<Copy> docs = FXCollections.observableArrayList();
        ResultSet order = ((Librarian) Login.current).copiesOfDocument(LibrarianController.docId);
        while (order.next()) {
            Copy copy = new Copy(order.getInt("copyID"), order.getInt("userID"));
            docs.add(copy);
        }
        title.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        copyID.setCellValueFactory(cellData -> cellData.getValue().copyIDProperty());
        checkedOut.setCellValueFactory(cellData -> cellData.getValue().checkedOutProperty());
        locations.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        table.setItems(docs);
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedDoc(newValue));
    }

    private void selectedDoc(Copy copy) {
        if (copy == null) copyId = -1;
        else copyId = Integer.parseInt(copy.copyIDProperty().get());
    }

    @FXML
    private void back() {Main.window.setScene(Login.librarianScene); }

    @FXML
    private void add() {
        count.setVisible(true);
        count.setDisable(false);
        apply.setVisible(true);
        apply.setDisable(false);
        add.setOnAction(e -> apply());
    }

    @FXML
    private void apply() {
        try {
            Documents doc = new Documents(LibrarianController.docId);
            String bs;
            String ref;
            if (doc.isBestseller()) bs = "T";
            else bs = "F";
            if (doc.isReference()) ref = "T";
            else ref = "F";
            ((Librarian3)Login.current).modify(doc.getDocID(), doc.getName(), doc.getAuthor(), doc.getPublisher(), doc.getYear(), doc.getCopies() + Integer.parseInt(count.getText()), 100, doc.getEdition(), doc.getType(), bs, ref, doc.getKeywords());
            Stage dialog = new Stage();
            (new Admin(1)).addLog(Login.current.getName() + " added " + count.getText() + " copies of " + doc.getName(), "");
            dialog.setTitle("Add copy");
            LibrarianController.object = "Copy";
            LibrarianController.action = "added";
            Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
            dialog.setScene(new Scene(root, 315, 155));
            dialog.show();
        }catch (Exception e) {
            addError.setText("Number is required");
        }
    }

    @FXML
    private void delete() {
        try {
            if (checkedOut.getCellData(table.getSelectionModel().selectedItemProperty().get()).equals("Yes")) error.setText("This copy is not in library");
            else {
                ((Librarian)Login.current).deleteCopy(copyId);
                (new Admin(1)).addLog(Login.current.getName() + " deleted the copy of " + table.getSelectionModel().getSelectedItem().nameProperty().get(), "");
                Stage dialog = new Stage();
                dialog.setTitle("Delete copy");
                LibrarianController.object = "Copy";
                LibrarianController.action = "deleted";
                table.getItems().remove(table.getSelectionModel().selectedIndexProperty().get());
                Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
                dialog.setScene(new Scene(root, 315, 155));
                dialog.show();
            }
        }catch (Exception e) {
            error.setText("Number is required");
        }}
}
