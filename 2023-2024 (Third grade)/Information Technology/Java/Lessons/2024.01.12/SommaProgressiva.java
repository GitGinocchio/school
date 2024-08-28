import java.util.Scanner;

public class SommaProgressiva {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            System.out.print("Inserisci un numero: ");
            n = scanner.nextInt();
            sum = sum + n;
            System.out.println("Somma progressiva: " + sum);
        }

        scanner.close();
    }
}
