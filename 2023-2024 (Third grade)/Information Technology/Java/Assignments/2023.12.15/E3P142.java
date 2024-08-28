import java.util.Scanner;

public class E3P142 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il primo numero: ");
        int num1 = scanner.nextInt();
        System.out.print("Inserisci il secondo numero: ");
        int num2 = scanner.nextInt();
        System.out.print("Inserisci il terzo numero: ");
        int num3 = scanner.nextInt();

        if (num1 * num1 + num2 * num2 == num3 * num3) {
            System.out.printf("(%d, %d, %d) è una terna pitagorica!",num1,num2,num3);
        }
        else {
            System.out.printf("(%d, %d, %d) non è una terna pitagorica!",num1,num2,num3);
        }

        scanner.close();
    }
}
