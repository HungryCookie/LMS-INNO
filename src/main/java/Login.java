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

    public void login() throws Exception{
        checkID(id.getText(), password.getText());
    }

    public void checkID(String id, String password) throws Exception {
        current = findUser(id);
        if (current == null) {
            wrongid.setText("Check ID, we can't find this user");
        } else {
            if (!current.getPassword().equals(password)) {
                wrongid.setText("");
                wrongpass.setText("Check password, it is wrong for this account");
            } else {
                wrongid.setText("");
                wrongpass.setText("");
                if (current instanceof Librarian) {
                    Parent root = FXMLLoader.load(getClass().getResource("/Librarian.fxml"));
                    librarianScene = new Scene(root, 600, 400);
                    Main.window.setScene(librarianScene);
                } else if (current instanceof Patron) {
                    Parent root = FXMLLoader.load(getClass().getResource("/Patron.fxml"));
                    patronScene = new Scene(root, 600, 400);
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
            if (userStatus.equals("Librarian")) {
                //user = new Librarian(Integer.parseInt(id));
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
