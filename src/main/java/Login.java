import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

    public TextField id;
    public PasswordField password;
    public Button login;
    public Label wrongid;
    public Label wrongpass;
    public static Users current;
    public static Scene patronScene;
    public static Scene librarianScene;
    public static Scene adminScene;

    public void login() throws Exception{
        checkID(id.getText(), password.getText());
    }

    public void checkID(String id, String password) throws Exception {
        try {
            current = findUser(id);
        }catch (Exception e) {
            wrongid.setText("Enter ID");
        }
        if (current == null) {
            wrongid.setText("Check ID, we can't find this user");
        } else {
            if (!current.getPassword().equals(password)) {
                wrongid.setText("");
                wrongpass.setText("Check password, it is wrong for this account");
            } else {
                wrongid.setText("");
                wrongpass.setText("");
                if (current instanceof Admin) {
                    Parent root = FXMLLoader.load(getClass().getResource("/Admin.fxml"));
                    adminScene = new Scene(root);
                    adminScene.getStylesheets().add("/material-fx-v0_3.css");
                    Main.window.setScene(adminScene);
                }
                if (current instanceof Librarian){
                    Parent root = FXMLLoader.load(getClass().getResource("/Librarian.fxml"));
                    librarianScene = new Scene(root);
                    librarianScene.getStylesheets().add("/material-fx-v0_3.css");
                    Main.window.setScene(librarianScene);
                } else if (current instanceof Patron) {
                    Parent root = FXMLLoader.load(getClass().getResource("/Patron.fxml"));
                    patronScene = new Scene(root);
                    patronScene.getStylesheets().add("/material-fx-v0_3.css");
                    Main.window.setScene(patronScene);
                }
            }
        }
    }

    public static Users findUser (String id) throws Exception {
        Users user = null;
        FcukBase base = new FcukBase();
        if (base.checkUserID(Integer.parseInt(id))) {
            String userStatus = base.getUserByID(Integer.parseInt(id)).getString("status");
            if (userStatus.equals("Librarian1")) {
                user = new Librarian1(Integer.parseInt(id));
            }
            else if (userStatus.equals("Librarian2")) {
                user = new Librarian2(Integer.parseInt(id));
            }
            else if (userStatus.equals("Librarian3")) {
                user = new Librarian3(Integer.parseInt(id));
            }
            else if (userStatus.equals("Admin")) {
                user = new Admin(Integer.parseInt(id));
            }
            else {
                user = new Patron(Integer.parseInt(id));
            }
        } else {
            return null;
        }
        return user;
    }

}
