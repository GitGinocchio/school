import java.util.Scanner;
import java.lang.Thread;

public class RegistroTognetto {
    static Scanner scanner = new Scanner(System.in);
    static int SleepTime = 3000;
    
    static int nteachers = 4;
    static int nsubjects = 5;
    static int nstudents = 5;
    static int nvotes = 5;

    static String[] students = {
        "Giulio;Tognetto;02/08/2007;3bi",
        "Ismaele;Mattiolo;00/00/2007;3bi",
        "Daniele;Pierantoni;00/00/2007;3bi",
        "Pietro;Casotto;00/00/0000;3al",
        "Mattia;Rizzi;00/00/0000;3al"
    };
    static String[] teachers = {
        "Irene;Campagnolo;00/00/0000;insegnante",
        "Emanuele;Rosi;00/00/0000;insegnante",
        "Luca;Ponzin;00/00/0000;coordinatore",
        "Federica;Puozzo;00/00/0000;insegnante"
    };
    static String[] subjects = {
        "Complementi della matematica;1",
        "Sistemi e Reti;0",
        "Matematica;3",
        "Italiano;2",
        "Storia;2",
    };
    static String[][][] register;

    // Metodi di utilita' generale ---------------------------------------------------------------------------

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

    static void backToMenu() {
        System.out.println("\nPremi un tasto qualsiasi per tornare al menu...");
        scanner.nextLine();
    }

    // Metodi per la validazione dell'input  ----------------------------------------------------------------

    static boolean isOnlyAlphabet(String s) {
        return s.matches("[a-zA-Z]+");
    }

    static boolean isOnlyAlphabetAndSpaces(String s) {
        return s.matches("[a-zA-Z ]+");
    }

    static boolean isDate(String s) {
        return s.matches("\\d{2}[/\\-.]\\d{2}[/\\-.]\\d{2,4}");
    }

    static boolean isClass(String s) {
        return s.matches("^[1-5][a-zA-Z]{1,2}$");
    }

    static boolean isGrade(String s) {
        return s.matches("10\\.00|[2-9]\\.(00|15|50|85)");
    }

    // Metodi per il check dell'esistenza di qualcosa -------------------------------------------------------

    static boolean PersonAlreadyExistIn(String name, String surname, String[] people) { 
        for (int person = 0; person < people.length; person++) {
            String[] data = people[person].split(";");
            if (name.equalsIgnoreCase(data[0]) && surname.equalsIgnoreCase(data[1])) return true;
        }
        return false; 
    }

