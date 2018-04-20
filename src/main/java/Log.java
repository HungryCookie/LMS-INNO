import javafx.beans.property.SimpleStringProperty;

public class Log {

    private SimpleStringProperty date;
    private SimpleStringProperty action;

    public Log(String date, String action) {
        this.action = new SimpleStringProperty(action);
        this.date = new SimpleStringProperty(date);
    }

    public SimpleStringProperty actionProperty() {
        return action;
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

}
