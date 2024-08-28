import java.util.Scanner;

public class E5P236v1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nums[] = new int[10];
        int unique[] = new int[10];

        for (int i = 0; i < 10; i++) {
            System.out.printf("Inserisci il %d numero: ",i+1);
            nums[i] = scanner.nextInt();
            if (isunique(unique, nums[i])) {
                unique[i] = nums[i];
            }
        }

        System.out.print("\n\nNumeri presenti una volta sola: ");
        for (int i = 0; i < 10; i++) {
            if (unique[i] != 0) {
                System.out.printf("%d ", unique[i]);
            }
        }

        scanner.close();
    }


    // controlla se un numero è presente in una lista data
    static boolean isunique(int[] vet, int n) {
        for (int i = 0; i < vet.length - 1; i++) {
            if (vet[i] == n) { return false; }
        }
        return true;
    }
}
