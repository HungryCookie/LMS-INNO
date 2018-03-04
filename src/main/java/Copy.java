import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Copy {
    private StringProperty copyID;
    private StringProperty name;
    private StringProperty type; // Book, JA or AV material
    private IntegerProperty docID; //ID of proper document
    private StringProperty author;
    private StringProperty checkedOut;

    public Copy(String author, String name, int copyID) {
        this.copyID = new SimpleStringProperty("" + copyID);
        this.author = new SimpleStringProperty(author);
        this.name = new SimpleStringProperty(name);
    }

    public Copy(int copyID, int userID) {
        this.copyID = new SimpleStringProperty(""+copyID);
        if (userID == 0) this.checkedOut = new SimpleStringProperty("No");
        else this.checkedOut = new SimpleStringProperty("Yes");
    }

    public StringProperty authorProperty() {
        return author;
    }

    public StringProperty copyIDProperty() {
        return copyID;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty checkedOutProperty() {
        return checkedOut;
    }
}
