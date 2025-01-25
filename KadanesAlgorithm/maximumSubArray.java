package KadanesAlgorithm;
/*
    https://leetcode.com/problems/maximum-subarray/description
 */
public class maximumSubArray {
    /*
        * Approach: Optimized Brute Force
        *
        * Generate all possible subarrays
        *
     */
    public int maxSubArray_1(int[] nums){
        int maxSubarray = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; ++i){
            int currSubArray = 0;
            for(int j = 1; j < nums.length; ++j){
                currSubArray += nums[j];
                maxSubarray = Math.max(currSubArray, maxSubarray);
            }
        }

        return maxSubarray;
    }

    /*
        Time Complexity: O(N^2)
        Space Complexity: O(1)
     */
    /*
        * Approach: Dynamic Programming, Kadane's Algorithm
        *
        * Track the previous subarray sum,
        * add the current num, if it is negative, throw it away.
        *
     */
    public int maxSubArray_2(int[] nums){
        int maxSubarray = nums[0];
        int currSubarray = nums[0];

        for(int i = 1; i < nums.length; ++i){

            currSubarray = Math.max(nums[i], currSubarray + nums[i]);
            maxSubarray = Math.max(currSubarray, maxSubarray);

        }

        return maxSubarray;
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(1)
     */

}
