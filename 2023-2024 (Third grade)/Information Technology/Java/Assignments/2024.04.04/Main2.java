import java.util.Random;

public class Main2 {
    static Random generator = new Random();
    public static void main(String[] args) {
        int[][] matrix1 = new int[3][4];
        int[][] matrix2 = new int[3][4];

        fill(matrix1,0,1);
        show(matrix1);

        fill(matrix2,1,100);
        show(matrix2);
    }

    static void fill(int[][] matrix, int origin, int bound) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = generator.nextInt(origin,bound);
            }
        }
    }

    static void show(int[][] matrix) {
        System.out.println("Showing matrix");
        for (int row = 0; row < matrix.length; row++) {
            System.out.print("[ ");
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.printf("%d ",matrix[row][col]);
            }
            System.out.print("]");
            System.out.println();
        }
    }
}
