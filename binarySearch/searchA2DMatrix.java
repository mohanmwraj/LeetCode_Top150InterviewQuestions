package binarySearch;
/*
    https://leetcode.com/problems/search-a-2d-matrix/description/
 */
public class searchA2DMatrix {
    /*
        * Approach: Binary Search
        *
        * One could notice that the input matrix m x n could be considered as a sorted array of length m x n.
        *
        * row = idx / n
        * col = idx % n
        *
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        // binary search
        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement;
        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            if (target == pivotElement) return true;
            else {
                if (target < pivotElement) right = pivotIdx - 1;
                else left = pivotIdx + 1;
            }
        }
        return false;
    }
    /*
        Time Complexity: O(log(mn))
        Space Complexity: O(1)
     */
}
