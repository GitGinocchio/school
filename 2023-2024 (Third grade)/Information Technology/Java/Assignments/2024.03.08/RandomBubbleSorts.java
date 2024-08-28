import java.util.Random;

public class RandomBubbleSorts {
    static int temp;     // variabile "di cortesia" per lo scambio.
    static int iter = 0; // variabile per il conto delle iterazioni dei vari metodi.
    public static void main(String[] args) {
        // Creo un ogetto generatore di tipo :Random:
        Random random = new Random();
        // Genero una :IntStream: di 10 numeri interi da 0 a 20 e la trasformo in array.
        int[] original = random.ints(10,0,21).toArray();

        // Creo un ciclo per mostrare a schermo l'array di numeri casuali non ordinati.
        System.out.print("array disordinato: ");
        for (int i = 0; i < original.length; i++) { System.out.printf("%d ",original[i]); }

        // IMPORTANTE: alle iterazioni interne ho aggiunto 10 a causa del ciclo esterno.
        // (tranne con la sentinella che sono gia' considerate)
        int[] sorted1 = bubblesort_originale(original.clone());
        System.out.printf("\n\nOriginale: \niterazioni totali: %d\n", iter+10);
        for (int i = 0; i < sorted1.length; i++) { System.out.printf("%d ",sorted1[i]); }
        
        int[] sorted2 = bubblesort_libro(original.clone());
        System.out.printf("\n\nLibro: \niterazioni totali: %d\n", iter+10);
        for (int i = 0; i < sorted2.length; i++) { System.out.printf("%d ",sorted2[i]); }
        
        int[] sorted3 = bubblesort_sentinella(original.clone());
        for (int i = 0; i < sorted3.length; i++) { System.out.printf("%d ",sorted3[i]); }

        int[] sorted4 = bubblesort_sentinella_migliorata(original.clone());
        for (int i = 0; i < sorted4.length; i++) { System.out.printf("%d ",sorted4[i]); }

        int[] sorted5 = bubblesort_libro_migliorato(original.clone());
        System.out.printf("\n\nlibro migliorato: \niterazioni totali: %d\n", iter+10);
        for (int i = 0; i < sorted5.length; i++) { System.out.printf("%d ",sorted5[i]); }
    }

    static int[] bubblesort_originale(int[] array) {
        iter = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j+1]){ 
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
                iter+=1; 
            }
        }
        return array;
    }

    static int[] bubblesort_libro(int[] array) {
        iter = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j+1]){ 
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
                iter+=1;
            }
        }
        return array;
    }

    static int[] bubblesort_sentinella(int[] array) {
        boolean scambi;
        iter = 0;

        do {
            scambi = false;
            for (int i=0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    scambi = true;
                }
                iter+=1;
            }
            iter+=1;
        } while (scambi == true);

        System.out.printf("\n\nSentinella:\nIterazioni totali: %d\n",iter);
        return array;
    }

    static int[] bubblesort_libro_migliorato(int[] array) {
        iter = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] <= array[j+1] && j+1 < array.length - 1){ j+=1; }
                if (array[j] > array[j+1]){ 
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
                iter+=1;
            }
        }
        return array;
    }

    static int[] bubblesort_sentinella_migliorata(int[] array) {
        boolean scambi = false;
        iter = 0;

        do {
            scambi = false;
            for (int i=0; i < array.length - 1; i++) {
                if (array[i] <= array[i+1] && i+1 < array.length - 1){ i+=1; }
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    scambi = true;
                }
                iter+=1;
            }
            iter+=1;
        } while (scambi == true);

        System.out.printf("\n\nSentinella migliorata:\nIterazioni totali: %d\n",iter);
        return array;
    }
}
