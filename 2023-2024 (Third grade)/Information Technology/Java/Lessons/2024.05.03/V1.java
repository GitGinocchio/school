import java.util.Scanner;
import java.lang.Thread;


// Registro Elettronico
// Possibilità di aggiungere un nuovo voto (1o o 2o quadrimestre)
// All'aggiunta di un nuovo voto viene ricalcolata la media del quadrimestre, totale e dei voti della singola materia


public class V1 {
    static Scanner scanner = new Scanner(System.in);
    static int SleepTime = 3000;
    
    static int NumeroDocenti = 3;
    static int NumeroMaterie = 4;
    static int NumeroAlunni = 3;
    static int NumeroVoti = 10;

    static String[] alunni = {"Giulio;Tognetto;02/08/2007;3bi","Ismaele;Mattiolo;00/00/2007;3bi","Daniele;Pierantoni;00/00/2007;3bi","Nome;Cognome;00/00/2007;3ai"};
    static String[] docenti = {"Emanuele;Rosi;00/00/0000;insegnante","Irene;Campagnolo;00/00/0000;insegnante","Luca;Ponzin;00/00/0000;coordinatore"};
    static String[] materie = {"Matematica;0","Sistemi e Reti;1","Storia;2","Italiano;2"};
    static String[][][] registro;

    // Utilita' Generali -----------------------------------------------------------------

