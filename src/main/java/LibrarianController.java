import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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
    private Button deleteUser;
    @FXML
    private Button returnDoc;
    @FXML
    private Button Copy;
    @FXML
    private Button wait;
    @FXML
    private Button request;
    @FXML
    private Button ok;
    @FXML
    private Button payfine;
    @FXML
    private Button checkOut;
    @FXML
    private TextField fine;
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

    public static IntAndString checkCode;

    public static int code;

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
        ok.setVisible(false);
        ok.setDisable(true);
        fine.setVisible(false);
        bestseller.setText("");
        deleteUser.setDisable(false);
        deleteUser.setVisible(true);
        deleteDoc.setDisable(true);
        deleteDoc.setVisible(false);
        if (Login.current.getStatus().equals("Librarian2")) {
            deleteDoc.setDisable(true);
            deleteDoc.setVisible(false);
            deleteUser.setDisable(true);
            deleteUser.setVisible(false);
            Copy.setDisable(true);
            Copy.setVisible(false);
        }
        else if (Login.current.getStatus().equals("Librarian1")) {
            deleteDoc.setDisable(true);
            deleteDoc.setVisible(false);
            deleteUser.setDisable(true);
            deleteUser.setVisible(false);
            Copy.setDisable(true);
            Copy.setVisible(false);
            addDoc.setDisable(true);
            addDoc.setVisible(false);
            addUser.setDisable(true);
            addUser.setVisible(false);
            request.setDisable(true);
            request.setVisible(false);
        }
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
                searchDoc(newValue);
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
            if ((!orderU.getString("status").contains("Librarian")) &&
                    (!orderU.getString("status").equals("Admin"))) {
                    usr = new Patron(orderU.getInt("id"));
                    users.add(usr);
            }
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
    private void searchDoc(String que) throws SQLException {
        ObservableList<Documents> docs = FXCollections.observableArrayList();
        ResultSet order = fb.getDocs();
        while (order.next()) {
            Documents copy = new Documents(order.getString("name"));
            if ((copy.getName().contains(que)) || (copy.getAuthor().contains(que)) || (copy.getKeywords().contains(que))) docs.add(copy);
        }
        titleCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        tab.setItems(docs);
        tab.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedDoc(newValue));
    }

    @FXML
    private void checkOut() throws SQLException, IOException {
        int temp = ((Librarian)Login.current).checkOut(tab.getSelectionModel().getSelectedItem());
        if (temp == 1) {
            (new Admin(1)).addLog(Login.current.getName() + " checked out " + tab.getSelectionModel().getSelectedItem().getName(), "");
            action = " checked out";
            object = "Document ";
            Stage dialog = new Stage();
            dialog.setTitle("Checking out document");
            Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
            dialog.setScene(new Scene(root));
            dialog.getScene().getStylesheets().add("/material-fx-v0_3.css");
            dialog.show();
        }
        else {
            checkCode = new IntAndString(temp, "");

        }
    }

    @FXML
    private void waitList() throws IOException {
        docId = tab.getSelectionModel().getSelectedItem().getDocID();
        Parent root = FXMLLoader.load(getClass().getResource("/WaitingList.fxml"));
        Scene waitingList = new Scene(root);
        waitingList.getStylesheets().add("/material-fx-v0_3.css");
        Main.window.setScene(waitingList);
    }

    @FXML
    private void request() throws SQLException, IOException {
        object = "Request";
        action = " done";
        ((Librarian)Login.current).outstandingRequest(tab.getSelectionModel().getSelectedItem());
        (new Admin(1)).addLog(Login.current.getName() + " placed an outstanding request for " + tab.getSelectionModel().getSelectedItem().getName(), "");
        Stage dialog = new Stage();
        dialog.setTitle("Outstanding request");
        Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
        dialog.setScene(new Scene(root));
        dialog.getScene().getStylesheets().add("/material-fx-v0_3.css");
        dialog.show();
    }

    @FXML
    private void payfine() {
        if (usrs.getSelectionModel().getSelectedItem() == null) {
            name.setText("Select user");
        } else {
            ok.setDisable(false);
            ok.setVisible(true);
            fine.setDisable(false);
            fine.setVisible(true);
        }
    }

    @FXML
    private void ok() throws Exception {
        userId = usrs.getSelectionModel().getSelectedItem().getID();
        int paid = 0;
        if (!fine.getText().equals("")) paid = Integer.parseInt(fine.getText());
        code = ((Librarian)Login.current).payFine(userId, paid);
        if (code > 0) checkCode = new IntAndString(3, "");
        if (code < 0) checkCode = new IntAndString(4, "");
        (new Admin(1)).addLog((new Patron(userId)).getName() + " paid " + fine.getText() + " rubles for fine", "");
        action = "paid";
        object = "Fine";
        dialog = new Stage();
        dialog.setTitle("Fine paid");
        Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
        dialog.setScene(new Scene(root, 315, 155));
        dialog.getScene().getStylesheets().add("/material-fx-v0_3.css");
        dialog.show();
    }

    @FXML
    private void selectedDoc(Documents doc) {
        if (doc == null) {
            title.setText("");
            author.setText("");
            type.setText("");
            publisher.setText("");
            date.setText("");
            bestseller.setText("");
            edit.setText("");
        } else {
            title.setText(doc.getName());
            author.setText("Author: " + doc.getAuthor());
            type.setText("Type: " + doc.getType());
            if (!doc.getType().equals("AV")) {
                if (!doc.getPublisher().equals("")) publisher.setText("Publisher: " + doc.getPublisher());
                if (!doc.getYear().equals("")) date.setText("Date: " + doc.getYear());
                if (doc.isBestseller()) bestseller.setText("Bestseller");
                else bestseller.setText("");
                if (doc.getType().equals("Book")) {
                    if (!edit.getText().isEmpty()) edit.setText("Edition: " + doc.getEdition());
                } else {
                    if (!edit.getText().isEmpty()) edit.setText("Editor: " + doc.getEdition());
                }
            } else {
                publisher.setText("");
                date.setText("");
                bestseller.setText("");
                edit.setText("");
            }
        }
        Main.window.setWidth(USE_COMPUTED_SIZE);
    }

    @FXML
    private void search(String queue) throws SQLException {
        ObservableList<Users> users = FXCollections.observableArrayList();
        ResultSet order = fb.getUsers();
        while (order.next()) {
            Users usr = null;
            if (order.getString("status").equals("Patron"))
                usr = new Patron(order.getInt("id"));
            if ((usr != null) && ((usr.getName().contains(queue)) || ((""+usr.getID()).contains(queue)))) users.add(usr);
        }
        names.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        ids.setCellValueFactory(cellData -> cellData.getValue().getIDProperty().asString());
        usrs.setItems(users);
        usrs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedUser(newValue));
    }

    @FXML
    private void selectedUser(Users usr) {
        if (usr == null) {
            name.setText("");
            phone.setText("");
            address.setText("");
            status.setText("");
        } else {
            name.setText(usr.getName());
            phone.setText(usr.getPhoneNumber());
            address.setText(usr.getAddress());
            status.setText(usr.getStatus());
        }
        Main.window.setWidth(USE_COMPUTED_SIZE);
    }

    @FXML
    private void back() {
        Main.window.setScene(Main.login);
    }

    @FXML
    private void returnDocument() throws IOException {
        userId = Login.current.getID();
        Parent root = FXMLLoader.load(getClass().getResource("/table.fxml"));
        tableScene = new Scene(root, 1200, 600);
        tableScene.getStylesheets().add("/material-fx-v0_3.css");
        Main.window.setScene(tableScene);
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
    private void addUser() throws IOException, SQLException {
        userId = 0;
        action = "added";
        object = "User";
        Parent root = FXMLLoader.load(getClass().getResource("/Userinfo.fxml"));
        Scene userInfo = new Scene(root, 600, 400);
        userInfo.getStylesheets().add("/material-fx-v0_3.css");
        Main.window.setScene(userInfo);
    }

    @FXML
    private void addDoc() throws IOException, SQLException {
        docId = 0;
        action = "added";
        object = "Document";
        Parent root = FXMLLoader.load(getClass().getResource("/Docinfo.fxml"));
        Scene docInfo = new Scene(root, 600, 400);
        docInfo.getStylesheets().add("/material-fx-v0_3.css");
        Main.window.setScene(docInfo);
    }

    @FXML
    private void modifyUser() throws IOException {
        try {
            action = "modified";
            object = "User";
            userId = usrs.getSelectionModel().getSelectedItem().getID();
            Parent root = FXMLLoader.load(getClass().getResource("/Userinfo.fxml"));
            Scene userInfo = new Scene(root, 600, 400);
            userInfo.getStylesheets().add("/material-fx-v0_3.css");
            Main.window.setScene(userInfo);
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
            Scene docInfo = new Scene(root, 600, 400);
            docInfo.getStylesheets().add("/material-fx-v0_3.css");
            Main.window.setScene(docInfo);
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
            if (((Librarian3)Login.current).deleteUser(userId)) {
                dialog = new Stage();
                dialog.setTitle("User deleted");
                (new Admin(1)).addLog(Login.current.getName() + " deleted user " + usrs.getSelectionModel().getSelectedItem().getName(), "");
                Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
                dialog.setScene(new Scene(root, 315, 155));
                dialog.getScene().getStylesheets().add("/material-fx-v0_3.css");
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
//            if (((Librarian3)Login.current).deleteDocument(docId)) {
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
            Scene copiesActions = new Scene(root, 600, 400);
            copiesActions.getStylesheets().add("/material-fx-v0_3.css");
            Main.window.setScene(copiesActions);
        }catch (Exception e) {
            title.setText("Choose a document...");
        }
    }

    @FXML
    private void bookings() throws Exception {
        if (usrs.getSelectionModel().getSelectedItem() == null) {
            name.setText("Select user");
            address.setText("");
            phone.setText("");
            status.setText("");
        }
        else {
            userId = usrs.getSelectionModel().getSelectedItem().getID();
            Parent tab = FXMLLoader.load(getClass().getResource("/table.fxml"));
            tableScene = new Scene(tab, 1200, 600);
            tableScene.getStylesheets().add("/material-fx-v0_3.css");
            Main.window.setScene(tableScene);
        }
    }
}
