import java.util.Scanner;

public class E3P178 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci NUM: ");
        final int NUM = scanner.nextInt();
        double sum = 0;

        for (int i = 0; i < NUM; i++) {
            System.out.printf("Inserisci il %d numero: ", i+1);
            sum+= scanner.nextDouble();
        }
        System.out.printf("La somma di tutti i numeri e\' %.3f", sum);

        scanner.close();
    }
}
