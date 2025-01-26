package bitManipulation;
/*
    https://leetcode.com/problems/number-of-1-bits/description/
 */
public class numberOf1Bits {
    /*
        * Approach: Brute Force
        *
        * We check each 32 bits, if it is 1, we add it to the bit count.
        *
     */

    public int hammingWeight_1(int n){
        int mask = 1;
        int bits = 0;
        for(int i = 0; i < 32; ++i){
            if((n & mask) != 0){
                bits++;
            }
            mask <<= 1;
        }

        return bits;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
    /*
        * Approach: Bit Manipulation
        *
        * Anding two numbers n and n - 1 flips the least significant 1-bit in n to 0,
        * and keeps all other bits the same.
        *
     */
    public int hammingWeight_2(int n){
        int sum = 0;
        while(n != 0){
            sum++;
            n &= (n - 1);
        }
        return sum;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
}
