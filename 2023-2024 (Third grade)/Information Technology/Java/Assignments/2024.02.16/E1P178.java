import java.util.Scanner;

public class E1P178 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        double n;

        System.out.println("Inserisci una sequenza di numeri: ");
        do {
            n = scanner.nextDouble();
            i++;
        } while (n!=0);
        System.out.printf("Hai inserito %d  numeri.",i - 1);

        scanner.close();
    }
}