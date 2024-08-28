import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        int array[] = new int[10];
        int reversed[] = new int[array.length];
        // Inizializzo due array con dimensione identica di 10 elementi con valore iniziale a 0

        for (int i = array.length-1; i >= 0; i--) {
            array[i] = (int) (Math.random() * 10);
            reversed[array.length - i-1] = array[i];
        }

        System.out.print("Vettore: ");
        for (int i = 0; i <= array.length - 1; i++) {
            System.out.print(array[i]+" ");
        }

        System.out.print("\nVettore (reversed): ");
        for (int i = 0; i <= reversed.length - 1; i++) {
            System.out.print(reversed[i]+" ");
        }

    }
}