import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Test3 {

    private static Documents d1;
    private static Documents d2;
    private static Documents d3;
    private static Librarian lb;
    private static Patron p1;
    private static Patron p2;
    private static Patron p3;
    private static Patron s;
    private static Patron v;

    public static void main(String[] args) throws Exception {
        start();
    }

    public static void start() throws Exception {
        lb = new Librarian(1);
        lb.clearDB();
        lb.addDocument("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivestand Clifford Stein", "MIT Press", "2009", 4, 5000, "Third Edition", "Book", "F", "F");
        lb.addDocument("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma, Ralph Johnson, John Vlissides, Richard Helm", "Addison-Wesley Professional ", "2003", 4, 1700, "First Edition", "Book", "T", "F");
        lb.addDocument("Null References: The Billion Dollar Mistake", "Tony Hoare", "", "", 3, 700, "", "AV", "F", "F");
        p1 = new Patron(lb.addUser("Sergey Afonso", "30001", "Via Margutta, 3", "Professor").getInt());
        p2 = new Patron(lb.addUser("Nadia Teixeira", "30002", "Via Sacra, 13", "Professor").getInt());
        p3 = new Patron(lb.addUser("Elvira Espindola", "30003", "Via del Corso, 22", "Professor").getInt());
        s = new Patron(lb.addUser("Andrey Velo", "30004", "Avenida Mazatlan 250", "Student").getInt());
        v = new Patron(lb.addUser("Veronika Rama", "30005", "Stret Atocha, 27", "Visiting Professor").getInt());
        d1 = new Documents("Introduction to Algorithms");
        d2 = new Documents("Design Patterns: Elements of Reusable Object-Oriented Software");
        d3 = new Documents("Null References: The Billion Dollar Mistake");
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
            case 8: TC8();
            case 9: TC9();
            case 10: TC10();
            default: System.exit(0);
        }
    }

    public static void TC1() throws Exception {
        p1.checkOutTest(d1, "2018-03-05");
        p1.checkOutTest(d2, "2018-03-05");
        int copyID = 0;
        IntAndInt fine = p1.checkFineTest(d1, "2018-04-02");
        ResultSet rs = lb.checkedOut(p1.getID());
        while (rs.next()) {
            if (rs.getInt("commonID") == d2.getDocID()) copyID = rs.getInt("copyID");
        }
        lb.returnDoc(copyID);
        System.out.println("For " + p1.getName() + " overdue of " + d1.getName() + " is:");
        System.out.println(fine.getFirst() + " rubles, " + fine.getSecond() + " days");
        System.out.println("=================================");
        assert (fine.getFirst() == 0);
        assert (fine.getSecond() == 0);
        start();
    }

    public static void TC2() throws Exception {
        p1.checkOutTest(d1, "2018-03-05");
        p1.checkOutTest(d2, "2018-03-05");
        s.checkOutTest(d1, "2018-03-05");
        s.checkOutTest(d2, "2018-03-05");
        v.checkOutTest(d1, "2018-03-05");
        v.checkOutTest(d2, "2018-03-05");
        IntAndInt fine = p1.checkFineTest(d1, "2018-04-02");
        System.out.println("For " + p1.getName() + " overdue of " + d1.getName() + " is:");
        System.out.println(fine.getFirst() + " rubles, " + fine.getSecond() + " days");
        System.out.println("=================================");
        assert (fine.getFirst() == 0);
        assert (fine.getSecond() == 0);
        fine = p1.checkFineTest(d2, "2018-04-02");
        System.out.println("For " + p1.getName() + " overdue of " + d2.getName() + " is:");
        System.out.println(fine.getFirst() + " rubles, " + fine.getSecond() + " days");
        System.out.println("=================================");
        assert (fine.getFirst() == 0);
        assert (fine.getSecond() == 0);
        fine = s.checkFineTest(d1, "2018-04-02");
        System.out.println("For " + s.getName() + " overdue of " + d1.getName() + " is:");
        System.out.println(fine.getFirst() + " rubles, " + fine.getSecond() + " days");
        System.out.println("=================================");
        assert (fine.getFirst() == 7);
        assert (fine.getSecond() == 700);
        fine = s.checkFineTest(d2, "2018-04-02");
        System.out.println("For " + s.getName() + " overdue of " + d2.getName() + " is:");
        System.out.println(fine.getFirst() + " rubles, " + fine.getSecond() + " days");
        System.out.println("=================================");
        assert (fine.getFirst() == 14);
        assert (fine.getSecond() == 1400);
        fine = v.checkFineTest(d1, "2018-04-02");
        System.out.println("For " + v.getName() + " overdue of " + d1.getName() + " is:");
        System.out.println(fine.getFirst() + " rubles, " + fine.getSecond() + " days");
        System.out.println("=================================");
        assert (fine.getFirst() == 21);
        assert (fine.getSecond() == 2100);
        fine = v.checkFineTest(d2, "2018-04-02");
        System.out.println("For " + v.getName() + " overdue of " + d2.getName() + " is:");
        System.out.println(fine.getFirst() + " rubles, " + fine.getSecond() + " days");
        System.out.println("=================================");
        assert (fine.getFirst() == 21);
        assert (fine.getSecond() == 1700);
        start();
    }

    public static void TC3() throws Exception {
        p1.checkOutTest(d1, "2018-03-29");
        s.checkOutTest(d2, "2018-03-29");
        v.checkOutTest(d2, "2018-03-29");
        p1.renewTest(d1, "2018-04-02");
        s.renewTest(d2, "2018-04-02");
        v.renewTest(d2, "2018-04-02");
        ResultSet rs = lb.checkedOut(p1.getID());
        while (rs.next()) {
            System.out.println("For " + p1.getName() + " checked out " + rs.getString("name") + " due to " + rs.getString("date"));
            assert (rs.getString("name").equals(d1.getName()));
            assert (rs.getString("date").equals("2018-04-30"));
        }
        System.out.println("=================================");
        rs = lb.checkedOut(s.getID());
        while (rs.next()) {
            System.out.println("For " + s.getName() + " checked out " + rs.getString("name") + " due to " + rs.getString("date"));
            assert (rs.getString("name").equals(d2.getName()));
            assert (rs.getString("date").equals("2018-04-16"));
        }
        System.out.println("=================================");
        rs = lb.checkedOut(v.getID());
        while (rs.next()) {
            System.out.println("For " + v.getName() + " checked out " + rs.getString("name") + " due to " + rs.getString("date"));
            assert (rs.getString("name").equals(d2.getName()));
            assert (rs.getString("date").equals("2018-04-09"));
        }
        System.out.println("=================================");
        start();
    }

    public static void TC4() throws Exception {
        p1.checkOutTest(d1, "2018-03-29");
        s.checkOutTest(d2, "2018-03-29");
        v.checkOutTest(d2, "2018-03-29");
        lb.checkOut(d2);
        ResultSet rs = lb.checkedOut(p1.getID());
        while (rs.next()) {
            System.out.println("For " + p1.getName() + " checked out " + rs.getString("name") + " due to " + rs.getString("date"));
            assert (rs.getString("name").equals(d1.getName()));
            assert (rs.getString("date").equals("2018-04-30"));
        }
        System.out.println("=================================");
        rs = lb.checkedOut(s.getID());
        while (rs.next()) {
            System.out.println("For " + s.getName() + " checked out " + rs.getString("name") + " due to " + rs.getString("date"));
            assert (rs.getString("name").equals(d2.getName()));
            assert (rs.getString("date").equals("2018-04-02"));
        }
        System.out.println("=================================");
        rs = lb.checkedOut(v.getID());
        while (rs.next()) {
            System.out.println("For " + v.getName() + " checked out " + rs.getString("name") + " due to " + rs.getString("date"));
            assert (rs.getString("name").equals(d2.getName()));
            assert (rs.getString("date").equals("2018-04-02"));
        }
        System.out.println("=================================");
        start();

    }

    public static void TC5() throws Exception {
        p1.checkOut(d3);
        s.checkOut(d3);
        v.checkOut(d3);
        int[] wait = p1.getWaitingList(d3.getDocID());
        int i = 0;
        while (i < wait.length) {
            Patron ptr = new Patron(wait[i]);
            System.out.println(ptr.getName() + " waits for " + d3.getName());
            i++;
        }
        System.out.println("=================================");
        assert (wait[0] == v.getID());
        start();
    }

    public static void TC6() throws Exception {
        p1.checkOut(d3);
        p2.checkOut(d3);
        s.checkOut(d3);
        v.checkOut(d3);
        p3.checkOut(d3);
        int[] wait = p1.getWaitingList(d3.getDocID());
        int i = 0;
        while (i < wait.length) {
            Patron ptr = new Patron(wait[i]);
            System.out.println(ptr.getName() + " waits for " + d3.getName());
            i++;
        }
        System.out.println("=================================");
        assert (wait[0] == s.getID());
        assert (wait[1] == v.getID());
        assert (wait[2] == p3.getID());
        start();
    }

    public static void TC7() throws SQLException {
        p1.checkOut(d3);
        p2.checkOut(d3);
        s.checkOut(d3);
        v.checkOut(d3);
        p3.checkOut(d3);
        int[] wait = p1.getWaitingList(d3.getDocID());
        int i = 0;
        while (i < wait.length) {
            Patron ptr = new Patron(wait[i]);
            System.out.println(ptr.getName() + " waits for " + d3.getName());
            i++;
        }
        System.out.println("=================================");
        assert (wait[0] == s.getID());
        assert (wait[1] == v.getID());
        assert (wait[2] == p3.getID());
        lb.checkOut(d3);
        wait = p1.getWaitingList(d3.getDocID());
        i = 0;
        while (i < wait.length) {
            Patron ptr = new Patron(wait[i]);
            System.out.println(ptr.getName() + " waits for " + d3.getName());
            i++;
        }
        System.out.println("=================================");
        assert (wait[0] == 0);
        Documents [] notes = s.getBadNotifications();
        i = 0;
        while (i < notes.length) {
            System.out.println(s.getName() + " was notified that " + notes[i].getName() + " was deleted");
            i++;
        }
        System.out.println("=================================");
        assert (notes[0].getDocID() == d3.getDocID());
        notes = v.getBadNotifications();
        i = 0;
        while (i < notes.length) {
            System.out.println(v.getName() + " was notified that " + notes[i].getName() + " was deleted");
            i++;
        }
        System.out.println("=================================");
        assert (notes[0].getDocID() == d3.getDocID());
        notes = p3.getBadNotifications();
        i = 0;
        while (i < notes.length) {
            System.out.println(p3.getName() + " was notified that " + notes[i].getName() + " was deleted");
            i++;
        }
        System.out.println("=================================");
        assert (notes[0].getDocID() == d3.getDocID());
    }

    public static void TC8() throws Exception {
        p1.checkOut(d3);
        p2.checkOut(d3);
        s.checkOut(d3);
        v.checkOut(d3);
        p3.checkOut(d3);
        int[] wait = p1.getWaitingList(d3.getDocID());
        int i = 0;
        while (i < wait.length) {
            Patron ptr = new Patron(wait[i]);
            System.out.println(ptr.getName() + " waits for " + d3.getName());
            i++;
        }
        System.out.println("=================================");
        assert (wait[0] == s.getID());
        assert (wait[1] == v.getID());
        assert (wait[2] == p3.getID());
        ResultSet chout = lb.checkedOut(p2.getID());
        int copyID = 0;
        while (chout.next()) {
            if (chout.getInt("commonID") == d3.getDocID()) copyID = chout.getInt("copyID");
        }
        lb.returnDoc(copyID);
        Documents[] docs = s.getNotifications();
        i = 0;
        while (i < docs.length) {
            System.out.println(s.getName() + " was notified that " + docs[i].getName() + " is available");
            i++;
        }
        System.out.println("=================================");
        assert (docs[0].getDocID() == d3.getDocID());
        chout = lb.checkedOut(p2.getID());
        assert (!chout.next());
        wait = p2.getWaitingList(d3.getDocID());
        while (i < wait.length) {
            Patron ptr = new Patron(wait[i]);
            System.out.println(ptr.getName() + " waits for " + d3.getName());
            i++;
        }
        System.out.println("=================================");
        assert (wait[0] == s.getID());
        assert (wait[1] == v.getID());
        assert (wait[2] == p3.getID());
        start();
    }

    public static void TC9() throws SQLException {
        p1.checkOut(d3);
        p2.checkOut(d3);
        s.checkOut(d3);
        v.checkOut(d3);
        p3.checkOut(d3);
        int[] wait = p1.getWaitingList(d3.getDocID());
        int i = 0;
        while (i < wait.length) {
            Patron ptr = new Patron(wait[i]);
            System.out.println(ptr.getName() + " waits for " + d3.getName());
            i++;
        }
        System.out.println("=================================");
        assert (wait[0] == s.getID());
        assert (wait[1] == v.getID());
        assert (wait[2] == p3.getID());
        p1.renewTest(d3, "2018-04-02");
        ResultSet rs = lb.checkedOut(p1.getID());
        while (rs.next()) {
            System.out.println("For " + p1.getName() + " checked out " + rs.getString("name") + " due to " + rs.getString("date"));
            assert (rs.getString("name").equals(d3.getName()));
            assert (rs.getString("date").equals("2018-04-30"));
        }
        System.out.println("=================================");
        wait = p1.getWaitingList(d3.getDocID());
        i = 0;
        while (i < wait.length) {
            Patron ptr = new Patron(wait[i]);
            System.out.println(ptr.getName() + " waits for " + d3.getName());
            i++;
        }
        System.out.println("=================================");
        assert (wait[0] == s.getID());
        assert (wait[1] == v.getID());
        assert (wait[2] == p3.getID());
    }

    public static void TC10() throws SQLException {
        p1.checkOutTest(d1, "2018-03-26");
        p1.renewTest(d1, "2018-03-29");
        v.checkOutTest(d1, "2018-03-26");
        v.renewTest(d1, "2018-03-29");
        p1.renewTest(d1, "2018-04-02");
        v.renewTest(d1, "2018-04-02");
        ResultSet rs = lb.checkedOut(p1.getID());
        while (rs.next()) {
            System.out.println(p1.getName() + " checked out " + rs.getString("name") + " due to " + rs.getString("date"));
            assert (rs.getString("name").equals(d1.getName()));
            assert (rs.getString("date").equals("2018-04-26"));
        }
        System.out.println("=================================");
        rs = lb.checkedOut(v.getID());
        while (rs.next()) {
            System.out.println(v.getName() + " checked out " + rs.getString("name") + " due to " + rs.getString("date"));
            assert (rs.getString("name").equals(d1.getName()));
            assert (rs.getString("date").equals("2018-04-05"));
        }
        System.out.println("=================================");
    }

}
