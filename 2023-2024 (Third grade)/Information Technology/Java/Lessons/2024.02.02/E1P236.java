import java.lang.Math;
import java.util.Scanner;

public class E1P236 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci quanti numeri vuoi generare: ");
        int nums[] = new int[scanner.nextInt() + 1];

        for (int i = 0; i < nums.length - 1; i++) {
            nums[i] = (int) (Math.random() * 10);
            System.out.printf("Numero generato casualmente: %d. Il suo quadrato: %d\n",nums[i],nums[i] * nums[i]);
        }

        scanner.close();
    }
}