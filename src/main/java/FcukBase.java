import java.sql.*;
import java.util.ArrayList;

public class FcukBase {
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

    public ResultSet getBookByID(int bookID) {
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

    public ResultSet getBookByName(String name) {
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

    public void bookADocument(int bookID, int userID) {
        String query = "insert into booking values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookID);
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
            }
            /*for (Integer i : arr) {
                System.out.println(i);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;    }

    public ArrayList findUserByBookedDocument(int bookID) {
        String query = "select userID from booking where bookID = ?";
        ResultSet res;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookID);
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