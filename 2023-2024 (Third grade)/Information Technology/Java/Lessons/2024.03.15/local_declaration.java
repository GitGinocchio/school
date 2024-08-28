


public class local_declaration {
    public static void main(String[] args) {
        int base, esponente, potenza;
        potenza = 1;
        base = 2;
        esponente = 5;
        potenza = eleva(base, esponente);
        System.out.println("La potenza e': " + potenza);
    }

    public static int eleva(int b, int esp) {
        int potenza = 1;
        for (int n = 1; n <= esp; n++) {
            potenza = potenza * b;
        }
        return potenza;
    }

}