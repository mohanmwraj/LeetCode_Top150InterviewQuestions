package binarySearch;
/*
    https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 */
public class findFirstandLastPositionofElements {
    /*
        * Approach: Binary Search
        *
        * Find the
            * Upper Bound
            * Lower Bound
         *
     */
    public int[] searchRange(int[] nums, int target) {
        int firstOccurence = firstBound(nums, target, true);
        if(firstOccurence == -1){
            return new int[] {-1, -1};
        }

        int lastOccurence = firstBound(nums, target, false);
        return new int[]{firstOccurence, lastOccurence};
    }

    private int firstBound(int[] nums, int target, boolean isFirst){
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                if(isFirst){
                    if(mid == left || nums[mid - 1] != target) return mid;
                    right = mid - 1;
                } else {
                    if(mid == right || nums[mid + 1] != target) return mid;
                    left = mid + 1;
                }
            } else if(nums[mid] > target){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
    /*
        Time Complexity: O(log N)
        Space Complexity: O(1)
     */
}
