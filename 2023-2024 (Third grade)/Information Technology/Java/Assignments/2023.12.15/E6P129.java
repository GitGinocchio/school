import java.util.Scanner;

public class E6P129 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci un numero: ");
        int num = scanner.nextInt();

        if ((float) num / 2 - (int) num / 2 > 0) {
            System.out.printf("Il numero è dispari, il suo triplo è: %d",num*num*num);
        }
        else {
            System.out.printf("Il numero è pari, il suo doppio è: %d",num*num);
        }

        scanner.close();
    }




}