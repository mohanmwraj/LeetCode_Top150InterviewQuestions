package Matrix;
/*
    https://leetcode.com/problems/game-of-life/description/

    The board is made up of an m x n grid of cells, where each cell has an initial state:
    live (represented by a 1) or dead (represented by a 0).
    Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
    using the following four rules (taken from the above Wikipedia article):

    Any live cell with fewer than two live neighbors dies as if caused by under-population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by over-population.
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

 */

public class GameOfLife {
    /*
     * Approach: O(mn) Space Solution
     *
     * Make a copy of the original board which will remain unchanged throughout the process.
     * Iterate the cells of the Board one by one.
     * While computing the results of the rules, use the copy board and apply the result in the original board.
     *
     */
    public void gameOfLife_1(int[][] board) {

        // Neighbors array to find 8 neighboring cells for a given cell
        int[] neighbors = {0, 1, -1};

        int rows = board.length;
        int cols = board[0].length;

        // Create a copy of the original board
        int[][] copyBoard = new int[rows][cols];

        // Create a copy of the original board
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                copyBoard[row][col] = board[row][col];
            }
        }

        // Iterate through board cell by cell.
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // For each cell count the number of live neighbors.
                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);

                            // Check the validity of the neighboring cell.
                            // and whether it was originally a live cell.
                            // The evaluation is done against the copy, since that is never updated.
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (copyBoard[r][c] == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }

                // Rule 1 or Rule 3
                if ((copyBoard[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = 0;
                }
                // Rule 4
                if (copyBoard[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 1;
                }
            }
        }
    }

        /*
            Time Complexity: O(M * N)
            Space Complexity: O(M * N)
         */

    /*
     * Approach: O(1) Space Solution
     *
     * We only have two states live(1) or dead(0) for a cell.
     * We can use some dummy cell value to signify previous state of the cell along with the new changed value.
     *
     * when dead cell comes to life, we update the value as 2, in the same matrix.
     * when live cell deads, we udpate the value as -1, in the same matrix.
     *
     * In the separate iteration, if the value is greater than then the cell is alive and update as 1, else update as 0.
     *
     */

    public void gameOfLife_2(int[][] board) {
        int[] neighbors = {0, 1, -1};

        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                int liveNeighbors = 0;

                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);

                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }

                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = -1;
                }

                if (board[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 2;
                }
            }
        }

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (board[row][col] > 0) {
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }
    /*
        Time Complexity: O(M * N)
        Space Complexity: O(1)
     */
}
