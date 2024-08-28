import java.util.Scanner;

public class E1P142 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il primo numero: ");
        int num1 = scanner.nextInt();
        System.out.print("Inserisci il secondo numero: ");
        int num2 = scanner.nextInt();

        System.out.println("\n");
        if (num1 > num2) {
            System.out.printf("(prima) num1: %d, num2: %d \n", num1, num2);
            System.out.println("I valori delle variabili verranno scambiati...");
            int temp = num2;
            num2 = num1;
            num1 = temp;
            System.out.printf("(dopo) num1: %d, num2: %d \n", num1, num2);
        }
        else {
            System.out.printf("(prima) num1: %d, num2: %d \n", num1, num2);
            System.out.println("I valori delle variabili non verranno scambiati...");
            System.out.printf("(dopo) num1: %d, num2: %d \n", num1, num2);
        }

        scanner.close();
    }
}