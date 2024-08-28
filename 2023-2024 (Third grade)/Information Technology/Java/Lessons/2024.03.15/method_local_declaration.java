

public class method_local_declaration { 
    public static void main(String[] args) {
        int n, base, esponente, potenza;
        potenza = 1;
        base = 2;
        esponente = 5;

        for (n = 1; n <= esponente; n++) {
            potenza = potenza * base;
        }
        System.out.println("La potenza e': " + potenza);
    }
}