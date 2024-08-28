import java.util.Scanner;

public class Cilindro {
    final static double PI = 3.1415;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double raggio, altezza;
        double circonferenza, area;

        raggio = leggiDato(scanner,"Inserisci il raggio del cilindro: ");
        altezza = leggiDato(scanner,"Inserisci l'altezza del cilindro: ");
        scanner.close();

        circonferenza = calcolaCirconferenza(raggio);
        area = calcolaVolume(raggio,altezza);

        stampaRisultati(circonferenza, area);
    }

    static double leggiDato(Scanner scanner, String s) {
        double dato;
        do{
            System.out.print(s);
            dato = scanner.nextDouble();
        } while (dato <= 0);
        return dato;
    }


    static double calcolaCirconferenza(double raggio) {
        double circonferenza;
        circonferenza = 2 * raggio * PI;
        return circonferenza;
    }

    static double calcolaArea(double raggio, double altezza) {
        double volume;
        volume = (2 * raggio * PI * altezza) + (2 * PI * raggio * raggio);
        return volume;
    }

    static double calcolaVolume(double raggio, double altezza) {
        double volume;
        volume = raggio * raggio * PI * altezza;
        return volume;
    }

    static void stampaRisultati(double circonferenza, double volume) {
        System.out.printf("La misura del circonferenza è: %.2f%n",circonferenza);
        System.out.printf("La misura dell'area è: %.2f%n",volume);
    }
}