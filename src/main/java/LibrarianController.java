import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class LibrarianController {

    public Button back;

    public void back() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Main.window.setScene(new Scene(root, 659, 400));
    }

}
