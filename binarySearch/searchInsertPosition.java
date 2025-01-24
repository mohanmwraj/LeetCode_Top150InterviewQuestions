package binarySearch;
/*
    https://leetcode.com/problems/search-insert-position/description/
 */
public class searchInsertPosition {
    /*
        * Approach: Binary Search
        *
     */
    public int searchInsert(int[] nums, int target) {

        int pivot = 0;
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            pivot = left + (right - left) / 2;
            if(nums[pivot] == target) return pivot;
            if(nums[pivot] < target){
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }
        return left;
    }
    /*
        Time Complexity: O(log N)
        Space Complexity: O(1)
     */
}
