import java.util.Scanner;

public class E11P129 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci la spesa totale: ");
        double spesa = scanner.nextDouble();
        double sconto;


        if (spesa < 500) {
            sconto = (double) 20 / 100 * spesa;
        } 
        else {
            sconto = (double) 20 / 100 * spesa;
        }
        System.out.printf("viene applicato uno sconto di %.2f euro, l'importo da pagare è %.2f", sconto, spesa - sconto);

        scanner.close();
    }
    
}
