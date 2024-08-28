import java.util.Scanner;

public class E14P129 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il prezzo del primo prodotto: ");
        float p1 = scanner.nextFloat();

        System.out.print("Inserisci il prezzo del secondo prodotto: ");
        float p2 = scanner.nextFloat();
        float totale;

        if (p1 > p2) {
            totale = p1 + (float) (p2 / 2);
        }
        else {
            totale = p2 + (float) (p1 / 2);
        }
        System.out.printf("Il totale da pagare è: %.3f",totale);

        scanner.close();
    }
}
