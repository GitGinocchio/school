import java.util.Scanner;


public class E4P141 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        choose(scanner);

        scanner.close();
    }

    public static void choose(Scanner scanner) {
        System.out.println("Scrivi una di queste opzioni:");
        System.out.println("- Bambino");
        System.out.println("- Studente");
        System.out.println("- Pensionato");
        System.out.println("- Altro");
        System.out.println("Chi vuole entrare al museo?");

        String input = scanner.next().toLowerCase();

        switch (input) {
            case "bambino":
                System.out.println("\nL'ingresso e' gratuito.");
                break;
            case "studente":
                System.out.println("\nL'ingresso costa 8 euro.");
                break;
            case "pensionato":
                System.out.println("\nL'ingresso costa 10 euro.");
                break;
            case "altro":
                System.out.println("\nL'ingresso costa 15 euro.");
                break;
            default:
                System.out.println("\nHai inserito una stringa che non e' tra le opzioni. \n");
                choose(scanner);
        }
    }
}