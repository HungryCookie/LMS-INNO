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
import java.util.Date;

public class AdminController {

    @FXML
    private Button log;
    @FXML
    private Button back;
    @FXML
    private Button delete;
    @FXML
    private Button modify;
    @FXML
    private Button add;
    @FXML
    private Label name;
    @FXML
    private Label address;
    @FXML
    private Label phone;
    @FXML
    private Label priv;
    @FXML
    private TableView<Users> usrs;
    @FXML
    private TableColumn<Users, String> names;
    @FXML
    private TableColumn<Users, String> ids;
    @FXML
    private TextField search;
    @FXML
    public Scene tableScene;
    private FcukBase fb = new FcukBase();
    private Stage dialog;

    @FXML
    private void initialize() throws SQLException {
        ObservableList<Users> users = FXCollections.observableArrayList();
        ResultSet orderU = fb.getUsers();
        while (orderU.next()) {
            Users usr;
            if (orderU.getString("status").equals("Librarian1")) {
                usr = new Librarian1(orderU.getInt("id"));
                users.add(usr);
            }
            else if (orderU.getString("status").equals("Librarian2")){
                usr = new Librarian2(orderU.getInt("id"));
                users.add(usr);
            }
            else if (orderU.getString("status").equals("Librarian3")) {
                usr = new Librarian3(orderU.getInt("id"));
                users.add(usr);
            }
        }
        names.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        ids.setCellValueFactory(cellData -> cellData.getValue().getIDProperty().asString());
        usrs.setItems(users);
        usrs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedUser(newValue));
        search.textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                search(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }

    @FXML
    private void search(String queue) throws SQLException {
        ObservableList<Users> users = FXCollections.observableArrayList();
        ResultSet order = fb.getUsers();
        while (order.next()) {
            Users usr;
            if (order.getString("status").equals("Librarian1"))
                usr = new Librarian1(order.getInt("id"));
            else if (order.getString("status").equals("Librarians2"))
                usr = new Librarian2(order.getInt("id"));
            else usr = new Librarian3(order.getInt("id"));
            if ((usr.getName().contains(queue)) || ((""+usr.getID()).contains(queue))) users.add(usr);
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
            priv.setText("");
        } else {
            name.setText(usr.getName());
            phone.setText(usr.getPhoneNumber());
            address.setText(usr.getAddress());
            priv.setText(usr.getStatus());
        }
    }

    @FXML
    private void bookings() throws Exception {
        if (usrs.getSelectionModel().getSelectedItem() == null) {
            name.setText("Select user");
            address.setText("");
            phone.setText("");
            priv.setText("");
        }
        else {
            LibrarianController.userId = usrs.getSelectionModel().getSelectedItem().getID();
            Parent tab = FXMLLoader.load(getClass().getResource("/table.fxml"));

            tableScene = new Scene(tab, 1200, 600);
            Main.window.setScene(tableScene);
//            Patron ptr = new Patron(userId);
//            Date date = new Date();

        }
    }

    @FXML
    private void add() throws IOException {
        LibrarianController.userId = 0;
        LibrarianController.action = "added";
        LibrarianController.object = "User";
        Parent root = FXMLLoader.load(getClass().getResource("/Userinfo.fxml"));
        Main.window.setScene(new Scene(root, 600, 400));
    }

    @FXML
    private void delete() {
        try {
            LibrarianController.object = "User";
            LibrarianController.action = "deleted";
            LibrarianController.docId = 0;
            LibrarianController.userId = usrs.getSelectionModel().getSelectedItem().getID();
            if (((Admin)Login.current).deleteLibrarian(LibrarianController.userId)) {
                dialog = new Stage();
                dialog.setTitle("User deleted");
                Parent root = FXMLLoader.load(getClass().getResource("/Dialog.fxml"));
                dialog.setScene(new Scene(root, 315, 155));
                dialog.show();
                int index = usrs.getSelectionModel().getSelectedIndex();
                ((Admin)Login.current).addLog(Login.current.getName() + " deleted user " + usrs.getSelectionModel().getSelectedItem().getName(), "");
                usrs.getItems().remove(index);
            }
        }catch (Exception e) {
            name.setText("Choose a user...");
        }
    }

    @FXML
    private void modify() {
        try {
            LibrarianController.action = "modified";
            LibrarianController.object = "User";
            LibrarianController.userId = usrs.getSelectionModel().getSelectedItem().getID();
            Parent root = FXMLLoader.load(getClass().getResource("/Userinfo.fxml"));
            Main.window.setScene(new Scene(root, 600, 400));
        }catch (Exception e) {
            name.setText("Choose a user...");
        }
    }

    @FXML
    private void back() {
        Main.window.setScene(Main.login);
    }

    @FXML
    private void log() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Log.fxml"));
        Main.window.setScene(new Scene(root));
    }

}
