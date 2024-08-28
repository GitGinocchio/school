import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static final int ng = 14; // Numero dei giorni nei quali effettuare le misurazioni
    static final int nm = 15; // Numero delle misure che si possono fare in una giornata
    static int[][] data = new int[ng][nm];

    public static void main(String[] args) {
        generateRandomData(0, 35);
        showData();
        int[] maxvalue = getMaxValue();
        System.out.printf("\n\nIl valore massimo registrato è %d il giorno numero %d all'ora numero %d \n",maxvalue[0],maxvalue[1],maxvalue[2]);

        for (int day = 0; day < data.length; day++) {
            double avg = getDayAverage(day);
            System.out.printf("Il valore medio per il giorno numero %d è %f\n",day+1,avg);
        }

        scanner.close();
    }

    // Metodi per gestire i dati

    static void generateRandomData(int mintemp, int maxtemp) {
        for (int day = 0; day < data.length; day++) {
            for (int hour = 0; hour < data[day].length; hour++) {
                data[day][hour] =  random.nextInt(mintemp,maxtemp+1);
            }
        }
    }

    static void addDataFromInput() {
        for (int day = 0; day < data.length; day++) {
            System.out.printf("Inserisci i dati per il %d giorno: \n",day+1);
            for (int hour = 0; hour < data[day].length; hour++) {
                System.out.printf("%d ora: ",hour+1);
                data[day][hour] =  scanner.nextInt();
            }
        }
    }

    static void clearData() {  data = new int[ng][nm]; }

    // Metodi per esaminare i dati

    static void showData() {
        System.out.print("\nHours | ");
        for (int hour = 0; hour < data[0].length; hour++) {
            System.out.printf("%d\t",hour+1);
        }
        for (int day = 0; day < data.length; day++) {
            System.out.printf("\nDay %d | ",day+1);
            for (int hour = 0; hour < data[day].length; hour++) {
                System.out.printf("%d\t",data[day][hour]);
            }
        }

    }

    static int[] getMaxValue() {
        int[] max = new int[3]; // [Valore, Giorno, Ora]
        max[0] = data[0][0];

        for (int day = 0; day < data.length; day++) {
            for (int hour = 0; hour < data[day].length; hour++) {
                if (max[0] < data[day][hour]) {
                    max[0] = data[day][hour];
                    max[1] = day;
                    max[2] = hour;
                }
            }
        }
        return max;
    }

    static int[] getDayMaxValue(int day) {
        int[] max = new int[3]; // [Valore, Giorno, Ora]
        max[0] = data[day][0];
        
        for (int hour = 0; hour < data[day].length; hour++) {
            if (max[0] < data[day][hour]) {
                max[0] = data[day][hour];
                max[1] = day;
                max[2] = hour;
            }
        }
        return max;
    }

    static double getAverage() {
        int sum = 0;

        for (int day = 0; day < data.length; day++) {
            for (int hour = 0; hour < data[day].length; hour++) {
                sum+= data[day][hour];
            }
        }
        return sum / data[0].length;
    }

    static double getDayAverage(int day) {
        int sum = 0; 
        for (int hour = 0; hour < data[day].length; hour++) {
            sum+= data[day][hour];
        }
        return sum / data[day].length;
    }

}
