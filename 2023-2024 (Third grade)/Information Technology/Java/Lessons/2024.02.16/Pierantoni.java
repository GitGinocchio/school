import java.util.Scanner;

public class Pierantoni {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = {"giorgio", "daniele","alberto","mattia","pietro"};
        int[] ages = {10, 20, 15, 18,16};
        boolean finded = false;

        String name = scanner.next().toLowerCase();

        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().equals(name)) {
                System.out.println("Il nome è presente nella lista.");
                System.out.printf("L'età di %s è %d",name,ages[i]);
                finded = true;
                break;
            }
        }
        if (!finded) { System.out.printf("Non è stato trovato nessun alunno per %s",name); }

        scanner.close();
    }
}
