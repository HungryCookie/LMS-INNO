public class Patron extends Users{
    private int currentFine;
    private String rank;
    
    public static Patron(int userID){
       // if usedID is not correct then return null
        
       // else get result by userID and set all the fields
    }

    public static boolean bookADocument(Documents document){
        // if user with id = userID already booked document then return false
        // else put in data base a new note that user with id = userID is 
        // going to get document with id = document.docID
        // return true
        
    }
    
    public static Documents[] bookedDocuments(){
        
        // get array of docIDs
        // create Documents array
        // use docIDs and setter in Documents class to get final arrat Documents and return it
    }    
        
    public Patron() {
        /*Renew Fine for current user*/
    }

    public void bookOrder(int bookID) {
        /* Note that you ordered a book in database
        * */
    }

    public void bookOrder(String name) {
        /*Find bookID by name
        * Use method bookOrder(ID) to order a book by ID*/
    }

    public int checkFine() {
        return fine;
    }
}
