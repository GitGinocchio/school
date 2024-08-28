import java.util.Scanner;

public class EsGPT {
    static int sum = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[0];
        int n;

        System.out.println("Inserisci una sequenza di numeri interi terminante con l'immissione del numero 0:");
        do {
            n = scanner.nextInt();
            if (n != 0) { array = expand(array,n); }
        }
        while (n!=0);
        System.out.printf("\nIl valore massimo all'interno dell'array è: %d",max(array));
        System.out.printf("\nIl valore minimo all'interno dell'array è: %d",min(array));
        System.out.printf("\nLa media dei valori all'interno dell'array è: %d",avg(array));
        System.out.printf("\nLa somma di tutti i numeri del vettore è: %d",sum);
        
        scanner.close();
    }
    
    static int[] expand(int[] v, int n) { 
        int expanded[] = new int[v.length + 1];
        expanded[expanded.length - 1] = n;
        for (int i = 0; i < v.length; i++) { expanded[i] = v[i]; }
        return expanded;
    }

    static int max(int[] v) {
        int m = v[0];
        for (int i=0; i < v.length; i++) {
            if (v[i] > m) { m = v[i]; }
        }
        return m;
    }

    static int min(int[] v) {
        int m = v[0];
        for (int i=0; i < v.length; i++) {
            if (v[i] < m) { m = v[i]; }
        }
        return m;
    }

    static int avg(int[] v) {
        for (int i=0; i < v.length; i++) { sum+=v[i]; }
        return sum / v.length;
    } 
}