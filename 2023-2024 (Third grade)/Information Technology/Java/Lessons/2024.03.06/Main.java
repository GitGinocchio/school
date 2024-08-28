import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;

        do {
            System.out.println("Inserisci n:");
            n = scanner.nextInt();
        }
        while (n < 0);

        System.out.println(factorial(n));

        scanner.close();
    }

    static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        else {
            return n * factorial(n-1);
        }
    }
}