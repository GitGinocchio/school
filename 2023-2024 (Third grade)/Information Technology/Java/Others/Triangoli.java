import java.util.Scanner;

public class Triangoli {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il primo lato:");
        float lato1 = scanner.nextFloat();
        System.out.println("Inserisci il secondo lato:");
        float lato2 = scanner.nextFloat();
        System.out.println("Inserisci il terzo lato:");
        float lato3 = scanner.nextFloat();


        if (lato1 == lato2 && lato2 == lato3) {
            System.out.println("Il triangolo è equilatero");
        } 
        else if (lato1 == lato2 || lato1 == lato3 || lato2 == lato3) {
            System.out.println("Il triangolo è isoscele");
        }
        else {
            System.out.println("Il triangolo è scaleno");
        }


        scanner.close();
    }
}



