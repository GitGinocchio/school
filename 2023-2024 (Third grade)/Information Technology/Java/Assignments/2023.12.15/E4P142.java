import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class E4P142 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Float> nums = new ArrayList<>(4);

        System.out.println("Quanti numeri vuoi inserire?");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.printf("Inserisci il %d numero: ",i+1);
            nums.add(scanner.nextFloat());
        }

        float max = nums.get(0), min = nums.get(0);

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < min) { min = nums.get(i); }
            else if (nums.get(i) > max) { max = nums.get(i); }
        }

        System.out.printf("\nmax: %f min: %f",max,min);



        scanner.close();
    }
}
