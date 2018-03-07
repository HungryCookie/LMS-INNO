import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class FcukBase implements FcukBaseInterface{
    private static Connection connection = null;
    private static String url = "jdbc:sqlite:databaseTest.sqlite";

    public FcukBase(){
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void clear() throws Exception {
        Statement statement = connection.createStatement();
        statement.execute("delete from copies;");
        statement.execute("DELETE from booking");
        statement.execute("DELETE from users where id > 1");
        statement.execute("delete from documents");
        //statement.execute("delete from documents; \nDELETE from users where id > 1; \nDELETE from booking; \ndelete from copies;");
        /*statement.execute("DELETE FROM sqlite_master where tbl_name = 'documents'");
        statement.execute("DELETE FROM sqlite_master where tbl_name = 'booking'");
        statement.execute("DELETE FROM sqlite_master where tbl_name = 'users'");
        statement.execute(statement.executeQuery("SELECT group_concat(sql,';') FROM sqlite_master where name != 'sqlite_sequence';").getString(1));*/
        /*PreparedStatement statement1 = connection.prepareStatement("delete from copies;");
        statement.execute("delete from booking;");
        statement.execute("delete from copies;");
        statement.execute("delete from documents;");
        statement.execute(" delete from users where id > 1;");*/
        //statement.execute(string);
    }

    public int getNumberOfDocs() {
        String query = "select count(*) from copies";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNumberOfUsers() {
        String query = "select count(*) from users";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ResultSet getUserByID(int userID) {
        String query = "select * from users where rowid = ?";
        ResultSet res = null;
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            res = statement.executeQuery();
            if (res.next()) {
                return res;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ResultSet getDocumentByID(int bookID) {
        String query = "select * from documents where rowid = ?";
        ResultSet res = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookID);
            res = statement.executeQuery();
            if (res.next()) {
                return res;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ResultSet getDocumentByName(String name) {
        String query = "select * from documents where name = ?";
        ResultSet res = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            res = statement.executeQuery();
            if (res.next()) {
                return res;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ResultSet getDocumentByAuthor(String author) {
        String query = "select * from documents where author = ?";
        ResultSet res = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, author);
            res = statement.executeQuery();
            if (res.next()) {
                return res;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean bookADocument(int docID, int userID) {
        String query = "insert into booking values (?, ?)";
        String updateQuery = "update documents set counter = ? where rowid = ?";
        String referenceUpdate = "update documents set reference = 'T' where rowid = ?";
        ResultSet document = getDocumentByID(docID);
        try {
            int counter = document.getInt("counter");
            if (document.getInt("counter") > 1) {
                PreparedStatement statement = connection.prepareStatement(query);
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                statement.setInt(1, docID);
                statement.setInt(2, userID);
                statement.execute();
                updateStatement.setInt(1, counter - 1);
                updateStatement.setInt(2, docID);
                updateStatement.execute();
                return true;
            } else if (document.getInt("counter") == 1) {
                PreparedStatement referenceStatement = connection.prepareStatement(referenceUpdate);
                referenceStatement.setInt(1, docID);
                referenceStatement.execute();
                return false;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void bookAV(int docID, int userID) {
        String query = "insert into booking (bookID, userID) values (" + docID + ", " + userID + ")";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList findBookedDocuments(int userID) {
        String query = "select bookID from booking where userID = ?";
        ResultSet res;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            res = statement.executeQuery();
            while (res.next()) {
                arr.add(res.getInt(1));
                //res.beforeFirst();
            }
            /*for (Integer i : arr) {
                System.out.println(i);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;    }

    public ArrayList findUserByBookedDocument(int docID) {
        String query = "select userID from booking where bookID = ?";
        ResultSet res;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, docID);
            res = statement.executeQuery();
            while (res.next()) {
                arr.add(res.getInt(1));
            }
            /*for (Integer i : arr) {
                System.out.println(i);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public int[] findCopyID(int docID) {
        String query = "select copyID from copies where commonID = ? and availability = 'T'";
        ResultSet res;
        int rowCounter = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, docID);
            res = statement.executeQuery();
            while (res.next()) {
                rowCounter++;
            }
            int[] a = {0};
            if (rowCounter == 0) {
                return a;
            }
            int[] arr = new int[rowCounter];
            rowCounter = 0;
            res = statement.executeQuery();
            while (res.next()) {
                arr[rowCounter] = res.getInt("copyID");
                rowCounter++;
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkUserID(int userID) {
        String query = "select * from users where rowid = ?";
        ResultSet res;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            res = statement.executeQuery();
            if (res.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkDocumentByID(int bookID) {
        String query = "select * from documents where rowid = ?";
        ResultSet res = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookID);
            res = statement.executeQuery();
            if (res.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkDocumentByName(String name) {
        String query = "select * from documents where name = ?";
        ResultSet res = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            res = statement.executeQuery();
            if (res.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int addNewUser(String name, String phoneNumber, String address, String status, String password) {
        String query = "insert into users (name, phoneNumber, address, status, password) values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, phoneNumber);
            statement.setString(3, address);
            statement.setString(4, status);
            statement.setString(5, password);
            statement.execute();
            int newID = connection.createStatement().executeQuery("select last_insert_rowid()").getInt(1);
            return newID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int addNewDocument(String name, String publisher, String year, String edition,
                              String author, int counter, int cost, String reference, String bestseller) {
        String query = "insert into documents (name, author, publisher, \"year\", " +
                "edition, counter, cost, reference, bestseller, type) values (?, ?, ?, ?, ?, ?, ?, ?, ?, 'document')";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, author);
            statement.setString(3, publisher);
            statement.setString(4, year);
            statement.setString(5, edition);
            statement.setInt(6, counter);
            statement.setInt(7, cost);
            statement.setString(8, reference);
            statement.setString(9, bestseller);
            statement.execute();
            int newID = connection.createStatement().executeQuery("select last_insert_rowid()").getInt(1);
            for (int i = 0; i < counter; i++) {
                addCopy(newID);
            }
            return newID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int addNewDocument(String name, String author) {
        String query = "insert into documents (name, author, type) values (?, ?, 'AV')";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, author);
            statement.execute();
            int newID = connection.createStatement().executeQuery("select last_insert_rowid()").getInt(1);
            addCopy(newID);
            return newID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addCopy(int bookID) {
        String query = "insert into copies (commonID, availability) values (?, 'T')";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkOut(int userID, int copyID, String date) {
        String query = "update copies set availability = 'F', userID = ?, date = ? where copyID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setString(2, date);
            statement.setInt(3, copyID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int returnDoc(int copyID) {
        String query = "update copies set availability = 'T', userID = 0, date = null where copyID = ?";
        String check = "select * from copies where copyID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(check);
            statement.setInt(1, copyID);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            int bookID = rs.getInt("commonID");
            statement = connection.prepareStatement(query);
            statement.setInt(1, copyID);
            statement.execute();
            return bookID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int docByCopyID(int copyID) {
        String query = "select commonID from copies where copyID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, copyID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ResultSet checkedOutByUserID(int userID) { // ResultSet
        String query = "select copyID, date, name, author from copies " +
                        "JOIN documents on copies.commonID = documents.id " +
                        "where copies.userID = ?";
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            rs = statement.executeQuery();
            return rs;
            /*while (rs.next()) {
                System.out.println(rs.getString("name") + " " + rs.getInt("copyID"));
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet checkedOutByDocID(int bookID) {
        String query = "select copyID, date, name, author from copies " +
                        "JOIN documents on copies.commonID = documents.id " +
                        "where copies.commonID = ?";
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookID);
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void userModify(int id, String name, String phoneNumber, String address, String status, String password) {
        String query = "update users set name = ?, phoneNumber = ?, address = ?, status = ?, password = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, phoneNumber);
            statement.setString(3, address);
            statement.setString(4, status);
            statement.setString(5, password);
            statement.setInt(6, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void documentModify(int id, String name, String publisher, String year, String edition,
                               String author, int cost, String reference, String bestseller) {
        String query = "update documents set name = ?, author = ?, publisher = ?, \"year\" = ?, " +
                "edition = ?, cost = ?, reference = ?, bestseller = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, author);
            statement.setString(3, publisher);
            statement.setString(4, year);
            statement.setString(5, edition);
            statement.setInt(6, cost);
            statement.setString(7, reference);
            statement.setString(8, bestseller);
            statement.setInt(9, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void documentModify(int id, String name, String author) {
        String query = "update documents set name = '" + name + "', author = '" + author + "' where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userID) {
        String query = "delete from users where rowid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteCopy(int copyID) {
        if (!isCopyAvailable(copyID)) {
            return false;
        }
        String query = "delete from copies where copyID = ?";
        try {
            counterDown(docByCopyID(copyID));
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, copyID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void counterUp(int bookID, int newCounter) {
        String query = "update documents set counter = ? where id = ?";
        String getCounter = "select counter from documents where id = ?";
        try {
            PreparedStatement statement1 = connection.prepareStatement(getCounter);
            statement1.setInt(1, bookID);
            ResultSet rs = statement1.executeQuery();
            int counter = rs.getInt(1);
            counter += newCounter;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, counter);
            statement.setInt(2, bookID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void counterDown(int bookID) {
        counterUp(bookID, -1);
    }

    public ResultSet copiesOfDocument(int bookID) {
        String query = "select copyID, userID, date from copies where commonID = ?";
        ResultSet rs;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookID);
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isCopyAvailable(int copyID) {
        String query = "select availability from copies where copyID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, copyID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String check = rs.getString(1);
                if (check.equals("T")) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteBooking(int docID, int userID) {
        String query = "delete from booking where userID = ? and bookID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setInt(2, docID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet copyInfo(int copyID) {
        String query = "select * from copies where copyID = " + copyID;
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void main(String[] args) throws Exception {
        FcukBase b = new FcukBase();
        b.clear();
        //b.counterUp(2, 1);
        /*ResultSet rs = b.copiesOfDocument(6);
        while (rs.next()) {
            System.out.println(rs.getInt("copyID"));
        }*/
        //b.returnDoc(1);
        //b.addNewDocument("Amber Chronicles", "Roger Zelazny", 2, 110, "F", "T");
        //System.out.println(b.addNewUser("Jack Daniels",	"89224365732", "London", "Student", "zqazqa"));
        //b.checkOut(1, 2, "2018-03-23");
        //b.returnDoc(1);
    }

}