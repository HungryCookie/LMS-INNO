public class IntAndString {
    int intField;
    String stringField;

    public IntAndString(){}

    public IntAndString(int i, String s){
        intField = i;
        stringField = s;
    }

    public int getInt(){
        return intField;
    }

    public String getString(){
        return stringField;
    }
}
