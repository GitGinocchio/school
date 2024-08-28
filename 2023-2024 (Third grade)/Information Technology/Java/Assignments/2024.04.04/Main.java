import java.util.Random;

public class Main {
    static Random generator = new Random();
    static int rows = 3; // 3
    static int cols = 4; // 4
    public static void main(String[] args) {
        int[][] matrix1 = new int[cols][rows];
        int[][] matrix2 = new int[cols][rows];
        
        fill(matrix1,0,1);
        show(matrix1);

        fill(matrix2,1,100);
        show(matrix2);
    }

    static void fill(int[][] matrix, int origin, int bound) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[col][row] = generator.nextInt(origin,bound);
            }
        }
    }

    static void show(int[][] matrix) {
        System.out.println("Showing matrix");
        for (int row = 0; row < rows; row++) {
            System.out.print("[ ");
            for (int col = 0; col < cols; col++) {
                System.out.printf("%d ",matrix[col][row]);
            }
            System.out.print("]");
            System.out.println();
        }
    }
}