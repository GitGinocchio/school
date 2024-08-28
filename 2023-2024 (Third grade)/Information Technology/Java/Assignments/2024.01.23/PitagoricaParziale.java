import java.util.Scanner;

public class PitagoricaParziale {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int i, numero;
        System.out.print("Tabellina da visualizzare: ");
        numero = scanner.nextInt();
        for (i = 1; i <= 6; i++) {
            System.out.print(i * numero + " \t");
        }

        scanner.close();
    }
}