import java.util.Scanner;

public class Circonferenza {
    final static double PI = 3.1415;
    public static void main(String[] args) {
        double raggio;
        double circonferenza, area;
        raggio = leggiDati();
        circonferenza = calcolaCirconferenza(raggio);
        area = calcolaArea(raggio);
        stampaRisultati(circonferenza, area);
    }

    static double leggiDati() {
        Scanner in = new Scanner(System.in);
        double dato;
        do{
            System.out.print("Inserisci il raggio: ");
            dato = in.nextDouble();
        } while (dato <= 0);
        in.close();
        return dato;
    }

    static double calcolaCirconferenza(double raggio) {
        double numero;
        numero = 2 * raggio * PI;
        return numero;
    }

    static double calcolaArea(double raggio) {
        double numero;
        numero = raggio * raggio * PI;
        return numero;
    }

    static void stampaRisultati(double dato1, double dato2) {
        System.out.printf("La misura del circonferenza è: %.2f%n",dato1);
        System.out.printf("La misura dell'area è: %.2f%n",dato2);
    }
}