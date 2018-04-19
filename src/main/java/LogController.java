import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LogController {

    @FXML
    private Button back;
    @FXML
    private TableView<Log> log;
    @FXML
    private TableColumn<Log, String> dates;
    @FXML
    private TableColumn<Log, String> actions;

    @FXML
    private void initialize() throws Exception {
        ObservableList<Log> logs = FXCollections.observableArrayList();
        String[][] allLogs = ((Admin)Login.current).getLogs();
        for (int i = 0; i < allLogs.length; i++) {
            Log lg = new Log(allLogs[i][1], allLogs[i][0]);
            logs.add(lg);
        }
        dates.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        actions.setCellValueFactory(cellData -> cellData.getValue().actionProperty());
        log.setItems(logs);
    }

    @FXML
    private void back() {
        Main.window.setScene(Login.adminScene);
    }

}
