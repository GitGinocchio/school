import java.util.Scanner;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

// 1. Classe
// - 1. Bacheca
// - 2. promemoria
// - 3. Studente

// Classi = ["3bi", "3al"]
// Promemoria = {"3bi" : ["25/04/2024 Verifica di matematica",...]}
// Bacheche = {"3bi" : ["25/04/2024 avviso dalla segreteria...",...]}

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> classi = new ArrayList<>();
    static Dictionary<String, ArrayList<String>> studenti = new Hashtable<>();
    static Dictionary<String, ArrayList<String>> promemoria = new Hashtable<>();
    static Dictionary<String, ArrayList<String>> bacheche = new Hashtable<>();
    public static void main(String[] args) {
        while (true) {
            System.out.println("Scegli una delle seguenti operazioni: ");
            System.out.println("1. Aggiungi una classe");
            System.out.println("2. Mostra le classi presenti");
            System.out.println("3. Aggiungi un promemoria");
            System.out.println("4. Aggiungi una comunicazione");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addClass();
                    break;
                case 2:
                    showClasses();
                    break;
                case 3:
                    addReminder();
                    break;
                case 4:
                    addCommunication();
                    break;
                default:
                    System.out.println("Questa scelta non esiste.");
                    break;
            }
        }
    }

    static void addCommunication() {
        System.out.println("Inserisci la classe in cui vuoi inserire una comunicazione:");
        String name = scanner.nextLine();
        while (!(classi.contains(name))) {
            System.out.println("Classe non esistente, reinserisci la classe in cui vuoi inserire una comunicazione: ");
            name = scanner.nextLine();
        }

        System.out.println("Inserisci la comunicazione: ");
        String communication = scanner.nextLine();
        ArrayList<String> arraylist = bacheche.get(name);
        arraylist.add(communication);
    }

    static void addReminder() {
        System.out.println("Inserisci la classe in cui vuoi inserire un promemoria:");
        String name = scanner.nextLine();
        while (!(classi.contains(name))) {
            System.out.println("Classe non esistente, reinserisci la classe in cui vuoi inserire un promemoria: ");
            name = scanner.nextLine();
        }

        System.out.println("Inserisci il promemoria: ");
        String reminder = scanner.nextLine();
        ArrayList<String> arraylist = promemoria.get(name);
        arraylist.add(reminder);
    }

    static void addClass() {
        System.out.println("Scrivi il nome della classe:");
        String name = scanner.nextLine();
        while (classi.contains(name)) {
            System.out.println("Questa classe esiste già reinserisci il nome della classe: ");
            name = scanner.nextLine();
        }
        classi.add(name);
        ArrayList<String> arraylist = new ArrayList<>();
        promemoria.put(name,arraylist);
        bacheche.put(name,arraylist);
    }

    static void showClasses() {
        System.out.println("Ecco l'elenco delle classi: ");
        for (int i = 0; i < classi.size(); i ++) {
            System.out.printf("%d - %s\n",i+1,classi.get(i));
        }
    }
}

