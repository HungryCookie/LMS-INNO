import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class LibrarianController {

    public Button back;

    public void back() {
        Main.window.setScene(Main.login);
    }

}
