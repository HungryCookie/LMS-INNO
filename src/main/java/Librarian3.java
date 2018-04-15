import java.sql.ResultSet;
import java.sql.SQLException;

public class Librarian3 extends Librarian2{

    public boolean deleteUser(int userID){ //Method deletes user and if there isn't user with userID or this user has unreturned documents

        if (!base.checkUserID(userID))
            return false;

        ResultSet res = base.checkedOutByUserID(userID);

        try {
            if (res.next())
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        deleteBooking(userID);
        base.deleteUser(userID);

        return true;
    }
}
