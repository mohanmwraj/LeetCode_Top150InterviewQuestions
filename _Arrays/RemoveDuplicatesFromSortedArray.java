package _Arrays;

/*
https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 */

public class RemoveDuplicatesFromSortedArray {
    /*
        * Approach: Two Indexes Approach
        *
        * Since the array we have is sorted, all duplicate values will be one after the other.
        * We need to update the first k elements in an array with unique values and return the value of k.
        *
        * The problem states that we need to fill the first k elements of an array with unique values.
        * For doing so, we modify the input array in-place so that we don't use extra space
        * In order to perform in-place operations, we use the Two indexes approach
        * The first index updates the value in our input array while reading the data from the second index.
        * We continue the above steps until the second index reaches the end of an array
        *
        *
        * First Index is responsible for writing unique values in our input array, while Second Index will read the input array and pass all the distinct elements to First Index.
     */
    public int removeDuplicates(int[] nums) {
        int insertIndex = 1;
        for(int i = 1; i < nums.length; ++i){
            if(nums[i] != nums[i- 1]){
                nums[insertIndex++] = nums[i];
            }
        }

        return insertIndex;
    }

    /*
        Time Complexity: O(N)
        Space Complexity: O(1)
     */
}
