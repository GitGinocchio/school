import java.util.Scanner;

public class ES3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // numero in input
        // quadrato e cubo
        // dispari quadrato
        // pari cubo
        System.out.print("Inserisci n: ");
        int n = scanner.nextInt();
        System.out.printf("\nIl quadrato di %d è %d\n",n,quadrato(n));
        System.out.printf("Il cubo di %d è %d",n,cubo(n));
        
        scanner.close();
    }

    static int quadrato(int n) {
        return n * n;
    }

    static int cubo(int n) {
        return n * n * n;
    }

}
