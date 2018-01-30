public class Patron extends Users{
    private int fine;

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
