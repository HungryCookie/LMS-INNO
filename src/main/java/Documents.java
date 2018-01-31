import java.sql.*;
import java.util.Date;

public abstract class Documents {
    private String name;
    private String type; // Book, JA or AV material
    private int docID; //ID of proper document
    private String author;
    private int copies;

    public static Documents(int docID){
        
        //if docID is not correct return null;
        
        //else get result by docID and set all the fields
    }
    
    public static Documets(String name){
        
        //if name is not correct return null;
        
        //else get result by name and set all the fields
    }
    
    private static final String URL = "jdbc:mysql://localhost:3306/ldata?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";            //"jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection = null;

    public static void main(String[] args) {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("select * from users where id < 3");
            /*if (res.next()) {
                System.out.println(res.getInt(1));
            }*/
            while (res.next()) {
                System.out.println(res.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
