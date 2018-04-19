import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static Stage window;
    public static Scene login;

    public static void main(String[] args) throws Exception {launch(args); }

    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        login = new Scene(root, 600, 550);
        window.setTitle("LibrINNO: Management System");
        window.setScene(login);
        window.show();
    }

}
