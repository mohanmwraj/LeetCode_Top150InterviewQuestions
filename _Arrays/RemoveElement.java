package _Arrays;

/*
    https://leetcode.com/problems/remove-element/description/
 */

public class RemoveElement {
    /*
        * Approach: Two Pointers
        *
        *  remove all elements of the given value in-place
        * We can keep two pointers i and j, where i is the slow-runner while j is the fast-runner.
        *
        *
        * When nums[j] equals to the given value, skip this element by incrementing j.
        * As long as nums[j]!=val, we copy nums[j] to nums[i] and increment both indexes at the same time.
        * Repeat the process until j reaches the end of the array and the new length is i.
     */
    public int removeElement_1(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /*
        Time complexity : O(n).
        Space complexity : O(1).
     */

    /*
        * Approach: Two Pointers - when elements to remove are rare
        *
        * The previous algorithm will do unnecessary copy operation.
        *
        * When we encounter nums[i]=val, we can swap the current element out with the last element and dispose the last one.
        * This essentially reduces the array's size by 1.
        * Note that the last element that was swapped in could be the value you want to remove itself.
        * in the next iteration we will still check this element.
     */
    public int removeElement_2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while(i < n){
            if(nums[i] == val){
                nums[i] = nums[n - 1];
                n--;
            } else {
                i++;
            }
        }
        return n;
    }
    /*
        Time complexity : O(n).
        Space complexity : O(1).
     */
}
