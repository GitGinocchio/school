import java.util.Scanner;

public class E5P129 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci la temperatura della serra:");
        int gradi = scanner.nextInt();


        if (gradi == 18) {
            System.out.println("Temperatura perfetta. Stato: [Ottimale]");
        }
        else if (gradi > 5 && gradi < 18) {
            System.out.println("Temperatura bassa. Stato: [Pericolo]");
        }
        else if (gradi < 5) {
            System.out.println("Temperatura troppo bassa. Stato: [Danni irreparabili]");
        }
        else {
            System.out.println("Stato: [Indefinito]");
        }

        scanner.close();
    }
}
