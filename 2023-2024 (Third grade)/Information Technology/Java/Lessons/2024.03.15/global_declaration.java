public class global_declaration {
    static int potenza = 1;
    public static void main(String[] args) {
        int base, esponente;
        base = 2;
        esponente = 5;
        eleva(base,esponente);
        System.out.println("La potenza e': " + potenza);
    }

    public static void eleva(int b, int esp) {
        for (int n = 1; n <= esp; n++) {
            potenza = potenza * b;
        }
    }
}
