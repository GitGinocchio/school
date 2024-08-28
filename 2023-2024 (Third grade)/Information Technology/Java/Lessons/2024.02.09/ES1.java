import java.util.Random;

public class ES1 {
    public static void main(String[] args) {
        Random random = new Random();
        final int R = random.nextInt(-10,10);

        if (R > 0) {
            System.out.printf("Il numero scelto casualmente è %d.\n",R);
            System.out.printf("I primi %d numeri naturali sono: ",R);
            for (int i = 0; i < R; i++) {
                System.out.printf("%d ", i);
            }
        } 
        else {
            System.out.printf("Il numero scelto casualmente è %d.",R);
        }
    }
}