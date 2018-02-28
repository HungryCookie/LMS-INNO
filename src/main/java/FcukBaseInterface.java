import java.sql.ResultSet;
import java.util.ArrayList;

public interface FcukBaseInterface {
    ResultSet getUserByID(int userID); // Get all info about the user through its ID
    ResultSet getDocumentByID(int bookID); // Get all info about certain document through its ID
    ResultSet getDocumentByName(String name); // Get all info about certain document through its name
    ResultSet getDocumentByAuthor(String author); // Get all info about certain document through its author
    boolean bookADocument(int docID, int userID); // Place an order on a certain document from certain user
    ArrayList findBookedDocuments(int userID); // Find all booked documents of user
    ArrayList findUserByBookedDocument(int docID); // find users who booked certain document
    int[] findCopyID(int docID); // Find ID of copies of books
    boolean checkUserID(int userID); // Check whether there exist such a user
    boolean checkDocumentByName(String name); // Check whether there exist such a doc
}
