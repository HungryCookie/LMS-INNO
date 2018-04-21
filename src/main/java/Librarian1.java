import java.sql.ResultSet;
import java.sql.SQLException;

public class Librarian1 extends Librarian {

    public Librarian1(int id) {
        super(id);
    }

    public boolean modify(int userID, String name, String phoneNumber, String address, String status, String password){ //Method modifies fields of user with userID
        if (!base.checkUserID(userID))
            return false;

        //System.out.println(name + phoneNumber + address + status +password);

        base.userModify(userID, name, phoneNumber, address, status, password);

        return true;
    }

    public boolean modify(int docID, String name, String author, String publisher, String year, int counter, int cost, String edition, String type, String bestseller, String reference, String keywords) throws SQLException { //Method modifies fields of user with userID

        if (!base.checkDocumentByID(docID))
            return false;

        if (type.equals("AV"))
            base.documentModify(docID, name, author, cost);
        else {

            base.documentModify(docID, name, publisher, year, edition, author, cost, reference, bestseller, keywords);

            Documents d = new Documents(docID);

            if (counter < d.getCopies()) {

                ResultSet res = base.copiesOfDocument(docID);

                while (res.next()) {
                    if (counter == d.getCopies())
                        break;

                    if (base.deleteCopy(res.getInt("copyID")))
                        counter += 1;
                }
            } else {
                while (counter > d.getCopies()) {
                    base.addCopy(d.getDocID());
                    counter -= 1;
                }
            }

        }

        return true;
    }
}
