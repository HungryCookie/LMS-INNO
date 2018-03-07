import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Test {

    private static Librarian lb = new Librarian(1);
    private static Patron p1;
    private static Patron p2;
    private static Patron p3;
    private static Documents b1;
    private static Documents b2;
    private static Documents b3;
    private static Documents av1;
    private static Documents av2;
    private static Counter c = new Counter();

    public static void main(String[] args) throws SQLException {
        start();
    }

    public static void start() throws SQLException {
        System.out.println("Enter number of test case (or '0' to exit): ");
        Scanner sc = new Scanner(System.in);
        String ans = sc.next();
        switch (Integer.parseInt(ans)) {
            case 1: TC1();
            case 2: TC2();
            case 3: TC3();
            case 4: TC4();
            case 5: TC5();
            case 6: TC6();
            case 7: TC7();
            default: System.exit(0);
        }
    }

    private static void initialize() {
        int users = c.CountUsers();
        int copies = c.CountCopies();
        System.out.println("Number of users in system: " + users);
        System.out.println("Number of documents in system: " + copies);
    }

    public static void TC1() throws SQLException {
        System.out.println("Before action: ");
        initialize();
        p1 = new Patron(lb.addUser("Sergey Afonso", "30001", "Via Margutta, 3", "FacultyMember").intField);
        p2 = new Patron(lb.addUser("Nadia Teixeira", "30002", "Via Sacra, 13", "Student").intField);
        p3 = new Patron(lb.addUser("Elvira Espindola", "30003", "Via del Corso, 22", "Student").intField);
        lb.addDocument("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivestand Clifford Stein", "MIT Press", "2009", 3, 0, "Third Edition", "book", "F", "F");
        lb.addDocument("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma, Ralph Johnson, John Vlissides, Richard Helm", "Addison-Wesley Professional", "2003", 2, 0, "First Edition", "book", "T", "F");
        lb.addDocument("The Mythical Man-mont", "Brooks, Jr., Frederick P", "Addison-Wesley Longman Publishing Co.,Inc.", "1995", 1, 0, "Second edition", "book", "F", "T");
        lb.addDocument("Null References: The Billion Dollar Mistake", "Tony Hoare", "", "", 0, 0, "", "AV", "F", "F");
        lb.addDocument("Information Entropy", "Claude Shannon", "", "", 0, 0, "", "AV", "F", "F");
        b1 = new Documents("Introduction to Algorithms");
        b2 = new Documents("Design Patterns: Elements of Reusable Object-Oriented Software");
        b3 = new Documents("The Mythical Man-mont");
        av1 = new Documents("Null References: The Billion Dollar Mistake");
        av2 = new Documents("Information Entropy");
        System.out.println("After action: ");
        initialize();
        start();
    }

    public static void TC2() throws SQLException {
        System.out.println("Before action: ");
        initialize();
        ResultSet rs = lb.copiesOfDocument(b1.getDocID());
        int i = 2;
        while(rs.next()) {

            if (i > 0) {
                if (rs.getInt("userID") == 0) {
                    i--;
                    lb.deleteCopy(rs.getInt("copyID"));
                }
            }
        }
        rs = lb.copiesOfDocument(b2.getDocID());
        i = 1;
        while (rs.next()) {

            if (i > 0) {
                if (rs.getInt("userID") == 0) {
                    i--;
                    lb.deleteCopy(rs.getInt("copyID"));
                }
            }
        }
        lb.deleteUser(p2.getID());
        p2 = null;
        System.out.println("After action: ");
        initialize();
        start();
    }

    public static void TC3() throws SQLException {
        System.out.println("Before action: ");
        initialize();
        userInfo(p1);
        System.out.println("----------------");
        userInfo(p3);
        System.out.println("After action: ");
        initialize();
        start();
    }

    public static void TC4() throws SQLException {
        System.out.println("Before action: ");
        initialize();
        userInfo(p2);
        System.out.println("----------------");
        userInfo(p3);
        System.out.println("After action: ");
        initialize();
        start();
    }

    public static void TC5() throws SQLException {
        System.out.println("Before action: ");
        initialize();
        if (p2 == null) System.out.println("*******Impossible to check out*******");
        else System.out.println("Checked out successfully");
        System.out.println("After action: ");
        initialize();
        start();
    }

    public static void TC6() throws SQLException {
        System.out.println("Before actions: ");
        initialize();
        p1.bookADocument(b1);
        p3.bookADocument(b1);
        p3.bookADocument(b2);
        if (!lb.checkOut(p1.getID(), b1)) System.out.println("Impossible to check out");
        else System.out.println("Checked out successfully");
        if (!lb.checkOut(p3.getID(), b1)) System.out.println("Impossible to check out");
        else System.out.println("Checked out successfully");
        if (!lb.checkOut(p3.getID(), b2)) System.out.println("Impossible to check out");
        else System.out.println("Checked out successfully");
        userInfo(p1);
        System.out.println("---------------");
        userInfo(p3);
        System.out.println("After actions: ");
        initialize();
        start();
    }

    public static void TC7() throws SQLException {
        System.out.println("Before actions: ");
        initialize();
        p1.bookADocument(b1);
        p3.bookADocument(b1);
        p1.bookADocument(b2);
        p3.bookADocument(b2);
        p1.bookADocument(b3);
        p1.bookADocument(av1);
        p3.bookADocument(av1);
        if (!lb.checkOut(p1.getID(), b1)) System.out.println("Impossible to check out");
        else System.out.println("Checked out successfully");
        if (!lb.checkOut(p1.getID(), b2)) System.out.println("Impossible to check out");
        else System.out.println("Checked out successfully");
        if (!lb.checkOut(p1.getID(), b3)) System.out.println("Impossible to check out");
        else System.out.println("Checked out successfully");
        if (!lb.checkOut(p1.getID(), av1)) System.out.println("Impossible to check out");
        else System.out.println("Checked out successfully");
        if (!lb.checkOut(p3.getID(), b1)) System.out.println("Impossible to check out");
        else System.out.println("Checked out successfully");
        if (!lb.checkOut(p3.getID(), b2)) System.out.println("Impossible to check out");
        else System.out.println("Checked out successfully");
        if (!lb.checkOut(p3.getID(), av2)) System.out.println("Impossible to check out");
        else System.out.println("Checked out successfully");
        userInfo(p1);
        System.out.println("----------------");
        userInfo(p3);
        System.out.println("After actions: ");
        initialize();
        start();
    }

    private static void userInfo(Users user) throws SQLException {
        if (user == null) System.out.println("User is not a patron of library");
        else {
            System.out.println("Name: " + user.getName());
            System.out.println("Address: " + user.getAddress());
            System.out.println("Phone number: " + user.getPhoneNumber());
            System.out.println("Status: " + user.getClass().getName());
            System.out.println("ID: " + user.getID());
            System.out.println("Checked out documents: ");
            ResultSet rs = lb.checkedOut(user.getID());
            while (rs.next()) {
                System.out.println("         " + rs.getString("name") + " checked out at " + rs.getString("date"));
            }
            System.out.println();
        }
    }

}
