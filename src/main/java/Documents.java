import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
 
public class Documents {
    private String name;
    private String type; // Book, JA or AV material
    private int docID; //ID of proper document
    private String author;
    private int copies;
 
    private FcukBase base = new FcukBase();
 
    public Documents(int docID){
        ResultSet res = base.getBookByID(docID);
 
        try {
            //if docID is not correct return null;
            if (!res.next()) {
                return;
            }
            //else get result by docID and set all the field
            this.docID = docID;
 
            name = res.getString("name");
            author = res.getString("author");
            copies = res.getInt("counter");
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public Documents(String name){
        ResultSet res = base.getBookByName(name);
 
        try {
            //if name is not correct return null;
            if (!res.next()) {
                return;
            }
            //else get result by name and set all the fields
            this.docID = docID;
 
            name = res.getString("name");
            author = res.getString("author");
            copies = res.getInt("counter");
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
    }
 
    public String getName() {
        return name;
    }
 
    public String getType() {
        return type;
    }
 
    public int getDocID() {
        return docID;
    }
 
    public String getAuthor() {
        return author;
    }
 
    public int getCopies() {
        return copies;
    }
}
