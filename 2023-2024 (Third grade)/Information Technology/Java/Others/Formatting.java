import java.text.DecimalFormat;

public class Formatting {
    public static void main(String[] args) {
        // format: "%[flag][convertitore]"

        // --------------------------------------------------
        
        // %d numero decimale intero
        int year = 2023;
        int month = 11;
        int day = 28;
        System.out.printf("%d-%d-%d \n", day,month,year);
        
        // --------------------------------------------------
        
        // %0x x caratteri preceduti da 0
        System.out.printf("%03d-%03d-%03d \n", day,month,year);
        
        // --------------------------------------------------
        // %f Un numero float

        final double PI = 3.141593;
        System.out.printf("%f \n",PI);

        // --------------------------------------------------
        // %.xf x cifre dopo la virgola

        // (da notare il fatto che viene approssimato il numero...)
        System.out.printf("%.3f \n",PI);

        // --------------------------------------------------
        // %x.yf x cifre totali di cui y dopo la virgola

        double num = 2255242.457634;
        System.out.printf("%10.5f \n",num);

        // --------------------------------------------------
        // Custom formats

        // 0 Cifre decimali (dopo la virgola).
        DecimalFormat format0 = new DecimalFormat("0");
        System.out.println(format0.format(PI));

        // 2 Cifre decimali (dopo la virgola).
        DecimalFormat format1 = new DecimalFormat("0.##");
        System.out.println(format1.format(PI));
    }

}
