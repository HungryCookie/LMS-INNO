import java.sql.*;
import java.util.ArrayList;

public class FcukBase implements FcukBaseInterface{
    private static Connection connection = null;

    public FcukBase(){
        try {
            String url = "jdbc:sqlite:database.sqlite";
            connection = DriverManager.getConnection(url);
            /*if (!connection.isClosed()) {
                System.out.println("1");
            }*/
            /*Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from users");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        int rowCounter;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, docID);
            res = statement.executeQuery();
            res.last();
            rowCounter = res.getRow();
            res.beforeFirst();
            int[] arr = new int[rowCounter];
            rowCounter = 0;
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

    public int addNewDocument(String name, String author, int counter, int cost, String reference, String bestseller) {
        String query = "insert into documents (name, author, counter, cost, reference, bestseller) values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, author);
            statement.setInt(3, counter);
            statement.setInt(4, cost);
            statement.setString(5, reference);
            statement.setString(6, bestseller);
            statement.execute();
            int newID = connection.createStatement().executeQuery("select last_insert_rowid()").getInt(1);
            return newID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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

    public void returnDoc(int copyID) {
        String query = "update copies set availability = 'T', userID = 0, date = null where copyID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, copyID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet checkedOut(int userID) { // ResultSet
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

    public void documentModify(int id, String name, String author, int counter, int cost, String reference, String bestseller) {
        String query = "update documents set name = ?, author = ?, counter = ?, cost = ?, reference = ?, bestseller = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, author);
            statement.setInt(3, counter);
            statement.setInt(4, cost);
            statement.setString(5, reference);
            statement.setString(6, bestseller);
            statement.setInt(7, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        FcukBase b = new FcukBase();
        //b.returnDoc(1);
        //b.documentModify(5, "Amber Chronicles", "Roger Zelazny", 13, 110, "F", "T");
        //System.out.println(b.addNewUser("Jack Daniels",	"89224365732", "London", "Student", "zqazqa"));
        //b.checkOut(1, 1, "2018-03-23");
        //b.returnDoc(1);
        /*System.out.println(b.checkUserID(103));
        ResultSet rs = b.getDocumentByID(5);
        System.out.println(rs.getString("name"));*/
    }

}