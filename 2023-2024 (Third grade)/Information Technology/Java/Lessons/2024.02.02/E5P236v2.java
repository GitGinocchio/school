import java.util.Scanner;

public class E5P236v2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int unique[] = new int[10]; // Inizializza un vettore di lunghezza 10
        int n;

        // Inizializzo tutti i valori con Integer.MIN_VALUE
        // Questo perche' altrimenti non potrei inserire il numero 0
        for (int i = 0; i < 10; i++) { unique[i] = Integer.MIN_VALUE; }

        for (int i = 0; i < 10; i++) {
            System.out.printf("Inserisci il %d numero: ",i+1);
            n = scanner.nextInt();
            // Controllo se il numero inserito e' presente una volta sola nella lista
            if ( isunique(unique, n) ) { unique[i] = n; }
        }

        for (int i = 0; i < 10; i++) {
            // Visualizzo a schermo solamente i numeri diversi da Integer.MIN_VALUE
            if (unique[i] != Integer.MIN_VALUE) { System.out.printf("%d ", unique[i]); }
        }

        scanner.close();
    }

    //  Metodo che controlla se un numero e' presente almeno una volta all'interno di un vettore
    static boolean isunique(int[] vet, int n) {
        for (int i = 0; i < vet.length - 1; i++) {
            if (vet[i] == n) { return false; }
        }
        return true;
    }
}
