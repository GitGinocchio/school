import java.util.Scanner;

public class E7P168 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int i = 0;

        while (sum <= 200) {
            System.out.printf("Inserisci il %d numero: ",i+1);
            sum += scanner.nextInt();
            i++;
        }
        System.out.printf("\nHai inserito %d numero/i e superato il numero 200!",i);


        scanner.close();
    }
}
