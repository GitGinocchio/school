import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci un numero: ");
        int n = scanner.nextInt();
        System.out.println();

        int f1 = factorialwhile(n);
        System.out.println(f1);

        int f2 = factorialrecursive(n);
        System.out.println(f2);

        int f3 = factorialtail(n,1,1);
        System.out.println(f3);

        scanner.close();
    }

    static int factorialwhile(int number) {
        int factorial = 1;
        int i = 1;
        while (i <= number) {
            factorial*=i;
            i++;
        }
        return factorial;
    }

    static int factorialrecursive(int number) {
        if (number==0) {
            return 1;
        }
        else {
            return number *factorialrecursive(number-1);
        }
    }

    static int factorialtail(int number, int factorial, int i) {
        if (i <= number) {
            factorial*=i;
            i++;
            return factorialtail(number,factorial,i);
        }
        return factorial;
    }

    }