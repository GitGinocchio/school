import java.util.Scanner;

public class Main0 {
    static Scanner scanner = new Scanner(System.in);
    static String[] bibite = {"Caffè","Caffè decaffeinato","Te"};
    static String[] sceltebinarie = {"N","Y"};
    static double[] prezzi = {1,0.70,1.50};

    public static void main(String[] args) {
        /* Ciclo principale che gestisce e utilizza i vari metodi 
         * Questo ciclo termina se e solo se l'utente sceglie di uscire.
        */
        
        while (true) {
            mostraBibite();
            int bibita = scegli("\nQuale bibita vuoi comprare?",bibite);
            mostraPrezzo(bibita);
            int comprare = scegli("Vuoi comprare questa bibita? (0: no; 1: si)",sceltebinarie);
            if (comprare == 0) {
                System.out.println("Acquisto non effettuato.");
                int exit = scegli("Vuoi uscire dal programma? (0: no; 1: si)",sceltebinarie);
                if (exit == 1) {break;}
            }
            else{
                System.out.println("\nAcquisto effettuato con successo.");
                System.out.printf("Ho preparato un %s.\n\n", bibite[bibita]);
            }
        }
        
    
        scanner.close();
    }

    static void mostraPrezzo(int index) {
        /*  
         *  Dato un indice di una bevanda
         *  Scrive a terminale il prezzo della bevanda con due cifre dopo la virgola.
         */
        System.out.printf("\nIl prezzo per il %s è di %.2f euro\n",bibite[index],prezzi[index]);
    }

    static void mostraBibite() {
        /*  Metodo che mostra una lista di bibite presenti*/
        System.out.println("Scegli una tra le bevande disponibili:");
        for (int i = 0; i < bibite.length; i++) {
            System.out.printf("%d - %s\n",i,bibite[i]);
        }
    }

    static int scegli(String domanda, String[] scelte) {
        /*
         *  Questo metodo permette all'utente di fare diversi tipi di scelte
         *  che possono essere binarie (come si o no)
         *  oppure la scelta tra diverse opzioni
         */
        int scelta;
        do {
            System.out.println(domanda);
            scelta = scanner.nextInt();
            if (scelta < 0 || scelta > scelte.length - 1) {
                System.out.println("Input non valido!");
            }
        } while (scelta < 0 || scelta > scelte.length - 1);

        return scelta;
    }
}