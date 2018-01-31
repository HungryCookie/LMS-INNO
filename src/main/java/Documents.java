import java.sql.*;
import java.util.Date;

public abstract class Documents {
    private String name;
    private String type; // Book, JA or AV material
    private int bookID; //ID of proper document

    private Date date; //date, when doc was checked out
    private String [] authors; //list of authors of proper document

    /*private static final String URL = "jdbc:mysql://localhost:3306/ldata?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";            //"jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection = null;

    public static void main(String[] args) {

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
}
