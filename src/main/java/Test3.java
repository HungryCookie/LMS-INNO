/*
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

        d1 = new Documents("Introduction to Algorithms");
        d2 = new Documents("Design Patterns: Elements of Reusable Object-Oriented Software");
        d3 = new Documents("Null References: The Billion Dollar Mistake");
        for (int i = 1; i < 11; i++) {
        switch (i) {
            case 1: {
                TC1();
                break;
            }
            case 2: {
                TC2();
                break;
            }
            case 3: {
                TC3();
                break;
            }
            case 4: {
                TC4();
                break;
            }
            case 5: {
                TC5();
                break;
            }
            case 6: {
                TC6();
                break;
            }
            case 7: {
                TC7();
                break;
            }
            case 8: {
                TC8();
                break;
            }
            case 9: {
                TC9();
                break;
            }
            case 10: {
                TC10();
                break;
            }
            default:
                System.exit(0);
            }
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
        }
    }

    public static void TC1() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 1: ");
        System.out.println();
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
    }

    public static void TC2() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 2: ");
        System.out.println();
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
    }

    public static void TC3() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 3: ");
        System.out.println();
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
    }

    public static void TC4() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 4: ");
        System.out.println();
        p1.checkOutTest(d1, "2018-03-29");
        s.checkOutTest(d2, "2018-03-29");
        v.checkOutTest(d2, "2018-03-29");
        lb.outstandingRequestTest(d2, "2018-04-02");
        p1.renewTest(d1, "2018-04-02");
        s.renewTest(d2, "2018-04-02");
        v.renewTest(d2, "2018-04-02");
//        Documents[] ret = s.getHaveToReturnNotification();
//        ret = v.getHaveToReturnNotification();
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
    }

    public static void TC5() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 5: ");
        System.out.println();
        p1.checkOut(d3);
        s.checkOut(d3);
        v.checkOut(d3);
        int[] wait = p1.getWaitingList(d3.getDocID());
        int i = 0;
        System.out.println("Waiting list:");
        while (i < wait.length) {
            Patron ptr = new Patron(wait[i]);
            System.out.println(ptr.getName() + " waits for " + d3.getName());
            i++;
        }
        System.out.println("=================================");
        assert (wait[0] == v.getID());
    }

    public static void TC6() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 6: ");
        System.out.println();
        p1.checkOut(d3);
        p2.checkOut(d3);
        s.checkOut(d3);
        v.checkOut(d3);
        p3.checkOut(d3);
        int[] wait = p1.getWaitingList(d3.getDocID());
        int i = 0;
        System.out.println("Waiting list:");
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

    public static void TC7() throws SQLException {
        System.out.println("******************************************");
        System.out.println("Test case 7: ");
        System.out.println();
        p1.checkOut(d3);
        p2.checkOut(d3);
        s.checkOut(d3);
        v.checkOut(d3);
        p3.checkOut(d3);
        lb.outstandingRequestTest(d3, "2018-04-02");
        int[] wait = p1.getWaitingList(d3.getDocID());
        int i = 0;
        System.out.println("Waiting list: ");
        while (i < wait.length) {
            Patron ptr = new Patron(wait[i]);
            System.out.println(ptr.getName() + " waits for " + d3.getName());
            i++;
        }
        System.out.println("=================================");
        assert (wait.length == 0);
        Documents [] notes = p1.getHaveToReturnNotifications(true);
        i = 0;
        while (i < notes.length) {
            System.out.println(p1.getName() + " was notified that " + notes[i].getName() + " should be returned");
            i++;
        }
        System.out.println("=================================");
        assert (notes[0].getDocID() == d3.getDocID());
        notes = p2.getHaveToReturnNotifications(true);
        i = 0;
        while (i < notes.length) {
            System.out.println(p2.getName() + " was notified that " + notes[i].getName() + " should be returned");
            i++;
        }
        System.out.println("=================================");
        assert (notes[0].getDocID() == d3.getDocID());
        notes = s.getFailedBookingNotifications(true);
        i = 0;
        while (i < notes.length) {
            System.out.println(s.getName() + " was notified that " + notes[i].getName() + " was deleted");
            i++;
        }
        System.out.println("=================================");
        assert (notes[0].getDocID() == d3.getDocID());
        notes = v.getFailedBookingNotifications(true);
        i = 0;
        while (i < notes.length) {
            System.out.println(v.getName() + " was notified that " + notes[i].getName() + " was deleted");
            i++;
        }
        System.out.println("=================================");
        assert (notes[0].getDocID() == d3.getDocID());
        notes = p3.getFailedBookingNotifications(true);
        i = 0;
        while (i < notes.length) {
            System.out.println(p3.getName() + " was notified that " + notes[i].getName() + " was deleted");
            i++;
        }
        System.out.println("=================================");
        assert (notes[0].getDocID() == d3.getDocID());
    }

    public static void TC8() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 8: ");
        System.out.println();
        p1.checkOut(d3);
        p2.checkOut(d3);
        s.checkOut(d3);
        v.checkOut(d3);
        p3.checkOut(d3);
        ResultSet chout = lb.checkedOut(p2.getID());
        int copyID = 0;
        while (chout.next()) {
            if (chout.getInt("commonID") == d3.getDocID()) copyID = chout.getInt("copyID");
        }
        lb.returnDoc(copyID);
        Documents[] docs = s.getNotifications(true);
        int i = 0;
        while (i < docs.length) {
            System.out.println(s.getName() + " was notified that " + docs[i].getName() + " is available");
            i++;
        }
        System.out.println("=================================");
        assert (docs[0].getDocID() == d3.getDocID());
        chout = lb.checkedOut(p2.getID());
        while (chout.next()) {
            System.out.println(p2.getName() + " checked out " + chout.getString("name") + " due to " + chout.getString("date"));
        }
        int[] wait = p2.getWaitingList(d3.getDocID());
        System.out.println("Waiting list: ");
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

    public static void TC9() throws SQLException {
        System.out.println("******************************************");
        System.out.println("Test case 9: ");
        System.out.println();
        p1.checkOut(d3);
        p2.checkOut(d3);
        s.checkOut(d3);
        v.checkOut(d3);
        p3.checkOut(d3);
        p1.renewTest(d3, "2018-04-02");
        ResultSet rs = lb.checkedOut(p1.getID());
        while (rs.next()) {
            System.out.println("For " + p1.getName() + " checked out " + rs.getString("name") + " due to " + rs.getString("date"));
            assert (rs.getString("name").equals(d3.getName()));
            assert (rs.getString("date").equals("2018-04-30"));
        }
        System.out.println("=================================");
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
    }

    public static void TC10() throws SQLException {
        System.out.println("******************************************");
        System.out.println("Test case 10: ");
        System.out.println();
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
*/
