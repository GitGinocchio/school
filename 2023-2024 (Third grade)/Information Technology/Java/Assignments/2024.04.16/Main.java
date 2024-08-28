


public class Main {
    public static void main(String[] args) {
        int n = 5;
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) { 
            matrix[0][i] = 1; 
            matrix[i][0] = 1;
            matrix[1][i] = i+1;
            matrix[i][1] = i+1;
        }


        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n; row++) {
                System.out.printf("%d ",matrix[col][row]);
            }
            System.out.println();
        }
        /*
        1 1 1 1 1
        1 0 0 0 0
        1 0 0 0 0
        1 0 0 0 0
        1 0 0 0 0
        */

        /*
        1 1 1 1 
        1 2 3 4
        1 3 6
        1 4 
        */
        /*
        matrix[1][1] = matrix[0][1] + matrix[1][0];

        matrix[1][2] = matrix[0][2] + matrix[1][1];
        matrix[2][1] = matrix[1][1] + matrix[2][0];

        matrix[]
        */
    }
}