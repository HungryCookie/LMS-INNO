import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

    public Button login;
    public static TextField id;
    public PasswordField password;
    public static Label wrongid;
    public static Label wrongpass;
    public static Users Current;

    public Login() throws Exception {
    }

    public void login() throws Exception {
        Current = Main.findUser(id.getText());
        if (Current == null) {
            wrongid.setText("This ID doesn't exist");
        } else {
            if (!Current.getPassword().equals(password)) {
                wrongpass.setText("Wrong password");
            } else {
                if (Current instanceof Librarian) {
                    Parent root = FXMLLoader.load(getClass().getResource("/Librarian.fxml"));
                    Main.window.setScene(new Scene(root, 659, 400));
                } else if (Current instanceof Patron) {
                    Parent root = FXMLLoader.load(getClass().getResource("/Patron.fxml"));
                    Main.window.setScene(new Scene(root, 659, 400));
                }
            }
        }
    }
}
