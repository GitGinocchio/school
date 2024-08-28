import java.util.Scanner;

public class SeMaggiorenne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quanti anni hai?");
        int anni = scanner.nextInt();

        if (anni >= 18) {
            System.out.println("Sei maggiorenne");
        } else {
            System.out.println("Non sei maggiorenne");
        }

        scanner.close();
    }

}