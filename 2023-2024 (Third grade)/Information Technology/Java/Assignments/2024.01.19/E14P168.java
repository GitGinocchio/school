import java.util.Scanner;

public class E14P168 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int max = Integer.MAX_VALUE;
        int i = 0;
        int n;

        while (sum <= max) {
            if (i == 0) {
                System.out.print("Inserisci il numero massimo: ");
                max = scanner.nextInt();

                if (max >= 0) { i++; }
                else {
                    max = Integer.MAX_VALUE;
                    System.out.println("Sono accettati solo numeri interi positivi!\n");
                }
            }
            else {
                System.out.printf("Inserisci il %d numero: ",i);
                n = scanner.nextInt();
    
                if (n >= 0) {
                    sum +=n;
                    i++;
                }
                else {
                    System.out.println("Sono accettati solo numeri interi positivi!\n");
                }
            }
        }
        System.out.println("Hai superato il valore massimo impostato!");


        scanner.close();
    }
}
