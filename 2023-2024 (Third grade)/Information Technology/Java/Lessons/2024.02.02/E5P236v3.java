import java.util.Scanner;

public class E5P236v3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int unique[] = new int[0];
        int n;

        System.out.printf("Inserisci la sequenza di numeri: \n");
        for (int i = 0; i < 10; i++) {
            n = scanner.nextInt();
            if ( isunique(unique, n)) { unique = expand(unique, n); }
        }
        System.out.print("\n\nNumeri presenti una volta sola: ");
        for (int i = 0; i < unique.length; i++) { System.out.printf("%d ", unique[i]); }
        scanner.close();
    }

    static int[] expand(int[] vet, int n) { 
        int vector[] = new int[vet.length + 1];
        vector[vector.length - 1] = n;
        for (int i = 0; i < vet.length; i++) { vector[i] = vet[i]; }
        return vector;
    }

    static boolean isunique(int[] vet, int n) {
        for (int i = 0; i < vet.length; i++) { if (vet[i] == n) { return false; } } return true;
    }
}
