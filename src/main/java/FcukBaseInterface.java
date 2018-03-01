import java.sql.ResultSet;
import java.util.ArrayList;

public interface FcukBaseInterface {
    ResultSet getUserByID(int userID); // Get all info about the user through its ID
    ResultSet getDocumentByID(int bookID); // Get all info about certain document through its ID
    ResultSet getDocumentByName(String name); // Get all info about certain document through its name
    ResultSet getDocumentByAuthor(String author); // Get all info about certain document through its author
    void addNewUser(String name, String phoneNumber, String address, String status, String password); // Adds new User to the database
    void addNewDocument(String name, String author, int counter, int cost, String reference, String bestseller); // Adds new document to the data base
    void userModify(int id, String name, String phoneNumber, String address, String status, String password); // Modifies user
    void documentModify(int id, String name, String author, int counter, int cost, String reference, String bestseller); // Modifies user
    void checkOut(int userID, int copyID, String date); // Checks the document out to the certein user
    void returnDoc(int copyID); // Returning the document to the library
    boolean bookADocument(int docID, int userID); // Place an order on a certain document from certain user
    ArrayList findBookedDocuments(int userID); // Find all booked documents of user
    ArrayList findUserByBookedDocument(int docID); // find users who booked certain document
    int[] findCopyID(int docID); // Find ID of copies of books
    boolean checkUserID(int userID); // Check whether there exist such a user
    boolean checkDocumentByName(String name); // Check whether there exist such a doc
    boolean checkDocumentByID(int bookID); // Checks whether there is such a book by its ID
    ResultSet checkedOut(int userID); // Checks all books checked out by certain user
}
