import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci un numero: ");
        int num = scanner.nextInt();
        int fib1 = 1;
        int fib2 = 1;
        int fib3 = 0;
        int i = 0;

        System.out.println("\nSequenza di Fibonacci:");
        System.out.println(0);
        System.out.println(1);
        System.out.println(1);

        while (i < num - 3) {
            fib3 = fib1 + fib2;
            System.out.println(fib3);
            fib1 = fib2;
            fib2 = fib3;
            i++;
        }

        // for (int j = 0; j < num - 3; j++) {
            //fib3 = fib1 + fib2;
            //System.out.println(fib3);
            //fib1 = fib2;
            //fib2 = fib3;
        //}


        scanner.close();
    }
}