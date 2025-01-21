package _Arrays;

/*
    https://leetcode.com/problems/zigzag-conversion/description/


 */


import java.util.Arrays;

public class zigZagConversion {
    /*
        * Approach: Simulate Zig-Zag Movement
        *
        * one column, and a diagonal as one section.
        * Each section will have at most  numRows - 1  columns in it (the last section might have fewer columns)
        * In each section, we will have 2 * numRow - 2 characters, (numRow characters in one column and numRow - 2 in the diagonal).
        * Thus, for a string of n characters, we will require at most ceil(n / (2 * numRows - 2)) sections, and as each section will have numRows - 1 columns.
        * We can say we need ceil(n / (2 * numRows - 2)) * (numRows - 1) columns.
        *
        * So, our matrix will be of dimension, numRows×numCols, where numCols = ceil(n / (2 * numRows - 2)) * (numRows - 1).
        *
        * Our second and more tricky task is to traverse the matrix in a zigzag order.
        * We start from the top, traverse down through an entire column, then traverse diagonally up, i.e. we traverse one section and
        * then repeat the same process again for the next section until the string is finished.
        * While moving from top to bottom in a column, currCol will remain the same but currRow will go from 0 to numRows.
        * While moving diagonally up, we move one cell up and one cell right,
        * thus increment currCol by 1 and decrease currRow by 1 till it reaches the top (currRow=0)
     */

    public String convert_1(String s, int numRows){
        if(numRows == 1) return s;

        int n = s.length();
        int sections = (int) Math.ceil(n / (2 * numRows - 2.0));
        int numCols = sections * (numRows - 1);

        char[][] matrix = new char[numRows][numCols];
        for(char[] row: matrix) Arrays.fill(row, ' ');

        int currRow = 0;
        int currCol = 0;
        int currStringIndex = 0;

        while(currStringIndex < n){
            while(currRow < numRows && currStringIndex < n){
                matrix[currRow][currCol] = s.charAt(currStringIndex);
                currRow++;
                currStringIndex++;
            }

            currRow -= 2;
            currCol++;

            while(currRow > 0 && currCol < numCols && currStringIndex < n){
                matrix[currRow][currCol] = s.charAt(currStringIndex);
                currRow--;
                currCol++;
                currStringIndex++;
            }
        }

        StringBuilder answer = new StringBuilder();
        for(char[] row: matrix){
            for(char ch: row){
                if(ch != ' ') answer.append(ch);
            }
        }

        return answer.toString();
    }

    /*
        Time complexity: O(numRows * n)
        Space complexity: O(numRows * n)
     */

    /*
        * Approach: String Traversal
        *
        * there is a pattern between the distances between characters in each row.
        * If we analyze the jump pattern and traverse the input string using that pattern we can avoid the use of additional space.
        * If we have to jump to the next section then it's simple: we only jump charsInSection characters.
            So, currIndex += charsInSection.
        * If we have to jump to the next character in the same section, then we will have to calculate
        * how many characters are between these two positions and increment currIndex by that value.
        * If the total characters in a section are charsInSection, and we are in the ith row,
        * then the number of characters above the current row will be 2∗i,
        * and the number of characters left will be charsInBetween = charsInSection - 2∗i.
        * So, secondIndex = currIndex + charsInBetween.
        *
     */
    public String convert_2(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        StringBuilder answer = new StringBuilder();
        int n = s.length();
        int charsInSection = 2 * (numRows - 1);

        for (int currRow = 0; currRow < numRows; ++currRow) {
            int index = currRow;

            while (index < n) {
                answer.append(s.charAt(index));

                // If currRow is not the first or last row
                // then we have to add one more character of current section.
                if (currRow != 0 && currRow != numRows - 1) {
                    int charsInBetween = charsInSection - 2 * currRow;
                    int secondIndex = index + charsInBetween;

                    if (secondIndex < n) {
                        answer.append(s.charAt(secondIndex));
                    }
                }
                // Jump to same row's first character of next section.
                index += charsInSection;
            }
        }

        return answer.toString();
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
