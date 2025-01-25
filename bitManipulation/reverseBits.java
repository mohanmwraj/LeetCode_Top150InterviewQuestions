package bitManipulation;
/*
    https://leetcode.com/problems/reverse-bits/description/
 */
public class reverseBits {
    /*
        * Approach: Bit By Bit
        *
        * Iterate from right to left n = n >> 1
        * for each bit, we reverse it to the correct position (n & 1) << power,
        * then we accumulate this reversed bit to the final result.
        *
     */
    public int reverseBits(int n){
        int ret = 0;
        int power = 31;

        while(n != 0){
            ret += (n & 1) << power;
            n = n >> 1;
            power--;
        }

        return ret;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
}
