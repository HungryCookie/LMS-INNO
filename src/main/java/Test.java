import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        System.out.println("Number of test: ");
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
        Documents doc = new Documents("Discrete Math"); // id = 2
        System.out.println("Number of copies: " + doc.getCopies());
        int week = ptr.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to book this document");
        doc = new Documents("Discrete Math");
        System.out.println("Number of copies: " + doc.getCopies());
        start();
    }

    public static void TC2() {
        Patron ptr = new Patron(1);
        Librarian lbr = new Librarian(2);
        Documents doc = new Documents("Impossible book");
        if (doc != null) {
            System.out.println("Number of copies: " + doc.getCopies());
            System.out.println("There is no such document!!!");
            start();
            return;
        }
        int week = ptr.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to check out this document");
        doc = new Documents("Impossible book");
        System.out.println("Number of copies: "+ doc.getCopies());
        start();
    }

    public static void TC3() {
        Patron fclt = new Patron(4);
        Patron std = new Patron(5);
        Librarian lbr = new Librarian(2);
        Documents doc = new Documents("Discrete Math");
        System.out.println("Number of copies: " + doc.getCopies());
        int week = fclt.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to check out this document");
        doc = new Documents("Discrete Math");
        System.out.println("Number of copies: "+ doc.getCopies());
        start();
    }

    public static void TC4() {
        Patron fclt = new Patron(4);
        Patron std = new Patron(5);
        Librarian lbr = new Librarian(2);
        Documents doc = new Documents("Touch of Class");
        System.out.println("Number of copies: " + doc.getCopies());
        int week = fclt.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to check out this document");
        doc = new Documents("Touch of Class");
        System.out.println("Number of copies: "+ doc.getCopies());
        start();
    }

    public static void TC5() {
        Patron ptr1 = new Patron(4);
        Patron ptr2 = new Patron(5);
        Patron ptr3 = new Patron(5);
        Librarian lbr = new Librarian(2);
        Documents doc = new Documents("Academy");
        System.out.println("Number of copies: " + doc.getCopies());
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
        doc = new Documents("Academy");
        System.out.println("Number of copies: "+ doc.getCopies());
        start();
    }

    public static void TC6() {
        Patron ptr = new Patron(1);
        Librarian lbr = new Librarian(2);
        Documents doc = new Documents("Discrete Math");
        int week = ptr.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to check out this document");
        week = ptr.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to check out this document");
        start();
    }

    public static void TC7() {
        Patron ptr1 = new Patron(4);
        Patron ptr2 = new Patron(5);
        Librarian lbr = new Librarian(2);
        Documents doc = new Documents("Discrete Math");
        System.out.println("Number of copies: " + doc.getCopies());
        int week1 = ptr1.bookADocument(doc);
        int week2 = ptr2.bookADocument(doc);
        if (week1 != 0) {
            System.out.println("The document is successfully booked for " + week1 + " weeks");
        }
        else System.out.println("It's impossible to check out this document");
        if (week2 != 0) {
            System.out.println("The document is successfully booked for " + week2 + " weeks");
        }
        else System.out.println("It's impossible to check out this document");
        doc = new Documents("Discrete Math");
        System.out.println("Number of copies: "+ doc.getCopies());
        start();
    }

    public static void TC8() {
        Patron fclt = new Patron(4);
        Patron std = new Patron(5);
        Librarian lbr = new Librarian(2);
        Documents doc = new Documents("Discrete Math");
        System.out.println("Number of copies: " + doc.getCopies());
        int week = std.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to check out this document");
        doc = new Documents("Discrete Math");
        System.out.println("Number of copies: "+ doc.getCopies());
        start();
    }

    public static void TC9() {
        Patron fclt = new Patron(4);
        Patron std = new Patron(5);
        Librarian lbr = new Librarian(2);
        Documents doc = new Documents("Touch of Class");
        System.out.println("Number of copies: " + doc.getCopies());
        int week = std.bookADocument(doc);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to check out this document");
        doc = new Documents("Touch of Class");
        System.out.println("Number of copies: "+ doc.getCopies());
        start();
    }

    public static void TC10() {
        Patron ptr = new Patron(1);
        Librarian lbr = new Librarian(2);
        Documents docNoRef = new Documents("Discrete Math");
        Documents docRef = new Documents("Computer Architecture");
        System.out.println("Number of copies: " + docNoRef.getCopies());
        System.out.println("Number of copies: " + docRef.getCopies());
        int week = ptr.bookADocument(docNoRef);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to book this document");
        week = ptr.bookADocument(docRef);
        if (week != 0) {
            System.out.println("The document is successfully booked for " + week + " weeks");
        }
        else System.out.println("It's impossible to book this document");
        docNoRef = new Documents("Discrete Math");
        System.out.println("Number of copies: " + docNoRef.getCopies());
        docRef = new Documents("Computer Architecture");
        System.out.println("Number of copies: " + docRef.getCopies());
        start();
    }

}
