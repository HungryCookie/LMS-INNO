import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatronController {

    @FXML
    private TextField searchtext;
    @FXML
    private Button checkOut;
    @FXML
    private Label title;
    @FXML
    private Label author;
    @FXML
    private Label type;
    @FXML
    private Label edit;
    @FXML
    private Label publisher;
    @FXML
    private Label date;
    @FXML
    private Label bestseller;
    @FXML
    private Button back;
    @FXML
    private Button info;
    @FXML
    private TableColumn<Documents, String> authorCol;
    @FXML
    private TableColumn<Documents, String> titleCol;
    @FXML
    private TableView<Documents> tab;
    @FXML
    public Scene tableScene;
    private FcukBase fb = new FcukBase();
    public static IntAndString checkCode = new IntAndString();

    @FXML
    private void initialize() throws SQLException {
        title.setText("");
        author.setText("");
        publisher.setText("");
        type.setText("");
        edit.setText("");
        date.setText("");
        bestseller.setText("");
        ObservableList<Documents> docs = FXCollections.observableArrayList();
        ResultSet order = fb.getDocs();
        while (order.next()) {
            Documents copy = new Documents(order.getString("name"));
            docs.add(copy);
        }
        titleCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        tab.setItems(docs);
        tab.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedDoc(newValue));
        searchtext.textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                search(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }

    @FXML
    private void selectedDoc(Documents doc) {
        title.setText(doc.getName());
        author.setText("Author: " + doc.getAuthor());
        type.setText("Type: " + doc.getType());
        if (!doc.getType().equals("AV")) {
            if (!publisher.getText().equals(""))publisher.setText("Publisher: " + doc.getPublisher());
            if (!date.getText().isEmpty()) date.setText("Date: " + doc.getYear());
            if (doc.isBestseller()) bestseller.setText("Bestseller");
            else bestseller.setText("");
            if (doc.getType().equals("Book")) {
                if (!edit.getText().isEmpty()) edit.setText("Edition: " + doc.getEdition());
            }
            else {
                if (!edit.getText().isEmpty()) edit.setText("Editor: " + doc.getEdition());
            }
        }
        else {
            publisher.setText("");
            date.setText("");
            bestseller.setText("");
            edit.setText("");
        }
    }

    @FXML
    private void search(String que) throws SQLException {
        ObservableList<Documents> docs = FXCollections.observableArrayList();
        ResultSet order = fb.getDocs();
        while (order.next()) {
            Documents copy = new Documents(order.getString("name"));
            if ((copy.getName().contains(que)) || (copy.getAuthor().contains(que))) docs.add(copy);
        }
        titleCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        tab.setItems(docs);
        tab.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedDoc(newValue));
    }

    @FXML
    private void back() throws Exception {
        Main.window.setScene(Main.login);
    }

    @FXML
    private void checkOut() throws SQLException, IOException {
        Patron pr = new Patron(Login.current.getID());
        IntAndString temp = pr.checkOut(tab.getSelectionModel().getSelectedItem());
        System.out.println(temp.getInt());
        checkCode = temp;
        System.out.println(checkCode.getInt());
        System.out.println();
        Stage dialog = new Stage();
        dialog.setTitle("Checking out document");
        Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
        dialog.setScene(new Scene(root));
        dialog.show();
    }

    @FXML
    private void info() throws Exception{
        Parent tab = FXMLLoader.load(getClass().getResource("/table.fxml"));
        tableScene = new Scene(tab, 1200, 400);
        Main.window.setScene(tableScene);
    }
}
