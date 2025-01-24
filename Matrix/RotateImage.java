package Matrix;
/*
    https://leetcode.com/problems/rotate-image/description/
 */
public class RotateImage {
    /*
        * Approach: Rotate Groups of Four Cells
        *
        * swap elements in all four corners.
        * start last row, first column element.
     */

    public void rotate_1(int[][] matrix) {
        int n = matrix.length;
        // If there is odd number of rows, we need to take care of the middle row, so (n + 1) / 2.
        // No need to care of the middle or center column
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }
    /*
        Time Complexity: O(M)
        Space Complexity: O(1)
     */

    /*
        * Approach: Reverse on the Diagonal and then Reverse Left to Right
        *
     */
    public void rotate(int[][] matrix) {
        transpose(matrix);
        reflect(matrix);
    }

    private void transpose(int[][] matrix){
        int n = matrix.length;
        for(int i = 0; i < n; ++i){
            for(int j = i + 1; j < n; ++j){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private void reflect(int[][] matrix){
        int n = matrix.length;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n / 2; ++j){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }
    /*
        Time Complexity: O(M)
        Space Complexity: O(1)
     */
}
