package backtracking;
/*
    https://leetcode.com/problems/word-search/

    Given an m x n grid of characters board and a string word, return true if word exists in the grid.


 */
public class wordSearch {
    /*
        * Approach: Backtracking
        *
     */
    private char[][] board;
    private int ROWS;
    private int COLS;

    public boolean exist(char[][] board, String word){
        this.board = board;
        this.ROWS = board.length;
        this.COLS = board[0].length;

        for(int row = 0; row < ROWS; ++row){
            for(int col = 0; col < COLS; ++col){
                if(backtrack(0, row, col, word)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrack(int index, int row, int col, String word){
        if(index >= word.length()) return true;

        if(row < 0 || row == this.ROWS || col < 0 || this.COLS == col ||
                this.board[row][col] != word.charAt(index)){
            return false;
        }

        this.board[row][col] = '#';

        int[] rowOffsets = {0, 1, 0, -1};
        int[] colOffsets = {1, 0, -1, 0};
        for(int i = 0; i < 4; ++i){
            if(backtrack(index + 1, row + rowOffsets[i],
                                col + colOffsets[i], word)){
                return true;
            }
        }
        this.board[row][col] = word.charAt(index);
        return false;
    }
    /*
        Time Complexity: O(N * 3^L)
        Space Complexity: O(L)
     */
}
