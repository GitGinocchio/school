import java.util.Scanner;

public class Rettangolo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Inserisci la base del rettangolo: ");
        float base = in.nextFloat();

        System.out.println("Inserisci l'altezza del rettangolo: ");
        float altezza = in.nextFloat();

        float perimetro = (base + altezza) * 2;
        float area = base * altezza;

        System.out.println("La base del rettangolo e\': " + perimetro);
        System.out.println("L'area del rettangolo e\': " + area);

        in.close();
    }
}
