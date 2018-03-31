import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Documents {
    private StringProperty name;
    private StringProperty type; // Book, JA or AV material
    private IntegerProperty docID; //ID of proper document
    private StringProperty author;
    private IntegerProperty copies;
    private boolean reference;
    private boolean bestseller;
    private IntegerProperty cost;
    private StringProperty publisher;
    private StringProperty edition;
    private StringProperty year;
    private IntegerProperty counter;

    private FcukBase base = new FcukBase();

    public Documents(int docID){

        if (!base.checkDocumentByID(docID)) {
            return;        }

        ResultSet res = base.getDocumentByID(docID);
        try {
            //if docID is not correct return null;
            /*if (!res.next()) {
                return;
            }*/
            //else get result by docID and set all the field
            this.docID = new SimpleIntegerProperty(docID);
            this.name = new SimpleStringProperty(res.getString("name"));
            reference = (res.getString("reference").charAt(0) == 'T');
            bestseller = (res.getString("bestseller").charAt(0) == 'T');
            this.author = new SimpleStringProperty(res.getString("author"));
            this.copies = new SimpleIntegerProperty(res.getInt("counter"));
            this.cost = new SimpleIntegerProperty(res.getInt("cost"));
            this.type = new SimpleStringProperty(res.getString("type"));
            this.publisher = new SimpleStringProperty(res.getString("publisher"));
            this.edition = new SimpleStringProperty(res.getString("edition"));
            this.year = new SimpleStringProperty(res.getString("year"));
            this.counter = new SimpleIntegerProperty(res.getInt("counter"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Documents(String name){
        if (!base.checkDocumentByName(name)) {
            return;
        }
        ResultSet res = base.getDocumentByName(name);
        try {
            //if name is not correct return null;
            /*if (!res.next()) {
                return;
            }*/
            //else get result by name and set all the fields
            //System.out.println("--------"+name);
            this.name = new SimpleStringProperty(res.getString("name"));

            this.docID = new SimpleIntegerProperty(res.getInt("id"));
            reference = (res.getString("reference").charAt(0) == 'T');
            bestseller = (res.getString("bestseller").charAt(0) == 'T');
            this.author = new SimpleStringProperty(res.getString("author"));
            this.copies = new SimpleIntegerProperty(res.getInt("counter"));
            this.cost = new SimpleIntegerProperty(res.getInt("cost"));
            this.type = new SimpleStringProperty(res.getString("type"));
            this.publisher = new SimpleStringProperty(res.getString("publisher"));
            this.edition = new SimpleStringProperty(res.getString("edition"));
            this.year = new SimpleStringProperty(res.getString("year"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*public static void main(String[] args) {
        Documents doc = new Documents("Touch");
        System.out.println(doc.getName());
        System.out.println(doc.getDocID());
        System.out.println(doc.getCopies());
    }*/

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() { return name; }

    public StringProperty typeProperty() { return type; }

    public StringProperty authorProperty() { return author; }

    public IntegerProperty docIDProperty() { return docID; }

    public IntegerProperty copiesProperty() { return copies; }

    public IntegerProperty costProperty() { return cost; }

    public String getType() {
        return type.get();
    }

    public int getDocID() {
        return docID.get();
    }

    public String getAuthor() {
        return author.get();
    }

    public int getCopies() {
        return copies.get();
    }

    public int getCost() {
        return cost.get();
    }

    public boolean isReference() {
        return (counter.get() <= 1);
    }



    public boolean isBestseller() {
        return bestseller;
    }

    public boolean chechName() {
        return base.checkDocumentByName(name.get());
    }

    public String getPublisher() {
        return publisher.get();
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public String getEdition() {
        return edition.get();
    }

    public StringProperty editionProperty() {
        return edition;
    }

    public String getYear() {
        return year.get();
    }

    public StringProperty yearProperty() {
        return year;
    }

    public FcukBase getBase() {
        return base;
    }

    public int getCounter() {
        return counter.get();
    }

    public IntegerProperty counterProperty() {
        return counter;
    }
}
