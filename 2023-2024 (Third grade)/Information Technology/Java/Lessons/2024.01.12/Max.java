import java.util.Scanner;

public class Max {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 2;
        float max;
        float current;
        
        System.out.print("Inserisci il 1 numero: ");
        max = scanner.nextInt();


        // while (i < 11) {
            //System.out.printf("Inserisci il %d numero: ",i);
            //current = scanner.nextInt();
            //if (current > max) { max = current; }
            //i++;
        //}

        for (int j=0; j < 11; j++) {
            System.out.printf("Inserisci il %d numero: ",i);
            current = scanner.nextInt();
            if (current > max) { max = current; }
        }

        System.out.printf("Il numero maggiore è: %f",max);

        scanner.close();
    }
}
