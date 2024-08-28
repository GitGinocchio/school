import java.util.Scanner;

public class E5P142 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final float MAXPESOSACCHETTO = 5.0f;
        final float MAXPESOCASSETTA = 30.0f;
        final float MAXWEIGHT = MAXPESOCASSETTA * 2 + MAXPESOSACCHETTO * 4;
        System.out.print("Inserisci il peso in kg delle patate che vuoi acquistare: ");
        float weight = scanner.nextFloat();

        int sacchetti_necessari = 0;
        float peso_rimanente;

        if (weight > MAXWEIGHT) {
            System.out.printf("il peso inserito supera il limite massimo di %.3f chilogrammi!",MAXWEIGHT);
        }
        else if (weight > MAXPESOCASSETTA) {
            int cassette_necessarie = (int) Math.ceil(weight / MAXPESOCASSETTA);

            if (cassette_necessarie > 2) {
                cassette_necessarie = 2;
                peso_rimanente = weight - 2 * MAXPESOCASSETTA;

                if (peso_rimanente > 0) { sacchetti_necessari = (int) Math.ceil(peso_rimanente / MAXPESOSACCHETTO); }
            } 
            else {
                peso_rimanente = weight - cassette_necessarie * MAXPESOCASSETTA;

            }

            System.out.printf("Cassette necessarie: %d; Sacchetti necessari: %d\n\n",cassette_necessarie,sacchetti_necessari);
            System.out.printf("Se non sei sicuro basta fare 30 * %d + 5 * %d, che e' uguale a: %d ",cassette_necessarie,sacchetti_necessari,30 * cassette_necessarie + 5 * sacchetti_necessari);
        }
        else if (weight > MAXPESOSACCHETTO) {
            sacchetti_necessari = (int) Math.ceil(weight / MAXPESOSACCHETTO);
        }

        scanner.close();

    }
}
