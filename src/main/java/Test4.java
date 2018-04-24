import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Test4 {

    private static Admin adm;
    private static Documents d1;
    private static Documents d2;
    private static Documents d3;
    private static Librarian1 lb1;
    private static Librarian2 lb2;
    private static Librarian3 lb3;
    private static FcukBase fb = new FcukBase();
    private static Patron p1;
    private static Patron p2;
    private static Patron p3;
    private static Patron s;
    private static Patron v;

    public static void main(String[] args) throws Exception {
        start();
    }

    public static void start() throws Exception {
        adm = new Admin(1);
        lb3 = new Librarian3(3);
        d1 = new Documents("Introduction to Algorithms");
        d2 = new Documents("Design Patterns: Elements of Reusable Object-Oriented Software");
        d3 = new Documents("Null References: The Billion Dollar Mistake");
        lb3.clearDB();
        lb3.addDocument("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivestand Clifford Stein", "MIT Press", "2009", 4, 5000, "Third Edition", "Book", "F", "F", "");
        lb3.addDocument("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma, Ralph Johnson, John Vlissides, Richard Helm", "Addison-Wesley Professional ", "2003", 4, 1700, "First Edition", "Book", "T", "F", "");
        lb3.addDocument("Null References: The Billion Dollar Mistake", "Tony Hoare", "", "", 3, 700, "", "AV", "F", "F", "");
        p1 = new Patron(lb3.addUser("Sergey Afonso", "30001", "Via Margutta, 3", "Professor").getInt());
        p2 = new Patron(lb3.addUser("Nadia Teixeira", "30002", "Via Sacra, 13", "Professor").getInt());
        p3 = new Patron(lb3.addUser("Elvira Espindola", "30003", "Via del Corso, 22", "Professor").getInt());
        s = new Patron(lb3.addUser("Andrey Velo", "30004", "Avenida Mazatlan 250", "Student").getInt());
        v = new Patron(lb3.addUser("Veronika Rama", "30005", "Stret Atocha, 27", "Visiting Professor").getInt());
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
            lb3.clearDB();
            lb3.addDocument("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivestand Clifford Stein", "MIT Press", "2009", 4, 5000, "Third Edition", "Book", "F", "F", "");
            lb3.addDocument("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma, Ralph Johnson, John Vlissides, Richard Helm", "Addison-Wesley Professional ", "2003", 4, 1700, "First Edition", "Book", "T", "F", "");
            lb3.addDocument("Null References: The Billion Dollar Mistake", "Tony Hoare", "", "", 3, 700, "", "AV", "F", "F", "");
            p1 = new Patron(lb3.addUser("Sergey Afonso", "30001", "Via Margutta, 3", "Professor").getInt());
            p2 = new Patron(lb3.addUser("Nadia Teixeira", "30002", "Via Sacra, 13", "Professor").getInt());
            p3 = new Patron(lb3.addUser("Elvira Espindola", "30003", "Via del Corso, 22", "Professor").getInt());
            s = new Patron(lb3.addUser("Andrey Velo", "30004", "Avenida Mazatlan 250", "Student").getInt());
            v = new Patron(lb3.addUser("Veronika Rama", "30005", "Stret Atocha, 27", "Visiting Professor").getInt());
            d1 = new Documents("Introduction to Algorithms");
            d2 = new Documents("Design Patterns: Elements of Reusable Object-Oriented Software");
            d3 = new Documents("Null References: The Billion Dollar Mistake");
        }
    }

    public static void TC1() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 1: ");
        System.out.println();
    }

    public static void TC2() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 2: ");
        System.out.println();
        lb1 = new Librarian1(adm.addLibrarian("Eugenia Rama", "123456789", "Street", "Librarian1").getInt());
        lb2 = new Librarian2(adm.addLibrarian("Luie Ramos", "987654321", "Next Street", "Librarian2").getInt());
        lb3 = new Librarian3(adm.addLibrarian("Ramon Valdez", "456789123", "Another Street", "Librarian3").getInt());
        adm.addLog(adm.getName() + " created Librarian1 " + lb1.getName(), "");
        adm.addLog(adm.getName() + " created Librarian2 " + lb2.getName(), "");
        adm.addLog(adm.getName() + " created Librarian3 " + lb3.getName(), "");
        ResultSet rs = fb.getUsers();
        int users = 0;
        System.out.println("-------------------------------------------");
        while (rs.next()) {
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Status: " + rs.getString("status"));
            System.out.println();
            users++;
        }
        assert users == 4;
    }

    public static void TC3() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 3: ");
        System.out.println();
        lb1 = new Librarian1(adm.addLibrarian("Eugenia Rama", "123456789", "Street", "Librarian1").getInt());
        lb2 = new Librarian2(adm.addLibrarian("Luie Ramos", "987654321", "Next Street", "Librarian2").getInt());
        lb3 = new Librarian3(adm.addLibrarian("Ramon Valdez", "456789123", "Another Street", "Librarian3").getInt());
        adm.addLog(adm.getName() + " created Librarian1 " + lb1.getName(), "");
        adm.addLog(adm.getName() + " created Librarian2 " + lb2.getName(), "");
        adm.addLog(adm.getName() + " created Librarian3 " + lb3.getName(), "");
        try {
            ((Librarian2)lb1).addDocument("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivestand Clifford Stein", "MIT Press", "2009", 4, 5000, "Third Edition", "Book", "F", "F", "Algorithms, Data Structures, Complexity, Computational Theory");
            ((Librarian2)lb1).addDocument("Algorithms + Data Structures = Programs", "Niklaus Wirth", "Prentice Hall PTR", "1978", 4, 5000, "First Edition", "Book", "F", "F", "Algorithms, Data Structures, Search Algorithms, Pascal");
            ((Librarian2)lb1).addDocument("The Art of Computer Programming", "Donald E. Knuth", " Addison Wesley Longman Publishing Co., Inc.", "1997", 4, 5000, "Third Edition",  "Book","F", "F", "Algorithms, Combinatorial Algorithms, Recursion");
        }catch (Exception e)  {
            System.out.println("Impossible to create the document for " + lb1.getName());
        }
        ResultSet rs = fb.getDocs();
        int docs = 0;
        while (rs.next()) {
            System.out.println(rs.getString("name"));
            docs++;
        }
        assert docs == 0;
    }

    public static void TC4() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 4: ");
        System.out.println();
        //Test Case 2
        lb1 = new Librarian1(adm.addLibrarian("Eugenia Rama", "123456789", "Street", "Librarian1").getInt());
        lb2 = new Librarian2(adm.addLibrarian("Luie Ramos", "987654321", "Next Street", "Librarian2").getInt());
        lb3 = new Librarian3(adm.addLibrarian("Ramon Valdez", "456789123", "Another Street", "Librarian3").getInt());
        adm.addLog(adm.getName() + " created Librarian1 " + lb1.getName(), "");
        adm.addLog(adm.getName() + " created Librarian2 " + lb2.getName(), "");
        adm.addLog(adm.getName() + " created Librarian3 " + lb3.getName(), "");
        //Continue
        lb2.addDocument("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivestand Clifford Stein", "MIT Press", "2009", 4, 5000, "Third Edition", "Book", "F", "F", "Algorithms, Data Structures, Complexity, Computational Theory");
        lb2.addDocument("Algorithms + Data Structures = Programs", "Niklaus Wirth", "Prentice Hall PTR", "1978", 4, 5000, "First Edition", "Book", "F", "F", "Algorithms, Data Structures, Search Algorithms, Pascal");
        lb2.addDocument("The Art of Computer Programming", "Donald E. Knuth", " Addison Wesley Longman Publishing Co., Inc.", "1997", 4, 5000, "Third Edition",  "Book","F", "F", "Algorithms, Combinatorial Algorithms, Recursion");
        s = new Patron(lb2.addUser("Andrey Velo", "30004", "Avenida Mazatlan 250", "Student").getInt());
        p1 = new Patron(lb2.addUser("Sergey Afonso", "30001", "Via Margutta, 3", "Professor").getInt());
        p2 = new Patron(lb2.addUser("Nadia Teixeira", "30002", "Via Sacra, 13", "Professor").getInt());
        p3 = new Patron(lb2.addUser("Elvira Espindola", "30003", "Via del Corso, 22", "Professor").getInt());
        v = new Patron(lb2.addUser("Veronika Rama", "30005", "Stret Atocha, 27", "Visiting Professor").getInt());
        adm.addLog(lb2.getName() + " added document Introduction to Algorithms", "");
        adm.addLog(lb2.getName() + " added document Algorithms + Data Structures = Programs", "");
        adm.addLog(lb2.getName() + " added document The Art of Computer Programming", "");
        ResultSet rs = fb.getDocs();
        int docs = 0;
        System.out.println("Books in the system: ");
        System.out.println("----");
        while (rs.next()) {
            System.out.println(rs.getString("name"));
            System.out.println();
            docs++;
        }
        assert docs == 3;
        System.out.println("=====================");
        System.out.println("Users in the system: ");
        System.out.println("----");
        rs = fb.getUsers();
        int users = 0;
        while (rs.next()) {
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Status: " + rs.getString("status"));
            System.out.println();
            users++;
        }
        assert users == 4;
    }

    public static void TC5() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 5: ");
        System.out.println();
        //Test Case 2
        lb1 = new Librarian1(adm.addLibrarian("Eugenia Rama", "123456789", "Street", "Librarian1").getInt());
        lb2 = new Librarian2(adm.addLibrarian("Luie Ramos", "987654321", "Next Street", "Librarian2").getInt());
        lb3 = new Librarian3(adm.addLibrarian("Ramon Valdez", "456789123", "Another Street", "Librarian3").getInt());
        adm.addLog(adm.getName() + " created Librarian1 " + lb1.getName(), "");
        adm.addLog(adm.getName() + " created Librarian2 " + lb2.getName(), "");
        adm.addLog(adm.getName() + " created Librarian3 " + lb3.getName(), "");
        //Test Case 4
        lb2.addDocument("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivestand Clifford Stein", "MIT Press", "2009", 4, 5000, "Third Edition", "Book", "F", "F", "Algorithms, Data Structures, Complexity, Computational Theory");
        lb2.addDocument("Algorithms + Data Structures = Programs", "Niklaus Wirth", "Prentice Hall PTR", "1978", 4, 5000, "First Edition", "Book", "F", "F", "Algorithms, Data Structures, Search Algorithms, Pascal");
        lb2.addDocument("The Art of Computer Programming", "Donald E. Knuth", " Addison Wesley Longman Publishing Co., Inc.", "1997", 4, 5000, "Third Edition",  "Book","F", "F", "Algorithms, Combinatorial Algorithms, Recursion");
        s = new Patron(lb2.addUser("Andrey Velo", "30004", "Avenida Mazatlan 250", "Student").getInt());
        p1 = new Patron(lb2.addUser("Sergey Afonso", "30001", "Via Margutta, 3", "Professor").getInt());
        p2 = new Patron(lb2.addUser("Nadia Teixeira", "30002", "Via Sacra, 13", "Professor").getInt());
        p3 = new Patron(lb2.addUser("Elvira Espindola", "30003", "Via del Corso, 22", "Professor").getInt());
        v = new Patron(lb2.addUser("Veronika Rama", "30005", "Stret Atocha, 27", "Visiting Professor").getInt());
        adm.addLog(lb2.getName() + " added document Introduction to Algorithms", "");
        adm.addLog(lb2.getName() + " added document Algorithms + Data Structures = Programs", "");
        adm.addLog(lb2.getName() + " added document The Art of Computer Programming", "");
        d1 = new Documents("Introduction to Algorithms");
        d2 = new Documents("Algorithms + Data Structures = Programs");
        d3 = new Documents("The Art of Computer Programming");
        lb2.modify(d1.getDocID(), d1.getName(), d1.getAuthor(), d1.getPublisher(), d1.getYear(), d1.getCounter() - 1, d1.getCost(), d1.getEdition(), d1.getType(), "F", "F", d1.getKeywords());
        System.out.println("Number of copies after action (except from reference): " + (d1.getCounter() - 1));
        adm.addLog(lb2.getName() + " deleted copy of " + d1.getName(), "");
        assert d1.getCounter() == 3;
    }

    public static void TC6() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 6: ");
        System.out.println();
        //Test Case 2
        lb1 = new Librarian1(adm.addLibrarian("Eugenia Rama", "123456789", "Street", "Librarian1").getInt());
        lb2 = new Librarian2(adm.addLibrarian("Luie Ramos", "987654321", "Next Street", "Librarian2").getInt());
        lb3 = new Librarian3(adm.addLibrarian("Ramon Valdez", "456789123", "Another Street", "Librarian3").getInt());
        adm.addLog(adm.getName() + " created Librarian1 " + lb1.getName(), "");
        adm.addLog(adm.getName() + " created Librarian2 " + lb2.getName(), "");
        adm.addLog(adm.getName() + " created Librarian3 " + lb3.getName(), "");
        //Continue
        lb2.addDocument("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivestand Clifford Stein", "MIT Press", "2009", 4, 5000, "Third Edition", "Book", "F", "F", "Algorithms, Data Structures, Complexity, Computational Theory");
        lb2.addDocument("Algorithms + Data Structures = Programs", "Niklaus Wirth", "Prentice Hall PTR", "1978", 4, 5000, "First Edition", "Book", "F", "F", "Algorithms, Data Structures, Search Algorithms, Pascal");
        lb2.addDocument("The Art of Computer Programming", "Donald E. Knuth", " Addison Wesley Longman Publishing Co., Inc.", "1997", 4, 5000, "Third Edition",  "Book","F", "F", "Algorithms, Combinatorial Algorithms, Recursion");
        s = new Patron(lb2.addUser("Andrey Velo", "30004", "Avenida Mazatlan 250", "Student").getInt());
        p1 = new Patron(lb2.addUser("Sergey Afonso", "30001", "Via Margutta, 3", "Professor").getInt());
        p2 = new Patron(lb2.addUser("Nadia Teixeira", "30002", "Via Sacra, 13", "Professor").getInt());
        p3 = new Patron(lb2.addUser("Elvira Espindola", "30003", "Via del Corso, 22", "Professor").getInt());
        v = new Patron(lb2.addUser("Veronika Rama", "30005", "Stret Atocha, 27", "Visiting Professor").getInt());
        adm.addLog(lb2.getName() + " added document Introduction to Algorithms", "");
        adm.addLog(lb2.getName() + " added document Algorithms + Data Structures = Programs", "");
        adm.addLog(lb2.getName() + " added document The Art of Computer Programming", "");
        p1.checkOut(d3);
        p2.checkOut(d3);
        s.checkOut(d3);
        adm.addLog(p1.getName() + " checked out document " + d3.getName(), "");
        adm.addLog(p2.getName() + " checked out document " + d3.getName(), "");
        adm.addLog(s.getName() + " checked out document " + d3.getName(), "");
        v.checkOut(d3);
        p3.checkOut(d3);
        adm.addLog(v.getName() + " checked out document  " + d3.getName(), "");
        adm.addLog(p3.getName() + " checked out document  " + d3.getName(), "");
        if (!lb1.getStatus().equals("Librarian3"))
            System.out.println("Impossible to place outstanding request for " + lb1.getName());
        else
            lb1.outstandingRequest(d3);
        assert p1.getBadNotifications().length == 0;
    }

    public static void TC7() throws SQLException {
        System.out.println("******************************************");
        System.out.println("Test case 7: ");
        System.out.println();
        //Test Case 2
        lb1 = new Librarian1(adm.addLibrarian("Eugenia Rama", "123456789", "Street", "Librarian1").getInt());
        lb2 = new Librarian2(adm.addLibrarian("Luie Ramos", "987654321", "Next Street", "Librarian2").getInt());
        lb3 = new Librarian3(adm.addLibrarian("Ramon Valdez", "456789123", "Another Street", "Librarian3").getInt());
        adm.addLog(adm.getName() + " created Librarian1 " + lb1.getName(), "");
        adm.addLog(adm.getName() + " created Librarian2 " + lb2.getName(), "");
        adm.addLog(adm.getName() + " created Librarian3 " + lb3.getName(), "");
        //Continue
        lb2.addDocument("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivestand Clifford Stein", "MIT Press", "2009", 4, 5000, "Third Edition", "Book", "F", "F", "Algorithms, Data Structures, Complexity, Computational Theory");
        lb2.addDocument("Algorithms + Data Structures = Programs", "Niklaus Wirth", "Prentice Hall PTR", "1978", 4, 5000, "First Edition", "Book", "F", "F", "Algorithms, Data Structures, Search Algorithms, Pascal");
        lb2.addDocument("The Art of Computer Programming", "Donald E. Knuth", " Addison Wesley Longman Publishing Co., Inc.", "1997", 4, 5000, "Third Edition",  "Book","F", "F", "Algorithms, Combinatorial Algorithms, Recursion");
        s = new Patron(lb2.addUser("Andrey Velo", "30004", "Avenida Mazatlan 250", "Student").getInt());
        p1 = new Patron(lb2.addUser("Sergey Afonso", "30001", "Via Margutta, 3", "Professor").getInt());
        p2 = new Patron(lb2.addUser("Nadia Teixeira", "30002", "Via Sacra, 13", "Professor").getInt());
        p3 = new Patron(lb2.addUser("Elvira Espindola", "30003", "Via del Corso, 22", "Professor").getInt());
        v = new Patron(lb2.addUser("Veronika Rama", "30005", "Stret Atocha, 27", "Visiting Professor").getInt());
        adm.addLog(lb2.getName() + " added document Introduction to Algorithms", "");
        adm.addLog(lb2.getName() + " added document Algorithms + Data Structures = Programs", "");
        adm.addLog(lb2.getName() + " added document The Art of Computer Programming", "");
        p1.checkOut(d3);
        p2.checkOut(d3);
        s.checkOut(d3);
        v.checkOut(d3);
        p3.checkOut(d3);
        adm.addLog(p1.getName() + " checked out document " + d3.getName(), "");
        adm.addLog(p2.getName() + " checked out document " + d3.getName(), "");
        adm.addLog(s.getName() + " checked out document " + d3.getName(), "");
        adm.addLog(v.getName() + " checked out document " + d3.getName(), "");
        adm.addLog(p3.getName() + " checked out document  " + d3.getName(), "");
        lb3.outstandingRequest(d3);
        adm.addLog(lb3.getName() + " placed the outstanding request for " + d3.getName(), "");
        System.out.println("Waiting list for " + d3.getName() + ":");
        System.out.println();
        int[] wait = lb3.getWaitingList(d3.getDocID());
        for (int i = 0; i < wait.length; i++) {
            Patron ptr = new Patron(wait[i]);
            System.out.println(ptr.getName() + " waits for " + d3.getName());
        }
        assert wait.length == 0;
        System.out.println("=========================");
        Documents[] notifications = p1.getHaveToReturnNotifications();
        for (int i = 0; i < notifications.length; i++) {
            System.out.println(p1.getName() + " was notified to returned book " + notifications[i].getName());
        }
        assert notifications.length == 1;
        notifications = p2.getHaveToReturnNotifications();
        for (int i = 0; i < notifications.length; i++) {
            System.out.println(p2.getName() + " was notified to returned book " + notifications[i].getName());
        }
        assert notifications.length == 1;
        notifications = s.getHaveToReturnNotifications();
        for (int i = 0; i < notifications.length; i++) {
            System.out.println(s.getName() + " was notified to returned book " + notifications[i].getName());
        }
        assert notifications.length == 1;
        notifications = v.getFailedBookingNotifications();
        for (int i = 0; i < notifications.length; i++) {
            System.out.println(v.getName() + " was notified that he/she was deleted from waiting list for " + notifications[i].getName());
        }
        assert notifications.length == 1;
        notifications = p3.getFailedBookingNotifications();
        for (int i = 0; i < notifications.length; i++) {
            System.out.println(p3.getName() + " was notified that he/she was deleted from waiting list for " + notifications[i].getName());
        }
    }

    public static void TC8() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 8: ");
        System.out.println();
        //Test Case 2
        lb1 = new Librarian1(adm.addLibrarian("Eugenia Rama", "123456789", "Street", "Librarian1").getInt());
        lb2 = new Librarian2(adm.addLibrarian("Luie Ramos", "987654321", "Next Street", "Librarian2").getInt());
        lb3 = new Librarian3(adm.addLibrarian("Ramon Valdez", "456789123", "Another Street", "Librarian3").getInt());
        adm.addLog(adm.getName() + " created Librarian1 " + lb1.getName(), "");
        adm.addLog(adm.getName() + " created Librarian2 " + lb2.getName(), "");
        adm.addLog(adm.getName() + " created Librarian3 " + lb3.getName(), "");
        //Continue
        lb2.addDocument("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivestand Clifford Stein", "MIT Press", "2009", 4, 5000, "Third Edition", "Book", "F", "F", "Algorithms, Data Structures, Complexity, Computational Theory");
        lb2.addDocument("Algorithms + Data Structures = Programs", "Niklaus Wirth", "Prentice Hall PTR", "1978", 4, 5000, "First Edition", "Book", "F", "F", "Algorithms, Data Structures, Search Algorithms, Pascal");
        lb2.addDocument("The Art of Computer Programming", "Donald E. Knuth", " Addison Wesley Longman Publishing Co., Inc.", "1997", 4, 5000, "Third Edition",  "Book","F", "F", "Algorithms, Combinatorial Algorithms, Recursion");
        s = new Patron(lb2.addUser("Andrey Velo", "30004", "Avenida Mazatlan 250", "Student").getInt());
        p1 = new Patron(lb2.addUser("Sergey Afonso", "30001", "Via Margutta, 3", "Professor").getInt());
        p2 = new Patron(lb2.addUser("Nadia Teixeira", "30002", "Via Sacra, 13", "Professor").getInt());
        p3 = new Patron(lb2.addUser("Elvira Espindola", "30003", "Via del Corso, 22", "Professor").getInt());
        v = new Patron(lb2.addUser("Veronika Rama", "30005", "Stret Atocha, 27", "Visiting Professor").getInt());
        adm.addLog(lb2.getName() + " added document Introduction to Algorithms", "");
        adm.addLog(lb2.getName() + " added document Algorithms + Data Structures = Programs", "");
        adm.addLog(lb2.getName() + " added document The Art of Computer Programming", "");
        p1.checkOut(d3);
        p2.checkOut(d3);
        s.checkOut(d3);
        adm.addLog(p1.getName() + " checked out document " + d3.getName(), "");
        adm.addLog(p2.getName() + " checked out document " + d3.getName(), "");
        adm.addLog(s.getName() + " checked out document " + d3.getName(), "");
        v.checkOut(d3);
        p3.checkOut(d3);
        adm.addLog(v.getName() + " checked out document  " + d3.getName(), "");
        adm.addLog(p3.getName() + " checked out document  " + d3.getName(), "");
        if (!lb1.getStatus().equals("Librarian3"))
            System.out.println("Impossible to place outstanding request for " + lb1.getName());
        else {
            lb1.outstandingRequest(d3);
            adm.addLog(lb1.getName() + " failed to place outstanding request for " + d3.getName(), "");
        }
        assert p1.getBadNotifications().length == 0;
        String [][] logs = adm.getLogs();
        System.out.println("Current logs: ");
        System.out.println();
        for (int i = 0; i < logs.length; i++) {
            System.out.println("Date: " + logs[i][1]);
            System.out.println(logs[i][0]);
            System.out.println();
        }
        assert logs.length == 14;
    }

    public static void TC9() throws Exception {
        System.out.println("******************************************");
        System.out.println("Test case 9: ");
        System.out.println();
        //Test Case 2
        lb1 = new Librarian1(adm.addLibrarian("Eugenia Rama", "123456789", "Street", "Librarian1").getInt());
        lb2 = new Librarian2(adm.addLibrarian("Luie Ramos", "987654321", "Next Street", "Librarian2").getInt());
        lb3 = new Librarian3(adm.addLibrarian("Ramon Valdez", "456789123", "Another Street", "Librarian3").getInt());
        adm.addLog(adm.getName() + " created Librarian1 " + lb1.getName(), "");
        adm.addLog(adm.getName() + " created Librarian2 " + lb2.getName(), "");
        adm.addLog(adm.getName() + " created Librarian3 " + lb3.getName(), "");
        //Continue
        lb2.addDocument("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivestand Clifford Stein", "MIT Press", "2009", 4, 5000, "Third Edition", "Book", "F", "F", "Algorithms, Data Structures, Complexity, Computational Theory");
        lb2.addDocument("Algorithms + Data Structures = Programs", "Niklaus Wirth", "Prentice Hall PTR", "1978", 4, 5000, "First Edition", "Book", "F", "F", "Algorithms, Data Structures, Search Algorithms, Pascal");
        lb2.addDocument("The Art of Computer Programming", "Donald E. Knuth", " Addison Wesley Longman Publishing Co., Inc.", "1997", 4, 5000, "Third Edition",  "Book","F", "F", "Algorithms, Combinatorial Algorithms, Recursion");
        s = new Patron(lb2.addUser("Andrey Velo", "30004", "Avenida Mazatlan 250", "Student").getInt());
        p1 = new Patron(lb2.addUser("Sergey Afonso", "30001", "Via Margutta, 3", "Professor").getInt());
        p2 = new Patron(lb2.addUser("Nadia Teixeira", "30002", "Via Sacra, 13", "Professor").getInt());
        p3 = new Patron(lb2.addUser("Elvira Espindola", "30003", "Via del Corso, 22", "Professor").getInt());
        v = new Patron(lb2.addUser("Veronika Rama", "30005", "Stret Atocha, 27", "Visiting Professor").getInt());
        adm.addLog(lb2.getName() + " added document Introduction to Algorithms", "");
        adm.addLog(lb2.getName() + " added document Algorithms + Data Structures = Programs", "");
        adm.addLog(lb2.getName() + " added document The Art of Computer Programming", "");
        p1.checkOut(d3);
        p2.checkOut(d3);
        s.checkOut(d3);
        v.checkOut(d3);
        p3.checkOut(d3);
        adm.addLog(p1.getName() + " checked out document " + d3.getName(), "");
        adm.addLog(p2.getName() + " checked out document " + d3.getName(), "");
        adm.addLog(s.getName() + " checked out document " + d3.getName(), "");
        adm.addLog(v.getName() + " checked out document " + d3.getName(), "");
        adm.addLog(p3.getName() + " checked out document  " + d3.getName(), "");
        lb3.outstandingRequest(d3);
        adm.addLog(lb3.getName() + " placed outstanding request for " + d3.getName(), "");
        adm.addLog(lb3.getName() + " placed the outstanding request for " + d3.getName(), "");
        System.out.println("Waiting list for " + d3.getName() + ":");
        System.out.println();
        int[] wait = lb3.getWaitingList(d3.getDocID());
        for (int i = 0; i < wait.length; i++) {
            Patron ptr = new Patron(wait[i]);
            System.out.println(ptr.getName() + " waits for " + d3.getName());
        }
        assert wait.length == 0;
        System.out.println("=========================");
        Documents[] notifications = p1.getHaveToReturnNotifications();
        for (int i = 0; i < notifications.length; i++) {
            System.out.println(p1.getName() + " was notified to returned book " + notifications[i].getName());
            adm.addLog(p1.getName() + " was notified to returned book " + notifications[i].getName(), "");
        }
        assert notifications.length == 1;
        notifications = p2.getHaveToReturnNotifications();
        for (int i = 0; i < notifications.length; i++) {
            System.out.println(p2.getName() + " was notified to returned book " + notifications[i].getName());
            adm.addLog(p2.getName() + " was notified to returned book " + notifications[i].getName(), "");
        }
        assert notifications.length == 1;
        notifications = s.getHaveToReturnNotifications();
        for (int i = 0; i < notifications.length; i++) {
            System.out.println(s.getName() + " was notified to returned book " + notifications[i].getName());
            adm.addLog(s.getName() + " was notified to returned book " + notifications[i].getName(), "");
        }
        assert notifications.length == 1;
        notifications = v.getFailedBookingNotifications();
        for (int i = 0; i < notifications.length; i++) {
            System.out.println(v.getName() + " was notified that he/she was deleted from waiting list for " + notifications[i].getName());
            adm.addLog(v.getName() + " was notified that he/she was deleted from waiting list for " + notifications[i].getName(), "");
        }
        assert notifications.length == 1;
        notifications = p3.getFailedBookingNotifications();
        for (int i = 0; i < notifications.length; i++) {
            System.out.println(p3.getName() + " was notified that he/she was deleted from waiting list for " + notifications[i].getName());
            adm.addLog(p3.getName() + " was notified that he/she was deleted from waiting list for " + notifications[i].getName(), "");
        }
        String [][] logs = adm.getLogs();
        System.out.println("Current logs: ");
        System.out.println();
        for (int i = 0; i < logs.length; i++) {
            System.out.println("Date: " + logs[i][1]);
            System.out.println(logs[i][0]);
            System.out.println();
        }
        assert logs.length == 19;
    }

    public static void TC10() throws SQLException {
        System.out.println("******************************************");
        System.out.println("Test case 10: ");
        System.out.println();
    }

}
