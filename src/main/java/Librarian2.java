import java.util.Random;

public class Librarian2 extends Librarian1 {

    public Librarian2(int id) {
        super(id);
    }

    public IntAndString addUser(String name, String phoneNumber, String address, String status) { //Method adds new User into data base. And it returns userID and password

        String pass = "";

        for (int i = 0; i < 5; i++){
            Random r = new Random();

            int randomNum = r.nextInt(123 - 48 + 1) + 48;
            pass += Character.toString((char)randomNum);
        }

        IntAndString res = new IntAndString(base.addNewUser(name, phoneNumber, address, status, pass), pass);
        return res;
    }


    public void addDocument(String name, String author, String publisher, String year, int counter, int cost, String edition, String type, String bestseller, String reference, String keywords){ //Method adds document into data base

        if (type.equals("AV"))
            base.addNewDocument(name, author, counter, cost);
        else{
            base.addNewDocument(name, publisher, year, edition, author, counter, cost, reference, bestseller, type, keywords);
        }
    }
}
