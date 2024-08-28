import java.util.Scanner;



public class MaxMinAvg {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Inserisci il primo numero: ");
        int n1 = in.nextInt();
        System.out.print("Inserisci il secondo numero: ");
        int n2 = in.nextInt();
        System.out.print("Inserisci il terzo numero: ");
        int n3 = in.nextInt();

        int max = getmax(n1,n2);
        int maxIII = getmaxIII(n1,n2,n3);
        System.out.println("\nIl maggiore dei primi due numeri è: " + max);
        System.out.println("\nIl maggiore dei tre numeri è: " + maxIII);
        // int min = getmin(n1,n2);         // ▼ ▼ ▼ Se non voglio salvare il valore della variabile posso fare così
        System.out.println("\nIl minore è: " + getmin(n1,n2));
        System.out.println("\nLa media è: " + getavg(n1,n2));
        in.close();
    }

    static int getmaxIII(int num1, int num2, int num3) {
        return getmax(getmax(num1,num2),num3);
    }

    static int getmax(int num1,int num2) {
        if (num1 > num2) {
            return num1;
        }
        else {
            return num2;
        }
    }

    static int getmin(int num1, int num2) {
        if (num1 < num2) {
            return num1;
        }
        else {
            return num2;
        }
    }

    static float getavg(int num1, int num2) {
        return (float) (num1 + num2) / 2;
    }

    static float getavgIII(int num1, int num2, int num3) {
        return (float) (num1 + num2 + num3) / 3
    }

}
