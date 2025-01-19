package _Arrays;

/*
https://leetcode.com/problems/rotate-array/description/
 */
public class RotateArray {
    /*
        * Approach: Brute Force
        *
        * The simplest approach is to rotate all the elements of the array in k steps by rotating the elements by 1 unit in each step.
     */

    public void rotate_1(int[] nums, int k){
        k %= nums.length;

        for(int i = 0; i < k; ++i){
            int prev = nums[nums.length - 1];
            for(int j = 0; j < nums.length; ++j){
                int curr = nums[j];
                nums[j] = prev;
                prev = curr;
            }
        }
    }

    /*
        Time complexity: O(n×k).
        Space complexity: O(1)
     */

    /*
        * Approach: Use Extra Array
        *
        * We use an extra array in which we place every element of the array at its correct position i.e. the number at index i in the original array is placed at the
        * index (i+k)% length of array. Then, we copy the new array to the original one.
        *
     */

    public void rotate_2(int[] nums, int k){
        int n = nums.length;
        int[] arr = new int[n];

        for(int i = 0; i < n; ++i){
            arr[(i + k) % n] = nums[i];
        }

        for(int i = 0; i < n; ++i){
            nums[i] = arr[i];
        }
    }
    /*
        Time complexity: O(n).
        Space complexity: O(n).
     */

    /*
        * Approach: Use Cyclic Replacements
        *
        * We can directly place every number of the array at its required correct position.
        * But if we do that, we will destroy the original element.
        * Thus, we need to store the number being replaced in a temp variable.
        * Then, we can place the replaced number(temp) at its correct position and so on, n times, where n is the length of array.
        * We have chosen n to be the number of replacements since we have to shift all the elements of the array(which is n).
        *
     */
    public void rotate_3(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        for(int start = 0; count < n; ++start){
            int current = start;
            int prev = nums[start];

            do{
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            }while(current != start);
        }
    }
    /*
        Time complexity: O(n).
        Space complexity: O(1).
     */

    /*
        * Approach: Using Reverse
        *
        * This approach is based on the fact that when we rotate the array k times,
        * k elements from the back end of the array come to the front and the rest of the elements from the front shift backwards.
        *
        * In this approach, we firstly reverse all the elements of the array. Then, reversing the first k elements followed by reversing the rest n−k elements gives us the required result.
        *
     */

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(0, nums.length - 1, nums);
        reverse(0, k - 1, nums);
        reverse(k, nums.length - 1, nums);
    }

    private void reverse(int start, int end, int[] nums){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    /*
        Time complexity: O(n).
        Space complexity: O(1).
     */
}
