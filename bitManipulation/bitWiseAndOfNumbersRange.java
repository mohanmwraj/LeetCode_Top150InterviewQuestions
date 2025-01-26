package bitManipulation;
/*
    https://leetcode.com/problems/bitwise-and-of-numbers-range/description/

    Given two integers left and right that represent the range [left, right],
    return the bitwise AND of all numbers in this range, inclusive.
 */
public class bitWiseAndOfNumbersRange {
    /*
        * Approach: Bit Shift
        *
        * The idea is that we shift both numbers to the right,
        * until the numbers become equal, i.e. the numbers are reduced into their common prefix.
        * Then we append zeros to the common prefix in order to obtain the desired result,
        * by shifting the common prefix to the left.
        *
     */
    public int rangeBitwiseAnd_1(int m, int n) {
        int shift = 0;
        // find the common 1-bits
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
    /*
        * Approach: Brain Kernighan's Algorithm
        *
     */
    public int rangeBitwiseAnd_2(int m, int n) {
        while (m < n) {
            // turn off rightmost 1-bit
            n = n & (n - 1);
        }
        return n;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
}
