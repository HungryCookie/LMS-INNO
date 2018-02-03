import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PatronController {

    public TextField searchtext;
    public Button search;
    public TextField note;
    public Button back;

    public void search() {
        Users Current = Login.Current;
        Documents document;
        String order = searchtext.getText();
        try {
            document = new Documents(Integer.parseInt(order));
        }catch (Exception e) {document = new Documents(order);}
        if (document == null){
            note.setText("Sorry, there is no such a document, would you like to search for another one?");
            note.setStyle("-fx-background-color: #ff0000");
        }
        else{
            if (Current.bookADocument(document)) {
                note.setText("Your document is successfully booked");
                note.setStyle("-fx-background-color: lime");
            }
            else{
                note.setText("You have already booked this document before, repeating is prohibited");
                note.setStyle("-fx-background-color: #ff0000");
            }
        }
    }

    public void back() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Main.window.setScene(new Scene(root, 659, 400));
    }
}
