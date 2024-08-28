import java.util.Scanner;

public class E2P142 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il primo numero: ");
        int num1 = scanner.nextInt();

        System.out.print("Inserisci il secondo numero: ");
        int num2 = scanner.nextInt();

        System.out.print("Inserisci il terzo numero: ");
        int num3 = scanner.nextInt();

        System.out.println();
        if (num1 == num2) {
            System.out.println("Il primo e il secondo numero hanno lo stesso valore");
        } 
        else if (num1 == num3) {
            System.out.println("Il primo e il terzo numero hanno lo stesso valore");
        }
        else if (num2 == num3) {
            System.out.println("Il secondo e il terzo numero hanno lo stesso valore");
        }
        else {
            System.out.println("Tutti i numeri hanno valori diversi");
        }

        scanner.close();
    }
}
