package binarySearch;

public class findPeakElement {
    /*
        https://leetcode.com/problems/find-peak-element/description/
     */
    /*
        * Approach: Linear Scan
        *
        * In this approach, we make use of the fact that two consecutive numbers nums[j] and nums[j+1] are never equal.
        * Whenever, we find a number nums[i], we only need to check if it is larger than the next number nums[i+1]
        * for determining if nums[i] is the peak element.
        *
        * Case 1. All the numbers appear in a descending order.
        * Case 2. All the elements appear in ascending order.
        * Case 3. The peak appears somewhere in the middle.
        *
     */
    public int findPeakElement_1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) return i;
        }
        return nums.length - 1;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */

    /*
        * Approach: Recursive Binary Search
        *
     */
    public int findPeakElement_2(int[] nums) {
        return search_2(nums, 0, nums.length - 1);
    }

    public int search_2(int[] nums, int l, int r) {
        if (l == r) return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1]) return search_2(nums, l, mid);
        return search_2(nums, mid + 1, r);
    }
    /*
        Time Complexity: O(log n)
        Space Complexity: O(log n)
     */

    /*
        * Approach: Iterative Binary Search
        *
     */
    public int findPeakElement_3(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1]) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    /*
        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
}
