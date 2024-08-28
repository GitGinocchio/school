import java.util.Scanner;

public class bin_dec {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci un numero (base 10): ");
        int decimal = scanner.nextInt();
        String result1 = decimalToBinary(decimal);
        System.out.println("Il numero inserito in binario è: " + result1);
        scanner.nextLine();
        System.out.println("Inserisci un numero (base 2): ");
        String binary = scanner.nextLine();
        int result = BinaryToDecimal(binary);
        System.out.println("Il numero inserito in decimale è: " + result);
        
        scanner.close();
    }

    public static String decimalToBinary(int decimal) {
        String binary = "";
        while (decimal != 0) {
            binary = Integer.toString(decimal % 2) + binary;
            decimal = decimal / 2;
        }
        return binary;
    }

    public static int BinaryToDecimal(String binary) {
        int decimale = 0;

        for (int i = 0; i < binary.length(); i++) {
            decimale += Character.getNumericValue(binary.charAt(binary.length() - 1 - i)) * (1 << i);
        }
    
        return decimale;
    }
}