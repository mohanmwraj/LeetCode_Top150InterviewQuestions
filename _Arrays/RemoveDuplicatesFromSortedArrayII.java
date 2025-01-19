package _Arrays;

/*
https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
 */

public class RemoveDuplicatesFromSortedArrayII {

    /*
        * Approach: Popping Unwanted Duplicates
        *
        * Use two index approach.
        * First pointer count the frequency of the element.
        * Second pointer write the elements in-place in the array (if frequency of element count is less than 2).
        *
     */
    public int removeDuplicates_1(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;

        int i = 1;
        int count = 1;

        for(int j = 1; j < n; ++j){
            if(nums[j] == nums[j - 1]){
                count++; // Increment count for the current element
            } else {
                count = 1; // Reset count for new element
            }

            if(count <= 2){
                nums[i++] = nums[j]; // Update the array in place
            }
        }

        return i; // Return the new array length
    }

    /*
        Time Complexity: O(N^2)
            * We have to iterate over all the elements in the array. Time taken will be O(n).
            * for every unwanted duplicate element, we will have to perform a delete operation and deletions in arrays are also O(N).
            * The worst case would be when all the elements in the array are the same. In that case, we would be performing N−2 deletions thus giving us O(N^2) complexity for deletions.
            * Overall complexity = O(N)+O(N2)≡O(N2).
        Space Complexity: O(1)
     */

    /*
        * Approach: Overwrite Unwanted Duplicates
        *
        * we don't need to actually remove elements from the array.
        * Instead, we can do something better and simply overwrite the duplicate elements that are unwanted.
     */

    public int removeDuplicates_2(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;

        int i = 1;
        int j = 1;
        int count = 1;

        while(i < n){
            if(nums[i] == nums[i - 1]){
                count++;
                if(count > 2){
                    i++;
                    continue;
                }
            } else {
                count = 1;
            }

            nums[j++] = nums[i++];
        }
        return j;
    }

    /*
        Time Complexity: O(N)
        Space Complexity: O(1)
     */
}
