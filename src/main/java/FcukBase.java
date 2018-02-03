import java.sql.*;
import java.util.ArrayList;

public class FcukBase implements FcukBaseInterface{
    private static final String URL = "jdbc:mysql://localhost:3306/ldata?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";            //"jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection = null;
    public FcukBase() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                //System.out.println("Connection established");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUserByID(int userID) {
        String query = "select * from users where id = ?";
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
        String query = "select * from documents where id = ?";
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

    public void bookADocument(int docID, int userID) {
        String query = "insert into booking values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, docID);
            statement.setInt(2, userID);
            statement.execute();
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
        String query = "select copyID from copies where commonID = ?";
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

    private ResultSet getByID(int ID, String tableName) {
        String query = "select * from ? where id = ?";
        ResultSet res;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,tableName);
            statement.setInt(2, ID);
            res = statement.executeQuery();
            if (res.next()) {
                return res;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public static void main(String[] args) {
        FcukBase b = new FcukBase();
        String name = "Touch of class";
        ResultSet res = b.getDocumentByName(name);
        try {
            System.out.println(res.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

}



/*public static void main(String[] args) {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("select * from users where id < 3");
            *//*if (res.next()) {
                System.out.println(res.getInt(1));
            }*//*
            while (res.next()) {
                System.out.println(res.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


/*public ResultSet getUserByID(int ID) {
        String query = "select * from users where id = ?";
        ResultSet res;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            //statement.setString(1,tableName);
            statement.setInt(1, ID);
            res = statement.executeQuery();
            if (!res.next()) {
                res.beforeFirst();
                return null;
            } else {
                res.beforeFirst();
                return res;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        //return getByID(ID, "users");
    }

    public ResultSet getBookByID(int ID) {
        String query = "select * from documents where id = ?";
        ResultSet res;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            //statement.setString(1,tableName);
            statement.setInt(1, ID);
            res = statement.executeQuery();
            if (!res.next()) {
                res.beforeFirst();
                return null;
            } else {
                res.beforeFirst();
                return res;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        //return getByID(ID, "documents");
    }

    public ResultSet getBookByName(String name) {
        String query = "select * from documents where name = ?";
        ResultSet res;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            res = statement.executeQuery();
            if (!res.next())
                return null;
            if (res.next())
                return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/