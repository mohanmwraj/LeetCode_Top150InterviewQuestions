package KadanesAlgorithm;
/*
    https://leetcode.com/problems/maximum-sum-circular-subarray/description/
 */
public class maximumSumCircularSubarray {
    /*
        * Approach: Enumerate prefix and suffix sums
        *
        * As a circular array,
        * the maximum subarray sum can be either the maximum "normal sum"
        * which is the maximum sum of the ordinary array
        *
        * or a "special sum" which would involve elements that wrap around the array.
        *
        * The "special sum" would be the combination of a prefix sum and a suffix sum.
        *
     */
    public int maxSubarraySumCircular_1(int[] nums) {
        final int n = nums.length;
        final int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        int suffixSum = nums[n - 1];

        for (int i = n - 2; i >= 0; --i) {
            suffixSum += nums[i];
            rightMax[i] = Math.max(rightMax[i + 1], suffixSum);
        }

        int maxSum = nums[0];
        int specialSum = nums[0];
        int curMax = 0;
        for (int i = 0, prefixSum = 0; i < n; ++i) {
            // This is Kadane's algorithm.
            curMax = Math.max(curMax, 0) + nums[i];
            maxSum = Math.max(maxSum, curMax);

            prefixSum += nums[i];
            if (i + 1 < n) {
                //i + 1 -> in a circular array, ex: prefix sum 0 to 2 suffix sum will be 3 to n.
                // rightMax[i + 1] => will give suffix sum
                specialSum = Math.max(specialSum, prefixSum + rightMax[i + 1]);
            }
        }
        // maxSum = kadane's algorithm.
        // specialSum = prefix and suffix sum.
        return Math.max(maxSum, specialSum);
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Calculate the "Minimum Subarray"
        *
        * maxSum - normal subarray sum which can be found using the kadane's algorithm.
        * Special sum - a sum of prefix and suffix subarray.
        * => total Sum - subarray with minimum will give special sum.
        *
     */
    public int maxSubarraySumCircular(int[] nums){
        int currMax = 0;
        int currMin = 0;
        int maxSum = nums[0];
        int minSum = nums[0];
        int totalSum = 0;

        for(int i = 0; i < nums.length; ++i){
            currMax = Math.max(currMax,0) + nums[i];
            maxSum = Math.max(currMax, maxSum);

            currMin = Math.min(currMin, 0) + nums[i];
            minSum = Math.min(minSum, currMin);

            totalSum += nums[i];
        }

        if(totalSum == minSum) return maxSum; //[-3,-2,-3] if all are -ve elements, then total sum will be min sum, so we return 0.
        return Math.max(maxSum, totalSum - minSum);
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
