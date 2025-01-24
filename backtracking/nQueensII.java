package backtracking;

import java.util.HashSet;
import java.util.Set;

/*
    https://leetcode.com/problems/n-queens-ii/description/

    The n-queens puzzle is the problem of placing n queens on an n x n chessboard
    such that no two queens attack each other.

    Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 */
public class nQueensII {

    /*
        * Approach: Backtracking
        *
        * A queen can be attacked if another queen is in any of the 4 following positions:
        *   on the same row,
        *   on the same column,
        *   on the same diagonal,
        *   or on the same anti-diagonal.
        *
        * To make sure that we only place 1 queen per row, we will pass an integer argument row into backtrack,
        * and will only place one queen during each call.
        *
        * To make sure we only place 1 queen per column, we will use a set.
        *
        * For each square on a given diagonal, the difference between the row and column indexes (row - col) will be constant.
        *
        * For each square on a given anti-diagonal, the sum of the row and column indexes (row + col) will be constant.
        *
     */
    private int size;
    public int totalNQueeens(int n){
        this.size = n;
        return backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    private int backtrack(int row, Set<Integer> diagonals,
                                    Set<Integer> antiDiagonals,
                                    Set<Integer> cols){
        if(row == size) return 1;

        int solutions = 0;
        for(int col = 0; col < size; ++col){
            int currentDiagonal = row - col;
            int currentAntiDiagonal = row + col;

            if(cols.contains(col) ||
                    diagonals.contains(currentDiagonal) ||
                    antiDiagonals.contains(antiDiagonals)){
                continue;
            }

            cols.add(col);
            diagonals.add(currentDiagonal);
            antiDiagonals.add(currentAntiDiagonal);

            solutions += backtrack(row + 1, diagonals, antiDiagonals, cols);

            cols.remove(col);
            diagonals.remove(currentDiagonal);
            antiDiagonals.remove(currentAntiDiagonal);
        }

        return solutions;
    }
    /*
        Time Complexity: O(N!)
        Space Complexity: O(N)
     */
}
