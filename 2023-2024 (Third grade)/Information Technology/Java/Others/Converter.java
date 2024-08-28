



// AND = && 
// OR = ||
// NOT = !

// IF 
// IF ANNIDATI
// ELSE
// ELSE IF 



public class Converter {
    public static void main(String[] args) {
        String result = getBin(10);
        System.out.println(result);
    }



    public static String getBin(int decimal) {
        int n=decimal;
        String binary="";

        while (n!=0) {
            binary=n%2+binary;
            n/=2;
        }

        return binary;
    }

    public static String getOct(int decimal) {


        return "";
    }

    public static String getHex(int decimal) {

        return "";
    }
}