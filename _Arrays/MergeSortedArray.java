package _Arrays;

/*
    https://leetcode.com/problems/merge-sorted-array/description/
 */

/*
 *   Interview Tips
 *
 * Whenever you're trying to solve an array problem in place,
 * always consider the possibility of iterating backwards instead of forwards through the array.
 * It can completely change the problem, and make it a lot easier.
 */


import java.util.Arrays;

public class MergeSortedArray {

    /*
    Approach: Merge and Sort
    * A naive approach will be merged the nums2 array int0 nums1 and sort.
     */
    public void approach1(int[] nums1, int m, int[] nums2, int n) {
        if (n >= 0) System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }
    /*
        Time complexity: O((n+m)log(n+m))
        Space complexity: O(n)
    */
    /*
    Approach: Three Pointers start from the beginning
    * Make a copy of nums1 into another array nums1copy.
    * Use nums1copy and nums2, sort the elements and copy into nums1.
     */
    public void approach2(int[] nums1, int m, int[] nums2, int n) {

        int[] nums1Copy = new int[m];
        System.arraycopy(nums1, 0, nums1Copy, 0, m);

        int p1 = 0;
        int p2 = 0;

        for (int p = 0; p < m + n; p++) {
            if (p2 >= n || (p1 < m && nums1Copy[p1] < nums2[p2])) {
                nums1[p] = nums1Copy[p1++];
            } else {
                nums1[p] = nums2[p2++];
            }
        }
    }

    /*
          Time complexity: O(n+m).
          Space complexity: O(m).
    */

    /*
     * Approach: Three Pointers from End.
     *
     * Overwrite nums1 from end.
     * Take larger element from nums1 and nums2 overwrite from end.
     *
     * Always check for the condition, we are accessing elements in the limit.
     */

    public void approach3(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;

        for(int i = m + n - 1; i >= 0; --i){
            if(p2 < 0) break;

            if(p1 > 0 && nums1[p1] > nums2[p2]){
                nums1[i] = nums1[p1--];
            } else {
                nums1[i] = nums2[p2--];
            }
        }
    }
    /*
        Time complexity: O(n+m).
        Space complexity: O(1).
     */
}
