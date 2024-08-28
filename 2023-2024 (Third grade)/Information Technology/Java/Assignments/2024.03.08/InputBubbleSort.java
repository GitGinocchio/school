import java.util.Scanner;

public class InputBubbleSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int temp;

        System.out.print("Inserisci il numero di elementi: ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            System.out.printf("Inserisci l'elemento %d: ",i+1);
            array[i] = scanner.nextInt();
        }

        System.out.print("unsorted: ");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d ",array[i]);
        }
        System.out.println();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j+1]){ 
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        System.out.print("sorted: ");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d ",array[i]);
        }


        scanner.close();
    }
}