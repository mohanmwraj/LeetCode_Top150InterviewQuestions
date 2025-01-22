package SlidingWindow;
/*
    https://leetcode.com/problems/minimum-size-subarray-sum/
 */
public class minimumSizeSubarraySum {
    /*
        * Approach: Sliding Window
        *
        * initialize two pointers left and right, to 0.
        * iterate right, and add nums of right to the sum.
        * when sum exceeds the target, calculate minimum subarray right - left + 1.
        * remove the nums of left in the sum.
        *
     */

    public int minSubarrayLen(int target, int[] nums){
        int left = 0;
        int right = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;

        for(right = 0; right < nums.length; ++right){
            sum += nums[right];

            while(sum >= target){
                result = Math.min(right - left + 1, result);
                sum -= nums[left++];
            }
        }

        return result == Integer.MAX_VALUE ? 0: result;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
