import java.util.Scanner;

public class ES4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char currentchar;
        int count = 0;
        // numero in input
        // quadrato e cubo
        String stringa = scanner.nextLine().toLowerCase();

        for (int i = 0; i < stringa.length(); i++) {
            currentchar = stringa.charAt(i);
            if (
                currentchar == 'a' || 
                currentchar == 'e' || 
                currentchar == 'i' || 
                currentchar == 'o' ||
                currentchar == 'u') 
                {
                count+=1;

            }
        }
        System.out.println(count);


        // dispari quadrato
        // pari cubo


        scanner.close();
    }

}
