package Matrix;

import java.util.HashSet;
import java.util.Set;

/*
    https://leetcode.com/problems/set-matrix-zeroes/description/

    if an element is 0, set its entire row and column to 0's.
 */
public class setMatrixZeros {
    /*
        * Approach: Additional Memory Approach
        *
        * If any cell of the matrix has a zero we can record its row and column number.
            if cell[i][j] == 0 {
                 row_set.add(i)
                 column_set.add(j)
             }
        * All the cells of this recorded row and column can be marked zero in the next iteration.
         if r in row_set or c in column_set {
             cell[r][c] = 0
         }
     */
    public void setZeroes_1(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> cols = new HashSet<Integer>();

        // Essentially, we mark the rows and columns that are to be made zero
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        // Iterate over the array once again and using the rows and cols sets, update the elements.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    /*
        Time Complexity: O(M * N)
        Space Complexity: O(M + N)
     */

    /*
        * Approach: O(1) Space, Efficient Solution
        *
        * The idea is that we can use the first cell of every row and column as a flag.
        * This flag would determine whether a row or column has been set to zero.
        * This means for every cell instead of going to M+N cells and setting it to zero we just set the flag in two cells.
        *
                if cell[i][j] == 0 {
                    cell[i][0] = 0
                    cell[0][j] = 0
                }
     */

    public void setZeroes_2(int[][] matrix) {
        boolean isCol = false;
        int r = matrix.length;
        int c = matrix[0].length;

        for(int i = 0; i < r; ++i){
            // Since first cell for both first row and first column is the same i.e. matrix[0][0]
            // We can use an additional variable for either the first row/column.
            // For this solution we are using an additional variable for the first column
            // and using matrix[0][0] for the first row.
            if(matrix[i][0] == 0){
                // As per the approach, if we want to mark the cell of matrix[0][0] as 0, it holds both row and col.
                // we need to separate, if matrix[0][0] == 0, means first row is 0, isCol variable indicates first column is 0.
                isCol = true;
            }
            for(int j = 1; j < c; ++j){
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for(int i = 1; i < r; ++i){
            for(int j = 1; j < c; ++j){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        if(matrix[0][0] == 0){
            for(int j = 0; j < c; ++j){
                matrix[0][j] = 0;
            }
        }

        if(isCol){
            for(int i = 0; i < r; ++i){
                matrix[i][0] = 0;
            }
        }
    }
    /*
        Time Complexity: O(M * N)
        Space Complexity: O(1)
     */
}
