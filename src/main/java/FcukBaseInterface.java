import java.sql.ResultSet;
import java.util.ArrayList;

public interface FcukBaseInterface {
    ResultSet getUserByID(int userID); // Get all info about the user through its ID
    ResultSet getDocumentByID(int bookID); // Get all info about certain document through its ID
    ResultSet getDocumentByName(String name); // Get all info about certain document through its name
    ResultSet getDocumentByAuthor(String author); // Get all info about certain document through its author
    ResultSet checkedOutByUserID(int userID); // Checks all books checked out by certain user (copyID, date, name, author)
    ResultSet checkedOutByDocID(int bookID); // Checks all books checked out by its ID (copyID, date, name, author)
    ResultSet copiesOfDocument(int bookID); // Get all copies of the certain document
    int addNewUser(String name, String phoneNumber, String address, String status, String password); // Adds new User to the database
    int addNewDocument(String name, String publisher, String year, String edition,
                       String author, int counter, int cost, String reference, String bestselle); // Adds new document to the data base
    int returnDoc(int copyID); // Returning the document to the library
    int docByCopyID(int copyID); // Return ID of the parent document by copyID -- Returns 0 if there is no such copy
    void userModify(int id, String name, String phoneNumber, String address, String status, String password); // Modifies user
    void documentModify(int id, String name, String publisher, String year, String edition,
                        String author, int cost, String reference, String bestseller); // Modifies user
    void checkOut(int userID, int copyID, String date); // Checks the document out to the certain user
    void deleteUser(int userID); // Delete a certain user
    void counterUp(int bookID, int newCounter); // Adds newCounter to current number of available copies
    void counterDown(int bookID); // Substructs from counter 1 copy
    void deleteBooking(int docID, int userID); // Delete booked document without increasing the counter
    void addCopy(int bookID); // Adds new copy
    ArrayList findBookedDocuments(int userID); // Find all booked documents of user
    ArrayList findUserByBookedDocument(int docID); // find users who booked certain document
    int[] findCopyID(int docID); // Find ID of copies of books
    boolean checkUserID(int userID); // Check whether there exist such a user
    boolean checkDocumentByName(String name); // Check whether there exist such a doc
    boolean checkDocumentByID(int bookID); // Checks whether there is such a book by its ID
    boolean bookADocument(int docID, int userID); // Place an order on a certain document from certain user
    boolean deleteCopy(int copyID); // Deletes copy if it is available
    boolean isCopyAvailable(int copyID); // Checks whether copy is available or not
}
