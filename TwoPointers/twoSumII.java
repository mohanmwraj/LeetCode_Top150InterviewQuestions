package TwoPointers;

/*
    https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
 */

public class twoSumII {
    /*
        * Approach: Two Pointers
     */
    public int[] twoSum(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while(low < high){
            if(nums[low] + nums[high] == target){
                return new int[]{low + 1, high + 1};
            } else if(nums[low] + nums[high] < target){
                low++;
            } else {
                high--;
            }
        }

        return new int[]{-1, -1};
    }

    /*
        Time Complexity: O(N)
        Space Complexity: O(1)
     */
}
