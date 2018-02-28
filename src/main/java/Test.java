import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
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

    public static void TC1() {
        Patron ptr = new Patron(1);
        Librarian lbr = new Librarian(2);
        String name = "Discrete Math";
        Documents doc = new Documents(name);
        System.out.println("Starting test case 1. ");
        System.out.println("Number of copies in library: " + doc.getCopies());
        System.out.println("Try to book "+doc.getName()+" for "+ptr.getName());
        int week = ptr.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to book this document");
        doc = new Documents(name);
        System.out.println("Number of copies after action: " + doc.getCopies());
        System.out.println("**********************");
        start();
    }

    public static void TC2() {
        Patron ptr = new Patron(5);
        Librarian lbr = new Librarian(2);
        String name = "Impossible book";
        Documents doc = new Documents(name); // id = 2
        System.out.println("Starting test case 2. ");
        System.out.println("Number of copies in library: " + doc.getCopies());
        System.out.println("Try to book "+doc.getName()+" for "+ptr.getName());
        int week = ptr.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to book this document");
        doc = new Documents(name);
        System.out.println("Number of copies after action: " + doc.getCopies());
        System.out.println("**********************");
        start();
    }

    public static void TC3() {
        Patron fclt = new Patron(3);
        Patron std = new Patron(6);
        Librarian lbr = new Librarian(2);
        String name = "Discrete Math";
        Documents doc = new Documents(name); // id = 2
        System.out.println("Starting test case 3. ");
        System.out.println("Number of copies in library: " + doc.getCopies());
        System.out.println("Try to book "+doc.getName()+" for "+fclt.getName());
        int week = fclt.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to book this document");
        doc = new Documents(name);
        System.out.println("Number of copies after action: " + doc.getCopies());
        System.out.println("**********************");
        start();
    }

    public static void TC4() {
        Patron fclt = new Patron(7);
        Patron std = new Patron(8);
        Librarian lbr = new Librarian(2);
        String name = "Touch of Class";
        Documents doc = new Documents(name); // id = 2
        System.out.println("Starting test case 4. ");
        System.out.println("Number of copies in library: " + doc.getCopies());
        System.out.println("Try to book "+doc.getName()+" for "+fclt.getName());
        int week = fclt.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to book this document");
        doc = new Documents(name);
        System.out.println("Number of copies after action: " + doc.getCopies());
        System.out.println("**********************");
        start();
    }

    public static void TC5() {
        Patron ptr1 = new Patron(9);
        Patron ptr2 = new Patron(10);
        Patron ptr3 = new Patron(11);
        String name = "Academy";
        Librarian lbr = new Librarian(2);
        Documents doc = new Documents(name);
        System.out.println("Starting test case 5. ");
        System.out.println("Number of copies in library: " + doc.getCopies());
        System.out.println("Try to book "+doc.getName()+" for "+ptr1.getName()+", "+ptr2.getName()+" and "+ptr3.getName());
        int week1 = ptr1.bookADocument(doc);
        int week2 = ptr2.bookADocument(doc);
        int week3 = ptr3.bookADocument(doc);
        if (week1 != 0) {
            System.out.println("The document is successfully booked for " + week1 + " weeks");
        }
        else System.out.println("It's impossible to check out this document");
        if (week2 != 0) {
            System.out.println("The document is successfully booked for " + week2 + " weeks");
        }
        else System.out.println("It's impossible to check out this document");
        if (week3 != 0) {
            System.out.println("The document is successfully booked for " + week3 + " weeks");
        }
        else System.out.println("It's impossible to check out this document");
        doc = new Documents(name);
        System.out.println("Number of copies after action: "+ doc.getCopies());
        System.out.println("**********************");
        start();
    }

    public static void TC6() {
        System.out.println("Starting test case 6. ");
        TC1();
    }

    public static void TC7() {
        Patron ptr1 = new Patron(13);
        Patron ptr2 = new Patron(16);
        Librarian lbr = new Librarian(2);
        String name = "Discrete Math";
        Documents doc = new Documents(name); // id = 2
        System.out.println("Starting test case 7. ");
        System.out.println("Number of copies in library: " + doc.getCopies());
        System.out.println("Try to book "+doc.getName()+" for "+ptr1.getName()+" and "+ ptr2.getName());
        int week = ptr1.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to book this document");
        week = ptr2.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to book this document");
        doc = new Documents(name);
        System.out.println("Number of copies after action: " + doc.getCopies());
        System.out.println("**********************");
        start();
    }

    public static void TC8() {
        Patron fclt = new Patron(14);
        Patron std = new Patron(17);
        Librarian lbr = new Librarian(2);
        String name = "Discrete Math";
        Documents doc = new Documents(name);
        System.out.println("Starting test case 8. ");
        System.out.println("Number of copies in library: " + doc.getCopies());
        System.out.println("Try to book "+doc.getName()+" for "+std.getName());
        int week = std.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to book this document");
        doc = new Documents(name);
        System.out.println("Number of copies after action: " + doc.getCopies());
        System.out.println("**********************");
        start();
    }

    public static void TC9() {
        Patron fclt = new Patron(15);
        Patron std = new Patron(18);
        Librarian lbr = new Librarian(2);
        String name = "Touch of Class";
        Documents doc = new Documents(name);
        System.out.println("Starting test case 9. ");
        System.out.println("Number of copies in library: " + doc.getCopies());
        System.out.println("Try to book "+doc.getName()+" for "+std.getName());
        int week = std.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to book this document");
        doc = new Documents(name);
        System.out.println("Number of copies after action: " + doc.getCopies());
        System.out.println("**********************");
        start();
    }

    public static void TC10() {
        Patron ptr = new Patron(25);
        Librarian lbr = new Librarian(2);
        Documents docNoRef = new Documents("Discrete Math");
        Documents docRef = new Documents("Computer Architecture");
        System.out.println("Number of copies (non-referal book): " + docNoRef.getCopies());
        System.out.println("Number of copies (referal book): " + docRef.getCopies());
        System.out.println("Try to book non-referal doc "+docNoRef.getName()+" for "+ptr.getName());
        int week = ptr.bookADocument(docNoRef);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to book this document");
        System.out.println("Try to book referal doc "+docRef.getName()+" for "+ptr.getName());
        week = ptr.bookADocument(docRef);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to book this document");
        docNoRef = new Documents("Discrete Math");
        System.out.println("Number of copies after action (non-referal): " + docNoRef.getCopies());
        docRef = new Documents("Computer Architecture");
        System.out.println("Number of copies after action (referal): " + docRef.getCopies());
        start();
    }

}
