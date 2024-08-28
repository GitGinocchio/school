import java.util.Scanner; // importare le librerie



public class E10P168 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int min = Integer.MAX_VALUE; // Assegno il valore massimo per i numeri interi per poter iniziare il ciclo
        int n = -1; // Assegno il valore di -1 per poter iniziare il ciclo
        int i = 0;

        while (n != 0) {
            System.out.printf("Inserisci il %d numero (0 per terminare): ", i);
            n = scanner.nextInt();
            if (n > 0) {
                if (n < min) { min = n; }
                i++;
            }
            else if (n == 0) {}
            else {
                System.out.println("Sono accettati solamente numeri interi positivi!\n");
            }
        }

        if (min != Integer.MAX_VALUE && i > 1) {
            System.out.printf("Il numero minimo inserito è %d.",min); // è stato inserito piu' di un numero ed esiste un minimo.
        }
        else if (min != Integer.MAX_VALUE) {
            System.out.printf("è stato inserito solo il numero %d.",min); // esiste solo un numero.
        }
        else {
            System.out.printf("Non è stato inserito nessun numero."); // non sono stati inseriti numeri.
        }

        scanner.close();
    }
}