    static void clear() { 
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
    static void sleep(long millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { System.out.println(e); }
    }

    static int safeNextInt() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    static boolean isOnlyAlphabet(String s) {
        return s.matches("[a-zA-Z]+");
    }

    static boolean isOnlyAlphabetAndSpaces(String s) {
        return s.matches("[a-zA-Z ]+$");
    }

    static boolean isDate(String s) {
        return s.matches("\\d{2}[/\\-.]\\d{2}[/\\-.]\\d{2,4}");
    }

    static boolean isClass(String s) {
        return s.matches("^[1-5][a-zA-Z]{1,2}$");
    }

    static void backToMenu() {
        System.out.println("Premi un tasto qualsiasi per tornare al menu...");
        scanner.nextLine();
    }


    static boolean PersonAlreadyExistIn(String nome, String cognome, String[] array) { 
        for (int person = 0; person < array.length; person++) {
            String[] dati = array[person].split(";");
            if (nome.equals(dati[0]) && cognome.equals(dati[1])) return true;
        }
        return false; 
    }

    static boolean SubjectAlreadyExistIn(String nome, String[] array) {
        for (int subject = 0; subject < materie.length; subject++) {
            String[] dati = materie[subject].split(";");
            if (dati[0].equals(nome)) return true;
        }
        return false;
    }

    static String[] ottieniAlunno(String nome, String cognome) {
        for (int alunno = 0; alunno < alunni.length; alunno++) {
            String[] dati = alunni[alunno].split(";");
            if (dati[0].equals(nome) && dati[1].equals(cognome)) return dati;
        }

        String[] dati = {"null","null","null","null"};
        return dati;
    }

    static String[] ottieniDocente(String nome, String cognome) {
        for (int docente = 0; docente < docenti.length; docente++) {
            String[] dati = docenti[docente].split(";");
            if (dati[0].equals(nome) && dati[1].equals(cognome)) return dati;
        }
        String[] dati = {"null","null","null","null"};
        return dati;
    }

    static String[] ottieniMateria(String nome) {
        for (int materia = 0; materia < materie.length; materia++) {
            String[] dati = materie[materia].split(";");
            if (dati[0].equals(nome)) return dati;
        }
        String[] dati = {"null","null",};
        return dati;
    }

    // Inizializzazione dati ------------------------------------------------------------
    
    static void initAlunni() {
        clear();

        // Numero Alunni ----------------------------------------------------------------
        System.out.print("Inserisci il numero di alunni: ");
        NumeroAlunni = safeNextInt();
        while (NumeroAlunni <= 0) {
            clear();
            System.out.println("Il numero degli alunni deve essere maggiore di 0!");
            sleep(SleepTime);
            clear();
            System.out.println("Inserisci il numero di alunni:");
            NumeroAlunni = safeNextInt();
        }

        alunni = new String[NumeroAlunni];

        clear();

        // Dati Alunni ------------------------------------------------------------------

        for (int a = 0; a < alunni.length; a++) {

            // Nome e Cognome dell'alunno

            System.out.printf("Inserisci il nome dell'alunno numero %d: ",a+1);
            String nome = scanner.nextLine();
            System.out.printf("Inserisci il cognome dell'alunno %s: ",nome);
            String cognome = scanner.nextLine();
            while (!isOnlyAlphabet(nome) && !isOnlyAlphabet(cognome) && PersonAlreadyExistIn(nome, cognome, alunni)) {
                clear();
                System.out.println("Il nome e il cognome del docente possono contenere solo caratteri dell'alfabeto e non devono essere gia' presenti!");
                sleep(SleepTime);
                clear();
                System.out.printf("Inserisci il nome dell'alunno numero %d: ",a+1);
                nome = scanner.nextLine();
                System.out.printf("Inserisci il cognome dell'alunno %s: ",nome);
                cognome = scanner.nextLine();
            }

            clear();

            // Data di nascita dell'alunno
            
            System.out.printf("Inserisci la data di nascita dell'alunno %s %s: ",nome,cognome);
            String data = scanner.nextLine();
            while (!isDate(data)) {
                clear();
                System.out.println("La data di nascita può contenere solo numeri da 0 a 9 e caratteri separatori (\\,/,-,.)!");
                sleep(SleepTime);
                clear();
                System.out.printf("Inserisci la data di nascita dell'alunno %s %s: ",nome,cognome);
                data = scanner.nextLine();
            }

            clear();

            // Classe dell'alunno

            System.out.printf("Inserisci la classe dell'alunno %s %s: ",nome,cognome);
            String classe = scanner.nextLine();
            while (!isClass(classe)) {
                clear();
                System.out.println("La classe può essere scritta solo con una cifra da 1 a 5 e da una o due lettere!");
                sleep(SleepTime);
                clear();
                System.out.printf("Inserisci la classe dell'alunno %s %s: ",nome,cognome);
                data = scanner.nextLine();
            }

            alunni[a] = nome+";"+cognome+";"+data+";"+classe;
            clear();
        }
    }

    static void initDocenti() {
        clear();
        
        // Numero Docenti ---------------------------------------------------------------

        System.out.print("Inserisci il numero di docenti: ");
        NumeroDocenti = safeNextInt();
        while (NumeroDocenti <= 0) {
            clear();
            System.out.println("Il numero dei docenti deve essere maggiore di 0!");
            sleep(SleepTime);
            clear();
            System.out.println("Inserisci il numero di docenti: ");
            NumeroDocenti = safeNextInt();
        }

        docenti = new String[NumeroDocenti];

        clear();

        // Dati Docenti   ---------------------------------------------------------------

        for (int d = 0; d < docenti.length; d++) {

            // Nome e Cognome Docente

            System.out.printf("Inserisci il nome del docente numero %d: ",d+1);
            String nome = scanner.nextLine();
            System.out.printf("Inserisci il cognome del docente %s: ",nome);
            String cognome = scanner.nextLine();
            while (!isOnlyAlphabet(nome) && !isOnlyAlphabet(cognome) && PersonAlreadyExistIn(nome, cognome, docenti)) {
                clear();
                System.out.println("Il nome e il cognome del docente possono contenere solo caratteri dell'alfabeto e non devono essere gia' presenti!");
                sleep(SleepTime);
                clear();
                System.out.printf("Inserisci il nome del docente numero %d: ",d+1);
                nome = scanner.nextLine();
                System.out.printf("Inserisci il cognome del docente %s: ",nome);
                cognome = scanner.nextLine();
            }

            clear();

            // Data Docente

            System.out.printf("Inserisci la data di nascita del docente %s %s: ",nome,cognome);
            String data = scanner.nextLine();
            while (!isDate(data)) {
                clear();
                System.out.println("La data di nascita può contenere solo numeri da 0 a 9 e caratteri separatori (\\,/,-,.)!");
                sleep(SleepTime);
                clear();
                System.out.printf("Inserisci la data di nascita del docente %s %s: ",nome,cognome);
                data = scanner.nextLine();
            }

            clear();

            // Ruolo Docente

            System.out.printf("Inserisci il ruolo del docente %s %s: ",nome,cognome);
            String ruolo = scanner.nextLine();
            while (!isOnlyAlphabetAndSpaces(ruolo)) {
                clear();
                System.out.println("Il ruolo del docente può contenere solo caratteri dell'alfabeto e lo spazio!");
                sleep(SleepTime);
                clear();
                System.out.printf("Inserisci il ruolo del docente %s %s: ",nome,cognome);
                ruolo = scanner.nextLine();
            }

            docenti[d] = nome+";"+cognome+";"+data+";"+ruolo;

            clear();
        }
    }

    static void initMaterie() {
        clear();
        
        // Numero Materie ---------------------------------------------------------------

        System.out.print("Inserisci il numero di materie: ");
        NumeroMaterie = safeNextInt();
        while (NumeroMaterie <= 0) {
            clear();
            System.out.println("Il numero delle materie deve essere maggiore di 0!");
            sleep(SleepTime);
            clear();
            System.out.print("Inserisci il numero di materie: ");
            NumeroMaterie = safeNextInt();
        }

        clear();

        // Dati Materie -----------------------------------------------------------------

        materie = new String[NumeroMaterie];

        for (int j = 0; j < materie.length; j++) {

            // Nome Materia

            System.out.printf("Inserisci il nome della materia numero %d: ",j+1);
            String materia = scanner.nextLine();
            while (!isOnlyAlphabetAndSpaces(materia) && !SubjectAlreadyExistIn(materia,materie)) {
                clear();
                System.out.print("Il nome della materia può contenere solo caratteri dell'alfabeto e non deve essere gia' una materia esistente!");
                sleep(SleepTime);
                clear();
                System.out.printf("Inserisci il nome della materia numero %d: ",j+1);
                materia = scanner.nextLine();
            }

            clear();

            // Indice del docente che insegna questa materia

            mostraDocenti();
            System.out.printf("Inserisci l'indice del docente tra quelli salvati che insegna %s: ",materia);
            int idocente = safeNextInt();
            while (!(idocente >= 1 || idocente < docenti.length)) {
                clear();
                System.out.printf("Il numero del docente deve essere compreso tra 1 e %d", docenti.length);
                sleep(SleepTime);
                clear();
                mostraDocenti();
                System.out.printf("Inserisci l'indice del docente tra quelli salvati che insegna %s: ",materia);
                idocente = safeNextInt();
            }

            clear();
        }
    }

    static void initVoti() {
        clear();

        // Numero Voti ------------------------------------------------------------------
        
        System.out.print("Inserisci il numero di voti per quadrimestre: ");
        NumeroVoti = safeNextInt();
        while (NumeroVoti <= 0) {
            clear();
            System.out.println("Il numero di voti deve essere maggiore di 0!");
            sleep(SleepTime);
            clear();
            System.out.print("Inserisci il numero di voti per quadrimestre: ");
            NumeroVoti = safeNextInt();
        }
    }

    static void initRegistro() {
        registro = new String[NumeroMaterie][NumeroAlunni][(NumeroVoti*2)+3]; 

        for (int materia = 0; materia < registro.length; materia++) {
            for (int alunno = 0; alunno < registro[0].length; alunno++) {
                registro[materia][alunno][0] = "0.0"; // Media totale
                registro[materia][alunno][1] = "0.0"; // Media primo quadrimestre
                registro[materia][alunno][2] = "0.0"; // Media secondo quadrimestre
                for (int voto = 3; voto < registro[0][0].length;voto++) {
                    registro[materia][alunno][voto] = "0.0;true"; // Voto e se fa media
                }

            }
        }
    }

    public static void main(String[] args) {
        //initAlunni();
        //initDocenti();
        //initMaterie();
        //initVoti();
        initRegistro();

        boolean loop = true;

        while (loop) {
            clear();
            System.out.println("Cosa desideri fare?");

            System.out.println("- Materie");
            System.out.println("1) Mostra tutte le materie");
            System.out.println("2) Mostra le materie di un docente");
            System.out.println("");
            System.out.println("- Docenti");
            System.out.println("3) Mostra tutti i docenti");
            System.out.println("4) Mostra un docente");
            System.out.println("");
            System.out.println("- Alunni");
            System.out.println("5) Mostra tutti gli alunni");
            System.out.println("6) Mostra tutti gli alunni di una classe");
            System.out.println("7) Mostra un alunno");
            System.out.println("");
            System.out.println("- Voti");
            System.out.println("8) Mostra le medie di un alunno");
            System.out.println("9) Mostra i voti e la media di una materia di un alunno");
            System.out.println("10) Aggiungi un voto");
            System.out.println("11) Modifica un voto");
            System.out.println("12) Rimuovi un voto");
            System.out.println("13) Esci dal programma");
            System.out.println("");
        
            int choice = safeNextInt();

            switch (choice) {
                case 1: 
                    clear();
                    mostraMaterie();
                    backToMenu();
                case 2: 
                    clear();

                    System.out.print("Inserisci il nome del docente: ");
                    String nome = scanner.nextLine();
                    System.out.printf("Inserisci il cognome del docente %s: ",nome);
                    String cognome = scanner.nextLine();
                    while (!isOnlyAlphabet(nome) && !isOnlyAlphabet(cognome)) {
                        clear();
                        System.out.println("Il nome e il cognome del docente possono contenere solo caratteri dell'alfabeto!");
                        sleep(SleepTime);
                        clear();
                        System.out.print("Inserisci il nome del docente: ");
                        nome = scanner.nextLine();
                        System.out.printf("Inserisci il cognome del docente %s: ",nome);
                        cognome = scanner.nextLine();
                    }

                    clear();

                    mostraDocente(nome,cognome);
                    
                    System.out.println("Premi un tasto qualsiasi per tornare al menu...");
                    scanner.nextLine();
                case 3: 
                    clear();
                    mostraDocenti();
                    backToMenu();
                case 4: 
                    clear();

                case 5: 
                    break;
                case 6: 
                    break;
                case 7: 
                    break;
                case 8: 
                    break;
                case 9: 
                    break;
                case 10: 
                    clear();
                    System.out.println("Uscita dal programma...");
                    loop = false;
                    break;
                default:
                    clear();
                    System.out.println("Hai inserito un numero che non corrisponde a nessuna delle scelte disponibili!");
                    sleep(SleepTime);
                    clear();
            }
        }
        
        scanner.close();
    }

    // Mostrare gli alunni

    static void mostraAlunni() {
        for (int alunno = 0; alunno < alunni.length; alunno++) {
            String[] dati = alunni[alunno].split(";");
            System.out.printf("%d) Classe: %s\tData di Nascita: %s\tAlunno: %s %s \n",alunno+1,dati[3],dati[2],dati[0],dati[1]);
        }
    }

    static void mostraAlunni(String classe) {
        for (int alunno = 0; alunno < alunni.length; alunno++) {
            String[] dati = alunni[alunno].split(";");
            if (classe.equals(dati[3])) {
                System.out.printf("%d) Classe: %s\tData di Nascita: %s\tAlunno: %s %s \n",alunno+1,dati[3],dati[2],dati[0],dati[1]);
            }
        }
    }

    static void mostraAlunno(String nome, String cognome) {
        String[] alunno = ottieniAlunno(nome, cognome);
        if (!alunno[0].equals("null")) {
            System.out.printf("Classe: %s\tData di Nascita: %s\tAlunno: %s %s \n",alunno[3],alunno[2],alunno[0],alunno[1]);
        }
        else {
            System.out.printf("Non esiste uno studente con questo nome e cognome! \n");
        }
    }

    // Mostrare i docenti

    static void mostraDocenti() {
        for (int docente = 0; docente < docenti.length; docente++) {
            String[] dati = docenti[docente].split(";");
            System.out.printf("%d) Data di Nascita: %s\tRuolo: %s\tDocente: %s %s \n",docente+1,dati[2],dati[3],dati[0],dati[1]);
        }
    }

    static void mostraDocente(String nome, String cognome) {
        String[] docente = ottieniDocente(nome, cognome);
        if (!docente[0].equals("null")) {
            System.out.printf("Data di Nascita: %s\tRuolo: %s\tDocente: %s %s \n",docente[2],docente[3],docente[0],docente[1]);
        }
        else {
            System.out.printf("Non esiste un docente con questo nome e cognome! \n");
        }
    }

    // Mostrare le materie

    static void mostraMaterie() {
        for (int materia = 0; materia < materie.length; materia++) {
            String[] dati = materie[materia].split(";");
            String[] docente = docenti[Integer.parseInt(dati[1])].split(";");
            System.out.printf("%d) Materia: %s \t\t Docente: %s %s \n",materia+1,dati[0],docente[0],docente[1]);
        }
    }

    static void mostraMaterie(int insegnante) {
        String[] dati_docente = docenti[insegnante].split(";");
        
        System.out.printf("Le materie insegnate da %s %s sono: \n",dati_docente[0],dati_docente[1]);
        for (int materia = 0; materia < materie.length; materia++) {
            String[] dati = materie[materia].split(";");
            if (insegnante == Integer.parseInt(dati[1])) {
                System.out.printf("%d) %s \n",materia+1,dati[0]);
            }
        }
    }

    // Media voti

    static void calcolaMediaMateria(int alunno) {}

    static void calcolaMedia(int q) {}

    // Registrazione voto

    static void registraVoto() {}
    
    static void modificaVoto() {}

    static void rimuoviVoto() {}

}