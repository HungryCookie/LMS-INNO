import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    public Button login;
    public TextField id;
    public PasswordField password;
    public Label error;

    public void login() throws Exception{
        if (password.getText().equals("MyPass")) {
            Parent patronscene = FXMLLoader.load(getClass().getResource("PatronWindow.fxml"));
            LibrINNO.window.setScene(new Scene(patronscene, 600, 550));
        }
        else {
            error.setText("Wrong Password");
            login.setText("Try Again");
        }
    }

}
