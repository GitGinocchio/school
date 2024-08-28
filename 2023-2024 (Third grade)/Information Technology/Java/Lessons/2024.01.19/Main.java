/* 
    dato un numero n, di ammalati nell'ospedale di Monselice e data la percentuale media di guariti giornalieri  FATTO.
    andare a definire quanti giorni effettivi ci vogliono per scendere sotto alla soglia dei cento ammalati. FATTO.
    Tieni conto che è possibile che nuovi ammalati vengano ricoverati FATTO.
*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ammalati;
        int nuovi_ammalati = 0;
        float pmg; // pmg percentuale media guariti
        int i = 1;

        System.out.print("Inserisci il numero degli ammalati: ");
        ammalati = scanner.nextInt();

        System.out.print("Inserisci la percentuale media di guariti: ");
        pmg = scanner.nextFloat();

        // media dei guariti in una giornata (pmg * ammalati) / 100
        // giorni necessari per scendere sotto alla soglia dei cento. 

        while (ammalati >= 100) {
            ammalati -= (pmg * ammalati) / 100;
            System.out.printf("Giorno %d: Ammalati %d: Inserisci il numero dei nuovi ammalati: ",i,ammalati);
            nuovi_ammalati = scanner.nextInt();
            if (nuovi_ammalati >= 0) {
                ammalati += nuovi_ammalati;
                i++;
            }

            else {
                System.out.println("Il numero dei nuovi ammalati deve essere maggiore o uguale a 0!");

            }
        }
        System.out.printf("Giorni necessari %d",i);

        scanner.close();
    }
}