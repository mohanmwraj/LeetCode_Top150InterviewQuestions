package math;
/*
    https://leetcode.com/problems/plus-one/description/

    Input: digits = [1,2,3]
    Output: [1,2,4]
 */
public class plusOne {
    /*
        * Approach: Schoolbook Addition with Carry
        *
        *
     */

    public int[] plusOne(int[] digits){
        int n = digits.length;
        for(int idx = n - 1; idx >= 0; idx--){
            if(digits[idx] == 9){
                digits[idx] = 0;
            } else {
                digits[idx]++;
                return digits;
            }
        }

        //We're here because all the digits ar nines.
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
