import java.util.Scanner;

public class Calcolatrice {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        float result;

        System.out.print("Inserisci il primo numero: ");
        float n1 = scanner.nextFloat();
        System.out.print("Scrivi un operatore [ (+), (-), (/), (*) ]: ");
        char operator = scanner.next().charAt(0);

        /*
        - Altro metodo disponibile

        String test = scanner.next();
        while (test.length() > 1) { // la stringa è composta da più di un carattere
            // reinserisci il carattere
            test = scanner.next();
        }

        */

        System.out.print("Inserisci il secondo numero: ");
        float n2 = scanner.nextFloat();

        switch (operator) {
            case '+':
                result = n1 + n2;
                System.out.println("Il risultato della somma è: " + result);
                break;
            case '-':
                result = n1 - n2;
                System.out.println("Il risultato della differenza è: " + result);
                break;
            case '/':
                result = n1 / n2;
                System.out.println("Il risultato della divisione è: " + result);
                break;
            case '*':
                result = n1 * n2;
                System.out.println("Il risultato della moltiplicazione è: " + result);
                break;
            default:
                System.out.println("Operatore inserito non corretto.");
                break;
        }

        /*
        if (operator == '+') {
            result = n1 + n2;
            System.out.println("Il risultato della somma è: " + result);
        }
        else if (operator == '-') {
            result = n1 - n2;
            System.out.println("Il risultato della differenza è: " + result);
        }
        else if (operator == '/') {
            result = n1 / n2;
            System.out.println("Il risultato della divisione è: " + result);
        }
        else if (operator == '*') {
            result = n1 * n2;
            System.out.println("Il risultato della moltiplicazione è: " + result);
        }
        else {
            System.out.println("Operatore inserito non corretto.");
        }
        */
        
        scanner.close();
    }
}
