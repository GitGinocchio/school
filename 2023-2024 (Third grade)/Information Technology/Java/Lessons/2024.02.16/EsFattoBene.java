import java.util.Scanner;

public class EsFattoBene {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n;
        int i = 0;
        double sum = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        System.out.printf("Inserisci la sequenza di numeri: \n");
        
        do {
            n = scanner.nextDouble();
            if (n != 0) {
                sum+=n;
                i+=1;
                if (n > max) { max = n; }
                else if (n < min) { min = n; }
            }
        } while (n!=0);
        
        System.out.printf("\nHai inserito %d numeri\n",i);
        System.out.printf("La somma dei numeri inseriti è %.3f\n",sum);
        System.out.printf("La media dei numeri inseriti è %.3f\n",(float) sum / i);
        System.out.printf("Il valore massimo inserito è %.3f\n",max);
        System.out.printf("Il valore minimo inserito è %.3f",min);


        scanner.close();
    }
}
