import java.util.Random;


public class test {
    public static void main(String[] args) {
        Random random = new Random();   // Oggetto Random per la generazione di numeri casuali.
        int[] array = new int[10];      // Inizializzo un vettore di interi con lunghezza 10.
        int max, min, avg;              // Inizializzo le tre variabili massimo, minimo e media.

        // Ciclo per il caricamento di 10 numeri casuali da -10 a 100.
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(-10,100);
        }

        // Chiamata dei tre metodi.
        max = getMax(array);
        min = getMin(array);
        avg = getAvg(array);

        // Stampa a schermo dei risultati.
        System.out.printf("min: %d max: %d avg: %d",min,max,avg);
    }

    public static int getMin(int vet[]) {
        int min = vet[0];                       // inizializzo una variabile locale :min: (NON E' LA STESSA DEL METODO MAIN!)
        for (int i = 0; i < vet.length; i++) {  // Ciclo tutti i numeri all'interno del vettore
            if (vet[i] < min) {                 // ... controllando se il numero attuale e' inferiore all'attuale numero minimo.
                min = vet[i];                   // ... se e' cosi' sostituisco il valore di :min: con il numero attuale.
            }
        }
        return min;                             // ritorno il risultato dopo aver finito tutto il ciclo for.
    }

    public static int getMax(int vet[]) {
        int max = vet[0];                       // inizializzo una variabile locale :max: (NON E' LA STESSA DEL METODO MAIN!)
        for (int i = 0; i < vet.length; i++) {  // Ciclo tutti i numeri all'interno del vettore
            if (vet[i] > max) {                 // ... controllando se il numero attuale e' maggiore all'attuale numero massimo.
                max = vet[i];                   // ... se e' cosi' sostituisco il valore di :max: con il numero attuale.
            }
        }
        return max;                             // ritorno il risultato dopo aver finito tutto il ciclo for.
    }

    public static int getAvg(int vet[]) {       
        int sum = 0;                            // inizializzo una variabile locale :max: (NON E' LA STESSA DEL METODO MAIN!)
        for (int i = 0; i < vet.length; i++) { 
            sum+=vet[i];
        }
        return sum / vet.length+1;
    }

}