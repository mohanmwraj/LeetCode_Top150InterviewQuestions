package Matrix;

import java.util.HashSet;

/*
    https://leetcode.com/problems/valid-sudoku/description/

 */
public class validSudoku {
    /*
     * Approach: HashSet
     *
     * To check if the sudoku is valid, for each number, we must check if that number is repeated anywhere in the same row, column, or box.
     * we can use hash sets to store the previously seen numbers in each row, column, and box.
     * Via hash sets, we can
     * determine if the current number already exists in the corresponding row, column, or box in constant time.
     *
     * The index of the current box is (r / 3) * 3 + (c / 3) where / represents floor division.
     *
     */
    public boolean isValidSudoku_1(char[][] board) {
        int N = 9;

        HashSet<Character>[] rows = new HashSet[N];
        HashSet<Character>[] cols = new HashSet[N];
        HashSet<Character>[] boxes = new HashSet[N];
        for (int r = 0; r < N; r++) {
            rows[r] = new HashSet<Character>();
            cols[r] = new HashSet<Character>();
            boxes[r] = new HashSet<Character>();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                char val = board[r][c];

                // Check if the position is filled with number
                if (val == '.') {
                    continue;
                }

                // Check the row
                if (rows[r].contains(val)) {
                    return false;
                }
                rows[r].add(val);

                // Check the column
                if (cols[c].contains(val)) {
                    return false;
                }
                cols[c].add(val);

                // Check the box
                int idx = (r / 3) * 3 + c / 3;
                if (boxes[idx].contains(val)) {
                    return false;
                }
                boxes[idx].add(val);
            }
        }
        return true;
    }

    /*
        Time Complexity: O(N^2)
        Space Complexity: O(N^2)
     */

    /*
     * Approach: Array of Fixed Length
     *
     * Apart from using a hash set, we can also use an array of fixed length to check for duplicates.
     * Each position (pos) in the array represents the status of the number pos + 1.
     * Therefore, we can determine if we have already seen some number in constant time.
     *
     */
    public boolean isValidSudoku_2(char[][] board) {
        int N = 9;

        // Use an array to record the status
        int[][] rows = new int[N][N];
        int[][] cols = new int[N][N];
        int[][] boxes = new int[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // Check if the position is filled with number
                if (board[r][c] == '.') {
                    continue;
                }
                int pos = board[r][c] - '1';

                // Check the row
                if (rows[r][pos] == 1) {
                    return false;
                }
                rows[r][pos] = 1;

                // Check the column
                if (cols[c][pos] == 1) {
                    return false;
                }
                cols[c][pos] = 1;

                // Check the box
                int idx = (r / 3) * 3 + c / 3;
                if (boxes[idx][pos] == 1) {
                    return false;
                }
                boxes[idx][pos] = 1;
            }
        }
        return true;
    }
    /*
        Time Complexity: O(N^2)
        Space Complexity: O(N^2)
     */

    /*
     * Approach: Bit Masking
     *
     * Check if the ith bit of a binary number is set to 1: x & (1 << i).
     * If x & (1 << i) is nonzero, then the number i + 1 is a duplicate and the sudoku is not valid.
     * Otherwise, we haven't seen this number before, and we will use x | (1 << i) to set the ith bit from the right to signify the number i + 1 has been seen.
     *
     */
    public boolean isValidSudoku_3(char[][] board) {
        int N = 9;

        // Use a binary number to record previous occurrence
        int[] rows = new int[N];
        int[] cols = new int[N];
        int[] boxes = new int[N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // Check if the position is filled with number
                if (board[r][c] == '.') {
                    continue;
                }
                int val = board[r][c] - '0';
                int pos = 1 << (val - 1);

                // Check the row
                if ((rows[r] & pos) > 0) {
                    return false;
                }
                rows[r] |= pos;

                // Check the column
                if ((cols[c] & pos) > 0) {
                    return false;
                }
                cols[c] |= pos;

                // Check the box
                int idx = (r / 3) * 3 + c / 3;
                if ((boxes[idx] & pos) > 0) {
                    return false;
                }
                boxes[idx] |= pos;
            }
        }
        return true;
    }
    /*
        Time Complexity: O(N^2)
        Space Complexity: O(N)
     */

}
