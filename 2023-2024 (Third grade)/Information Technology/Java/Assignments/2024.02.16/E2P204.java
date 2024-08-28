import java.util.Scanner;

public class E2P204 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci n: ");
        int n = scanner.nextInt();
        System.out.print("Inserisci m: ");
        int m = scanner.nextInt();
        int result = 0;

        System.out.printf("\nLa moltiplicazione tra %d e %d e\' %d \n",n,m,n * m);

        while (m > 0) {                                     // Mentre m e' maggiore di 0
            if (m % 2 == 1) { result = sum(result,n); }     // Se e' presente un resto somma il risultato con n
            n = mol(n);                                     // n e' uguale alla moltiplicazione di n per 2
            m = div(m);                                     // m e' uguale alla divisione di m per 2
        }
        System.out.printf("Risultato: %d",result);

        scanner.close();
    }

    static int sum(int n1, int n2) {
        return n1 + n2;
    }

    static int sub(int n1, int n2) {
        return n1 - n2;
    }

    static int mol(int n) {
        return n * 2;
    }

    static int div(int n) {
        return n / 2;
    }

}
