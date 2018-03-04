import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Copy {
    private IntegerProperty copyID;
    private StringProperty name;
    private StringProperty type; // Book, JA or AV material
    private IntegerProperty docID; //ID of proper document
    private StringProperty author;

    public Copy(String author, String name, int copyID) {
        this.copyID = new SimpleIntegerProperty(copyID);
        this.author = new SimpleStringProperty(author);
        this.name = new SimpleStringProperty(name);
    }

    public StringProperty authorProperty() {
        return author;
    }

    public IntegerProperty copyIDProperty() {
        return copyID;
    }

    public StringProperty nameProperty() {
        return name;
    }
}
