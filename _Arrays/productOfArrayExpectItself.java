package _Arrays;

/*
    https://leetcode.com/problems/product-of-array-except-self/description/
 */

public class productOfArrayExpectItself {
    /*
        * Approach: Left and Right product lists
        *
        * Instead of dividing the product of all the numbers in the array by the number at
        * at a given index to get the corresponding product, we can make use of the product of all the numbers to the left
        * and all the numbers to the right of the index. Multiplying these two individual products would give us
        * the desired results as well.
        *
        *
     */
    public int[] productExceptItself_1(int[] nums){
        int length = nums.length;

        int[] L = new int[length];
        L[0] = 1;
        for(int i = 1; i < length; ++i){
            L[i] = nums[i - 1] * L[i - 1];
        }

        int[] R = new int[length];
        R[length - 1] = 1;
        for(int i = length - 2; i >= 0; --i){
            R[i] = nums[i + 1] * R[i + 1];
        }

        int[] answer = new int[length];
        for(int i = 0; i < length; ++i){
            answer[i] = L[i] * R[i];
        }

        return answer;
    }

    /*
        Time Complexity: O(N)
        Space Complexity: O(N)
     */

    /*
        * Approach: O(1) Space Approach
        *
        * we will be using the output array as one of L or R and we will be constructing the other one on the fly.
     */
    public int[] productExceptItself_2(int[] nums){
        int len = nums.length;
        int[] answer = new int[len];

        answer[0] = 1;
        for(int i = 1; i < len; ++i){
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        int R = 1;
        for(int i = len - 1; i >= 0; --i){
            answer[i] = answer[i] * R;
            R *= nums[i];
        }
        return answer;
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(1)
     */
}
