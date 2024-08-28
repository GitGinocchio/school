import java.util.Scanner;

public class E {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int age = scanner.nextInt();


        if (age > 10 && age < 80) {
            System.out.println("Ingresso a pagamento");
        } else {
            System.out.println("Ingresso gratuito");
        }


        scanner.close();
    }

}
