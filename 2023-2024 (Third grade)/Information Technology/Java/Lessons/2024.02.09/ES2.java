import java.util.Random;

public class ES2 {
    public static void main(String[] args) {
        Random random = new Random();
        // Creare un array di 10 numeri casuali in una riga sola.
        int[] array = random.ints(10,0,10).toArray();
        int sum = 0;

        System.out.print("array: ");
        for (int i=0; i < array.length; i++) {
            System.out.printf("%d ",array[i]);
            sum += array[i];
        }
        System.out.printf("\nSomma totale: %d",sum);
    }
}
