import java.util.Scanner;

public class PariDispari {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci n: ");
        int n = scanner.nextInt();


        if (n % 2 == 0) {
            System.out.printf("Il numero %d è pari\n",n);
        }
        else {
            System.out.printf("Il numero %d è dispari\n",n);
        }

        while (n > 1) {
            n = n - 2;
        }
        if (n == 0) {
            System.out.println("Il numero inserito è pari");
        }
        else {
            System.out.println("Il numero inserito è dispari");
        }





        scanner.close();
    }



}