    static boolean SubjectAlreadyExistIn(String name, String[] subjects) {
        for (int subject = 0; subject < subjects.length; subject++) {
            String[] dati = subjects[subject].split(";");
            if (dati[0].equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    static boolean ClassAlreadyExist(String name) {
        for (int person = 0; person < students.length; person++) {
            String[] data = students[person].split(";");
            if (data[3].equalsIgnoreCase(name)) return true;
        }
        return false; 
    }

    static boolean HasAtLeastOneGrade(int studentid, int subjectid) {
        for (int i = 3; i < register[0][0].length; i++) {
            if (!register[subjectid][studentid][i].split(";")[0].equals("0.00")) return true;
        }
        return false;
    }

    // Metodi utili per ottenere dei dati -------------------------------------------------------------------

    static String[] getPerson(String name, String surname, String[] people) { 
        for (int i = 0; i < people.length; i++) {
            String[] person = people[i].split(";");
            if (name.equalsIgnoreCase(person[0]) && surname.equalsIgnoreCase(person[1])) return person;
        }

        String[] data = {"null","null","null","null"};
        return data;
    }

    static int getPersonID(String name, String surname, String[] people) {
        for (int i = 0; i < people.length; i++) {
            String[] person = people[i].split(";");
            if (name.equalsIgnoreCase(person[0]) && surname.equalsIgnoreCase(person[1])) return i;
        }
        return -1;
    }

    static String[] getSubject(String name) {
        for (int i = 0; i < subjects.length; i++) {
            String[] subject = subjects[i].split(";");
            if (subject[0].equalsIgnoreCase(name)) return subject;
        }
        String[] subject = {"null","null"};
        return subject;
    }

    static int getSubjectID(String name) {
        for (int i = 0; i < subjects.length; i++) {
            String[] subject = subjects[i].split(";");
            if (subject[0].equalsIgnoreCase(name)) return i;
        }
        return -1;
    }

    static double getStudentTotalAvg(String name, String surname, int quarterly) {
        int studentid = getPersonID(name,surname,students);

        double sum = 0;
        int n = 0;

        for (int i = 0; i < register.length; i++) {
            double grade = Double.parseDouble(register[i][studentid][quarterly].split(";")[0]);
            if (grade != 0.00) {
                sum += Double.parseDouble(register[i][studentid][quarterly].split(";")[0]);
                n++;
            }
        }

        return sum / n;
    }

    static void calculateAvgs(int studentid, int subjectid) {
        double sum1q = 0;
        int nv1q = 0;
        for (int i = 3; i < nvotes + 3; i++) {
            String[] grade = register[subjectid][studentid][i].split(";");
            if (Boolean.parseBoolean(grade[1]) && !grade[0].equals("0.00")) {
                sum1q += Double.parseDouble(grade[0]);
                nv1q++;
            }
        }

        double avg1q;
        if (nv1q != 0) avg1q = sum1q / nv1q;
        else avg1q = 0.00;
        register[subjectid][studentid][1] = String.format("%.2f;true",avg1q).replace(',','.');

        double sum2q = 0;
        int nv2q = 0;
        for (int i = 3+nvotes; i < register[0][0].length; i++) {
            String[] grade = register[subjectid][studentid][i].split(";");
            if (Boolean.parseBoolean(grade[1]) && !grade[0].equals("0.00")) {
                sum2q += Double.parseDouble(grade[0]);
                nv2q++;
            }
        }
        double avg2q;
        if (nv2q != 0) avg2q = sum2q / nv2q;
        else avg2q = 0.00;
        register[subjectid][studentid][2] = String.format("%.2f;true",avg2q).replace(',','.');

        register[subjectid][studentid][0] = String.format("%.2f;true",(avg1q+avg2q) / 2).replace(',','.');
    }

    // Metodi utili per l'inserimento di dati ---------------------------------------------------------------

    static String[] inputExistingTeacher() {
        showTeachers();
        System.out.println("");
        System.out.printf("Inserisci il nome del docente: ");
        String name = scanner.nextLine();
        System.out.printf("Inserisci il cognome del docente: ");
        String surname = scanner.nextLine();
        while (!isOnlyAlphabet(name) || !isOnlyAlphabet(surname) || !PersonAlreadyExistIn(name, surname, teachers)) {
            clear();
            System.out.println("Il nome e il cognome del docente possono contenere solo caratteri dell'alfabeto e devono esistere nella lista!");
            sleep(SleepTime);
            clear();
            showTeachers();
            System.out.println("");
            System.out.printf("Inserisci il nome del docente: ");
            name = scanner.nextLine();
            System.out.printf("Inserisci il cognome del docente: ");
            surname = scanner.nextLine();
        }

        String[] teacher = {name, surname};
        return teacher;
    }

    static String[] inputExistingStudent() {
        showStudents();
        System.out.println("");
        System.out.printf("Inserisci il nome dello studente: ");
        String name = scanner.nextLine();
        System.out.printf("Inserisci il cognome dello studente: ");
        String surname = scanner.nextLine();
        while (!isOnlyAlphabet(name) || !isOnlyAlphabet(surname) || !PersonAlreadyExistIn(name, surname, students)) {
            clear();
            System.out.println("Il nome e il cognome dello studente possono contenere solo caratteri dell'alfabeto e devono esistere nella lista!");
            sleep(SleepTime);
            clear();
            showStudents();
            System.out.println("");
            System.out.printf("Inserisci il nome dello studente: ");
            name = scanner.nextLine();
            System.out.printf("Inserisci il cognome dello studente: ");
            surname = scanner.nextLine();
        }

        String[] student = {name, surname};
        return student;
    }

    static String inputExistingSubject() {
        showSubjects();
        System.out.printf("\nInserisci il nome della materia: ");
        String subject = scanner.nextLine();
        while (!isOnlyAlphabetAndSpaces(subject) || !SubjectAlreadyExistIn(subject,subjects)) {
            clear();
            System.out.println("La materia deve esistere nella lista");
            sleep(SleepTime);
            clear();
            showSubjects();
            System.out.printf("\nInserisci il nome della materia: ");
            subject = scanner.nextLine();
        }

        return subject;
    }

    static String inputExistingClass() {
        showClasses();
        System.out.printf("Inserisci il nome della classe: ");
        String classname = scanner.nextLine();
        while (!isClass(classname) || !ClassAlreadyExist(classname)) {
            clear();
            System.out.println("La classe può essere scritta solo con una cifra da 1 a 5 e da una o due lettere!");
            sleep(SleepTime);
            clear();
            showClasses();
            System.out.printf("Inserisci il nome della classe: ");
            classname = scanner.nextLine();
        }

        return classname;
    }

    static String inputGrade() {
        System.out.print("Inserisci il voto: ");
        String grade = scanner.nextLine();
        while (!isGrade(grade)) {
            clear();
            System.out.println("Il voti possono contenere solo parte decimale compresa tra 2 e 10 e la parte frazionaria uguale a .00 .15 .50 .85");
            sleep(SleepTime);
            clear();
            System.out.print("Inserisci il voto: ");
            grade = scanner.nextLine();
        }
        return grade;
    }

    // Inizializzazione dati --------------------------------------------------------------------------------
    
    static void initStudents() {
        clear();

        // Numero Alunni ----------------------------------------------------------------
        System.out.print("Inserisci il numero di alunni: ");
        nstudents = safeNextInt();
        while (nstudents <= 0) {
            clear();
            System.out.println("Il numero degli alunni deve essere maggiore di 0!");
            sleep(SleepTime);
            clear();
            System.out.println("Inserisci il numero di alunni:");
            nstudents = safeNextInt();
        }

        students = new String[nstudents];

        clear();

        // Dati Alunni ------------------------------------------------------------------

        for (int a = 0; a < students.length; a++) {

            // Nome e Cognome dell'alunno

            System.out.printf("Inserisci il nome dell'alunno numero %d: ",a+1);
            String nome = scanner.nextLine();
            System.out.printf("Inserisci il cognome dell'alunno %s: ",nome);
            String cognome = scanner.nextLine();
            while (!isOnlyAlphabet(nome) && !isOnlyAlphabet(cognome) && PersonAlreadyExistIn(nome, cognome, students)) {
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

            students[a] = nome+";"+cognome+";"+data+";"+classe;
            clear();
        }
    }

    static void initTeachers() {
        clear();
        
        // Numero Docenti ---------------------------------------------------------------

        System.out.print("Inserisci il numero di docenti: ");
        nteachers = safeNextInt();
        while (nteachers <= 0) {
            clear();
            System.out.println("Il numero dei docenti deve essere maggiore di 0!");
            sleep(SleepTime);
            clear();
            System.out.println("Inserisci il numero di docenti: ");
            nteachers = safeNextInt();
        }

        teachers = new String[nteachers];

        clear();

        // Dati Docenti   ---------------------------------------------------------------

        for (int teacher = 0; teacher < teachers.length; teacher++) {

            // Nome e Cognome Docente

            System.out.printf("Inserisci il nome del docente numero %d: ",teacher+1);
            String name = scanner.nextLine();
            System.out.printf("Inserisci il cognome del docente %s: ",name);
            String surname = scanner.nextLine();
            while (!isOnlyAlphabet(name) && !isOnlyAlphabet(surname) && PersonAlreadyExistIn(name, surname, teachers)) {
                clear();
                System.out.println("Il nome e il cognome del docente possono contenere solo caratteri dell'alfabeto e non devono essere gia' presenti!");
                sleep(SleepTime);
                clear();
                System.out.printf("Inserisci il nome del docente numero %d: ",teacher+1);
                name = scanner.nextLine();
                System.out.printf("Inserisci il cognome del docente %s: ",name);
                surname = scanner.nextLine();
            }

            clear();

            // Data Docente

            System.out.printf("Inserisci la data di nascita del docente %s %s: ",name,surname);
            String date = scanner.nextLine();
            while (!isDate(date)) {
                clear();
                System.out.println("La data di nascita può contenere solo numeri da 0 a 9 e caratteri separatori (\\,/,-,.)!");
                sleep(SleepTime);
                clear();
                System.out.printf("Inserisci la data di nascita del docente %s %s: ",name,surname);
                date = scanner.nextLine();
            }

            clear();

            // Ruolo Docente

            System.out.printf("Inserisci il ruolo del docente %s %s: ",name,surname);
            String role = scanner.nextLine();
            while (!isOnlyAlphabetAndSpaces(role)) {
                clear();
                System.out.println("Il ruolo del docente può contenere solo caratteri dell'alfabeto e lo spazio!");
                sleep(SleepTime);
                clear();
                System.out.printf("Inserisci il ruolo del docente %s %s: ",name,surname);
                role = scanner.nextLine();
            }

            teachers[teacher] = name+";"+surname+";"+date+";"+role;

            clear();
        }
    }

    static void initSubjects() {
        clear();
        
        // Numero Materie ---------------------------------------------------------------

        System.out.print("Inserisci il numero di materie: ");
        nsubjects = safeNextInt();
        while (nsubjects <= 0) {
            clear();
            System.out.println("Il numero delle materie deve essere maggiore di 0!");
            sleep(SleepTime);
            clear();
            System.out.print("Inserisci il numero di materie: ");
            nsubjects = safeNextInt();
        }

        clear();

        // Dati Materie -----------------------------------------------------------------

        subjects = new String[nsubjects];

        for (int j = 0; j < subjects.length; j++) {

            // Nome Materia

            System.out.printf("Inserisci il nome della materia numero %d: ",j+1);
            String subject = scanner.nextLine();
            while (!isOnlyAlphabetAndSpaces(subject) && !SubjectAlreadyExistIn(subject,subjects)) {
                clear();
                System.out.print("Il nome della materia può contenere solo caratteri dell'alfabeto e non deve essere gia' una materia esistente!");
                sleep(SleepTime);
                clear();
                System.out.printf("Inserisci il nome della materia numero %d: ",j+1);
                subject = scanner.nextLine();
            }

            clear();

            // Indice del docente che insegna questa materia

            showTeachers();
            System.out.println("");
            System.out.printf("Inserisci il nome del docente che insegna %s: ",subject);
            String name = scanner.nextLine();
            System.out.printf("Inserisci il cognome del docente che insegna %s: ",subject);
            String surname = scanner.nextLine();
            while (!isOnlyAlphabet(name) || !isOnlyAlphabet(surname) || !PersonAlreadyExistIn(name, surname, teachers)) {
                clear();
                System.out.println("Il nome e il cognome del docente possono contenere solo caratteri dell'alfabeto e devono esistere nella lista!");
                sleep(SleepTime);
                clear();
                showTeachers();
                System.out.println("");
                System.out.printf("Inserisci il nome del docente: ");
                name = scanner.nextLine();
                System.out.printf("Inserisci il cognome del docente: ");
                surname = scanner.nextLine();
            }

            int teacherid = getPersonID(name,surname,teachers);

            subjects[j] = subject + ";" + teacherid;

            clear();
        }
    }

    static void initGrades() {
        clear();

        // Numero Voti ------------------------------------------------------------------
        
        System.out.print("Inserisci il numero di voti per quadrimestre: ");
        nvotes = safeNextInt();
        while (nvotes <= 0) {
            clear();
            System.out.println("Il numero di voti deve essere maggiore di 0!");
            sleep(SleepTime);
            clear();
            System.out.print("Inserisci il numero di voti per quadrimestre: ");
            nvotes = safeNextInt();
        }
    }

    static void initRegister() {
        register = new String[nsubjects][nstudents][(nvotes*2)+3]; 

        for (int subject = 0; subject < register.length; subject++) {
            for (int student = 0; student < register[0].length; student++) {
                register[subject][student][0] = "0.00;true"; // Media totale
                register[subject][student][1] = "0.00;true"; // Media primo quadrimestre
                register[subject][student][2] = "0.00;true"; // Media secondo quadrimestre
                for (int grade = 3; grade < register[0][0].length;grade++) {
                    register[subject][student][grade] = "0.00;true"; // Voto e se fa media
                }

            }
        }
    }

    // Metodo main e relativi metodi dei singoli comandi --------------------------------------------------

    public static void main(String[] args) {
        //initStudents();       // Se si toglie il commento a questo metodo si avvia l'inizializzazione degli studenti
        //initTeachers();       // Se si toglie il commento a questo metodo si avvia l'inizializzazione degli insegnanti
        //initSubjects();       // Se si toglie il commento a questo metodo si avvia l'inizializzazione delle materie
        //initGrades();         // Se si toglie il commento a questo metodo si avvia l'inizializzazione della matrice per i voti
        //initRegister();

        boolean loop = true;

        while (loop) {
            clear();
            System.out.println("Cosa desideri fare?");

            System.out.println("\n- Materie");
            System.out.println("1) Mostra tutte le materie");
            System.out.println("2) Mostra le materie di un docente");
            System.out.println("\n- Docenti");
            System.out.println("3) Mostra tutti i docenti");
            System.out.println("\n- Alunni");
            System.out.println("4) Mostra tutti gli alunni");
            System.out.println("5) Mostra tutti gli alunni di una classe");
            System.out.println("\n- Voti");
            System.out.println("6) Mostra medie totali di un alunno");
            System.out.println("7) Mostra voti e medie delle materie di un alunno");
            System.out.println("8) Aggiungi un voto");
            System.out.println("9) Rimuovi un voto");
            System.out.println("\n- Altro");
            System.out.println("0) Esci dal programma");
            System.out.println("");
        
            int choice = safeNextInt();

            switch (choice) {
                case 1:
                    clear();
                    showSubjects();
                    backToMenu();
                    break;
                case 2:
                    clear();
                    showTeacherSubjects();
                    backToMenu();
                    break;
                case 3:
                    clear();
                    showTeachers();
                    backToMenu();
                    break;
                case 4:
                    clear();
                    showStudents();
                    backToMenu();
                    break;
                case 5:
                    clear();
                    showClassStudents();
                    backToMenu();
                    break;
                case 6:
                    clear();
                    showStudentAvg();
                    backToMenu();
                    break;
                case 7:
                    clear();
                    showStudentGrades();
                    backToMenu();
                    break;
                case 8:
                    clear();
                    addGrade();
                    backToMenu();
                    break;
                case 9:
                    clear();
                    delGrade();
                    backToMenu();
                    break;
                case 0:
                    clear();
                    System.out.println("Uscita dal programma...");
                    sleep(SleepTime);
                    clear();
                    loop = false;
                    break;
                default:
                    clear();
                    System.out.println("Hai inserito un indice che non corrisponde a nessuna delle scelte disponibili!");
                    sleep(SleepTime);
            }
        } 

    }

    static void showSubjects() {
        for (int i = 0; i < subjects.length; i++) {
            String[] subject = subjects[i].split(";");
            // String[] teacher = teachers[Integer.parseInt(subject[1])].split(";");
            // System.out.printf("%d) Materia: %s \t\t Docente: %s %s \n",i+1,subject[0],teacher[0],teacher[1]);
            System.out.printf("%d) Materia: %s \n",i+1,subject[0]);
        }
    }

    static void showTeachers() {
        for (int i = 0; i < teachers.length; i++) {
            String[] teacher = teachers[i].split(";");
            System.out.printf("%d) Data di Nascita: %s\tRuolo: %s\tDocente: %s %s \n",i+1,teacher[2],teacher[3],teacher[0],teacher[1]);
        }
    }

    static void showStudents() {
        for (int i = 0; i < students.length; i++) {
            String[] student = students[i].split(";");
            System.out.printf("%d) Classe: %s\tData di Nascita: %s\tAlunno: %s %s \n",i+1,student[3],student[2],student[0],student[1]);
        }
    }

    static void showStudents(String classname) {
        for (int i = 0; i < students.length; i++) {
            String[] student = students[i].split(";");
            if (classname.equalsIgnoreCase(student[3])) {
                System.out.printf("%d) Classe: %s\tData di Nascita: %s\tAlunno: %s %s \n",i+1,student[3],student[2],student[0],student[1]);
            }
        }
    }

    static void showClasses() {
        String[] alreadyprinted = new String[students.length];
        boolean canprint = true;
        int index = 0;

        System.out.print("Elenco delle classi salvate:\n");

        for (int i = 0; i < students.length; i++) {
            String[] student = students[i].split(";");

            canprint = true;
            for (int j = 0; j < alreadyprinted.length; j++) {
                if (student[3].equalsIgnoreCase(alreadyprinted[j])) {
                    canprint = false;
                    break;
                }
            }

            if (canprint) { 
                System.out.printf("- %s\n",student[3]);
                alreadyprinted[index] = student[3];
                index++;
            }
        }
        System.out.print("\n");

    }

    static void showTeacherSubjects() {
        String[] teacherinfo = inputExistingTeacher();
        clear();

        String[] teacher = getPerson(teacherinfo[0],teacherinfo[1], teachers);
        int teacherid = getPersonID(teacherinfo[0], teacherinfo[1], teachers);
        
        System.out.printf("Le materie insegnate da %s %s sono: \n",teacher[0],teacher[1]);
        for (int i = 0; i < subjects.length; i++) {
            String[] subject = subjects[i].split(";");
            if (teacherid == Integer.parseInt(subject[1])) {
                System.out.printf("%d) %s \n",i+1,subject[0]);
            }
        }

    }

    static void showClassStudents() {
        String classname = inputExistingClass();
        clear();
        showStudents(classname);
    }

    static void showStudentAvg() {
        String[] student = inputExistingStudent();

        double totalavg = getStudentTotalAvg(student[0],student[1],0);
        double firstquartavg = getStudentTotalAvg(student[0],student[1],1);
        double secondquartavg = getStudentTotalAvg(student[0],student[1],2);
        
        clear();
        
        System.out.printf("Medie dell'alunno %s %s \n\n",student[0],student[1]);
        System.out.printf("Media totale: %.2f \n",totalavg);
        System.out.printf("Media primo quadrimestre: %.2f \n",firstquartavg);
        System.out.printf("Media secondo quadrimestre: %.2f \n",secondquartavg);
    }

    static void showStudentGrades() {
        String[] student = inputExistingStudent();

        int studentid = getPersonID(student[0], student[1], students);

        clear();

        System.out.printf("Voti e medie suddivise per materia dell'alunno %s %s: \n\n",student[0],student[1]);

        System.out.print("\t MTt  \t M1Q  \t M2Q  \t");
        for (int i = 3; i < (nvotes*2)+3; i++) { System.out.printf(" V%d  \t",i-2); }
        System.out.println("");

        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("%.3s \t",subjects[i].split(";")[0]);

            for (int j = 0; j < register[0][0].length; j++) {
                String[] grade = register[i][studentid][j].split(";");

                if (!Boolean.parseBoolean(grade[1])) {
                    System.out.printf("(%s) \t",grade[0]);
                }
                else {
                    System.out.printf(" %s  \t",grade[0]);
                }
            }

            System.out.print("\n");
        }
    }

    static void showStudentGrades(String name, String surname) {
        int studentid = getPersonID(name, surname, students);

        clear();

        System.out.printf("Voti e medie suddivise per materia dell'alunno %s %s: \n\n",name,surname);

        System.out.print("\t MTt  \t M1Q  \t M2Q  \t");
        for (int i = 3; i < (nvotes*2)+3; i++) { System.out.printf(" V%d  \t",i-2); }
        System.out.println("");

        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("%.3s \t",subjects[i].split(";")[0]);

            for (int j = 0; j < register[0][0].length; j++) {
                String[] grade = register[i][studentid][j].split(";");

                if (!Boolean.parseBoolean(grade[1])) {
                    System.out.printf("(%s) \t",grade[0]);
                }
                else {
                    System.out.printf(" %s  \t",grade[0]);
                }
            }

            System.out.print("\n");
        }
    }

    static void addGrade() {
        String[] student = inputExistingStudent();
        int studentid = getPersonID(student[0], student[1], students);
        clear();

        String subject = inputExistingSubject();
        int subjectid = getSubjectID(subject);
        clear();

        System.out.println("Scegli tra primo e secondo quadrimestre: ");
        String quart = scanner.nextLine().toLowerCase();
        while (!quart.equalsIgnoreCase("primo") && !quart.equalsIgnoreCase("secondo")) {
            System.out.println("Gli unici input accettati sono \"primo\" e \"secondo\" quadrimestre");
            sleep(SleepTime);
            clear();
            System.out.println("Scegli tra primo e secondo quadrimestre: ");
            quart = scanner.nextLine().toLowerCase();
        }

        int max;
        int min = 3;
        if (quart.equalsIgnoreCase("primo")) {
            max = nvotes + min;
        } 
        else {
            min = nvotes + min;
            max = register[0][0].length-1;
        }
        clear();

        String grade = inputGrade();

        clear();

        System.out.println("Scrivi \"si\" se vuoi che il voto venga considerato nella media altrimenti \"no\": ");
        String ifavg = scanner.nextLine().toLowerCase();
        while (!ifavg.equalsIgnoreCase("si") && !ifavg.equalsIgnoreCase("no")) {
            System.out.println("Gli unici input accettati sono \"si\" e \"no\"");
            sleep(SleepTime);
            clear();
            System.out.println("Scrivi \"si\" se vuoi che il voto venga considerato nella media altrimenti \"no\": ");
            ifavg = scanner.nextLine().toLowerCase();
        }

        if (ifavg.equalsIgnoreCase("si")) ifavg = "true";
        else ifavg = "false";

        boolean saved = false;
        for (int i = min; i < max; i++) {
            if (register[subjectid][studentid][i].equalsIgnoreCase("0.00;true")) {
                register[subjectid][studentid][i] = grade + ";" + ifavg;
                saved = true;
                break;
            }
        }

        clear();

        if (!saved) {
            System.out.printf("Voto non salvato: Limite massimo di voti raggiunto per il %s quadrimestre\n\n",quart);
        }
        else {
            calculateAvgs(studentid, subjectid);
            System.out.printf("Voto salvato con successo: \n\n");

            System.out.printf("Alunno: %s %s \n",student[0],student[1]);
            System.out.printf("Materia: %s \n", subject);
            System.out.printf("Valore: %s \n", grade);
            System.out.printf("Fa media: %s\n", ifavg);
        }

    }

    static void delGrade() {
        String[] student = inputExistingStudent();
        int studentid = getPersonID(student[0], student[1], students);
        clear();

        String subject = inputExistingSubject();
        int subjectid = getSubjectID(subject);
        clear();

        if (!(HasAtLeastOneGrade(studentid,subjectid))) {
            clear();
            System.out.printf("Lo studente %s %s non ha voti salvati in %s \n\n",student[0],student[1],subject);
            return;
        }
        
        showStudentGrades(student[0],student[1]);

        System.out.print("\nInserisci il numero del voto da eliminare: ");
        int grade = safeNextInt();
        while (grade+2 < 1 || grade+2 > register[0][0].length-3 || register[subjectid][studentid][grade+2].split(";")[0].equalsIgnoreCase("0.00")) {
            clear();
            System.out.printf("Il numero del voto deve essere compreso tra 1 e %d e il voto deve essere diverso da 0.0", register[0][0].length-3);
            sleep(SleepTime);
            clear();
            showStudentGrades(student[0],student[1]);
            System.out.print("\nInserisci il numero del voto da eliminare: ");
            grade = safeNextInt();
        }

        clear();

        System.out.printf("Voto numero %d eliminato con successo\n\n",grade);
        calculateAvgs(studentid, subjectid);
    }
}
