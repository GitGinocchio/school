import java.lang.Math;

public class P233 {
    final static int TANTI = 8;
    final static int MAX = 30;
    public static void main(String[] args) {
        int mioVettore[] = new int[TANTI];

        for (int x=0; x < mioVettore.length; x++) {
            mioVettore[x] = (int) (MAX * Math.random());
        }
        
        for (int x = 0; x < mioVettore.length; x++) {
            System.out.print(mioVettore[x] + " ");
        }
    }

}
