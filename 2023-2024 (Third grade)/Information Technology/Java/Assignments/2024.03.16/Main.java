import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci fino a che numero intero vuoi la successione di fibonacci: ");
        int n = scanner.nextInt();
        System.out.println("Successione di Fibonacci fino al " + n + "-esimo termine:");
        for (int i=0;i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }


        scanner.close();
    }

    static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
