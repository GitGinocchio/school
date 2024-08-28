import java.util.Scanner;

public class E1P204 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double sum = 0; 
        int i = 0;
        double n;

        System.out.println("Inserisci una sequenza di numeri: ");
        do {
            n = scanner.nextDouble();
            sum+=n;
            i++;
        } while (n!=0);
        System.out.printf("Hai inserito %d  numeri, la somma e\' %f.",i - 1,sum);

        scanner.close();
    }
}