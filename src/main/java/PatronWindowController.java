import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PatronWindowController {

    public Button search;
    public TextField findText;
    public Label empty;

    public void search() {
        if (findText.getText().equals("")) {
            empty.setText("Enter the title for searching");
        }
        else {
            empty.setText("");
            //do something
        }
    }

}
