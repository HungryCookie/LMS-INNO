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

public class LibrarianController {

    @FXML
    private Button back;
    @FXML
    private Button addUser;
    @FXML
    private Button modifyUser;
    @FXML
    private Button bookings;
    @FXML
    private Button addDoc;
    @FXML
    private Button modifyDoc;
    @FXML
    private Button deleteDoc;
    @FXML
    private Button returnDoc;
    @FXML
    private Button Copy;
    @FXML
    public TextField searchUser;
    @FXML
    private TextField searchtext;
    @FXML
    private Label docError;
    @FXML
    private TableColumn<Users, String> names;
    @FXML
    private TableColumn<Users, String> ids;
    @FXML
    private TableView<Users> usrs;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
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
    private Label status;
    private FcukBase fb = new FcukBase();

    public static int userId;

    public static int docId;

    public static String action;

    public static String object;

    @FXML
    public TextField docID;
    public static Stage dialog = new Stage();
    @FXML
    public Scene tableScene;
    @FXML
    private TableColumn<Documents, String> authorCol;
    @FXML
    private TableColumn<Documents, String> titleCol;
    @FXML
    private TableView<Documents> tab;

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
        name.setText("");
        address.setText("");
        phone.setText("");
        status.setText("");
        ObservableList<Users> users = FXCollections.observableArrayList();
        ResultSet orderU = fb.getUsers();
        while (orderU.next()) {
            Users usr;
            if (orderU.getString("status").equals("Librarian"))
                usr = new Librarian(orderU.getInt("id"));
            else usr = new Patron(orderU.getInt("id"));
            users.add(usr);
        }
        names.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        ids.setCellValueFactory(cellData -> cellData.getValue().getIDProperty().asString());
        usrs.setItems(users);
        usrs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedUser(newValue));
        searchUser.textProperty().addListener(((observable, oldValue, newValue) -> {
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
    private void search(String queue) throws SQLException {
        ObservableList<Users> users = FXCollections.observableArrayList();
        ResultSet order = fb.getUsers();
        while (order.next()) {
            Users usr;
            if (order.getString("status").equals("Librarian"))
                usr = new Librarian(order.getInt("id"));
            else usr = new Patron(order.getInt("id"));
            if ((usr.getName().contains(queue)) || ((""+usr.getID()).contains(queue))) users.add(usr);
        }
        names.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        ids.setCellValueFactory(cellData -> cellData.getValue().getIDProperty().asString());
        usrs.setItems(users);
        usrs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedUser(newValue));
    }

    @FXML
    private void selectedUser(Users usr) {
        name.setText(usr.getName());
        phone.setText(usr.getPhoneNumber());
        address.setText(usr.getAddress());
        status.setText(usr.getStatus());
    }

    @FXML
    private void back() {
        Main.window.setScene(Main.login);
    }

    @FXML
    private void returnDoc() throws IOException {
        try {
            userId = usrs.getSelectionModel().getSelectedItem().getID();
            Parent root = FXMLLoader.load(getClass().getResource("/Returndoc.fxml"));
            Main.window.setScene(new Scene(root, 600, 400));
        } catch (Exception e) {
            name.setText("Choose a user...");
        }
    }

    @FXML
    private void addUser() throws IOException {
        userId = 0;
        action = "added";
        object = "User";
        Parent root = FXMLLoader.load(getClass().getResource("/Userinfo.fxml"));
        Main.window.setScene(new Scene(root, 600, 400));
    }

    @FXML
    private void addDoc() throws IOException {
        docId = 0;
        action = "added";
        object = "Document";
        Parent root = FXMLLoader.load(getClass().getResource("/Docinfo.fxml"));
        Main.window.setScene(new Scene(root, 600, 400));}

    @FXML
    private void modifyUser() throws IOException {
        try {
            action = "modified";
            object = "User";
            userId = usrs.getSelectionModel().getSelectedItem().getID();
            Parent root = FXMLLoader.load(getClass().getResource("/Userinfo.fxml"));
            Main.window.setScene(new Scene(root, 600, 400));
        }catch (Exception e) {
            name.setText("Choose a user...");
        }
    }

    @FXML
    private void modifyDoc() throws IOException {
        try {
            action = "modified";
            object = "Document";
            docId = tab.getSelectionModel().getSelectedItem().getDocID();
            Parent root = FXMLLoader.load(getClass().getResource("/Docinfo.fxml"));
            Main.window.setScene(new Scene(root, 600, 400));
        }catch (Exception e) {
            title.setText("Choose a document...");
        }
    }


    @FXML
    private void deleteUser() {
        try {
            object = "User";
            action = "deleted";
            docId = 0;
            userId = usrs.getSelectionModel().getSelectedItem().getID();
            if (((Librarian)Login.current).deleteUser(userId)) {
                dialog = new Stage();
                dialog.setTitle("User deleted");
                Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
                dialog.setScene(new Scene(root, 315, 155));
                dialog.show();
                int index = usrs.getSelectionModel().getSelectedIndex();
                usrs.getItems().remove(index);
            }
        }catch (Exception e) {
            name.setText("Choose a user...");
        }
    }

    @FXML
    private void deleteDoc() {
//        try {
//            object = "Document";
//            action = "deleted";
//            docId = Integer.parseInt(docID.getText());
//            userId = 0;
//            if (((Librarian)Login.current).deleteDocument(docId)) {
//                dialog = new Stage();
//                dialog.setTitle("Document deleted");
//                Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
//                dialog.setScene(new Scene(root, 315, 155));
//                dialog.show();
//            }
//        }catch (Exception e) {
//            docError.setText("Number is required");
//        }
    }

    @FXML
    private void Copy() {
        try {
            docId = tab.getSelectionModel().getSelectedItem().getDocID();
            Parent root = FXMLLoader.load(getClass().getResource("/CopiesActions.fxml"));
            Main.window.setScene(new Scene(root, 600, 400));
        }catch (Exception e) {
            title.setText("Choose a document...");
        }
    }

    @FXML
    private void bookings() throws Exception {
//        try {
//            userId = usrs.getSelectionModel().getSelectedItem().getID();
//            Parent tab = FXMLLoader.load(getClass().getResource("/table.fxml"));
//            tableScene = new Scene(tab, 600, 550);
//            Main.window.setScene(tableScene);
//        }catch (Exception e) {
//            userError.setText("Number is required");
//        }
    }
}
