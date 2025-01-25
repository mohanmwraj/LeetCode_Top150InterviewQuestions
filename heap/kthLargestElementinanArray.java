package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
    https://leetcode.com/problems/kth-largest-element-in-an-array/
 */
public class kthLargestElementinanArray {
    /*
        * Approach: Sort
        *
        * sort and return length - k element.
     */

    public int findKthLargest_1(int[] nums, int k){
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)

        In Java, Arrays.sort() for primitives is implemented using a variant of the Quick Sort algorithm, which has a space complexity of O(logn)
        In C++, the sort() function provided by STL uses a hybrid of Quick Sort, Heap Sort, and Insertion Sort, with a worst-case space complexity of O(logn)
        In Python, the sort() function is implemented using the Timsort algorithm, which has a worst-case space complexity of O(n)
     */

    /*
        * Approach: Min-Heap
        *
        * use min heap, priority queue.
        * Maintain a heap of size k.
        * After iteration, return top element.
        *
     */
    public int findKthLargest(int[] nums, int k){
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int num: nums){
            heap.add(num);
            if(heap.size() > k) heap.poll();
        }
        return heap.peek();
    }
    /*
        Time Complexity: O(n log k)
        Space Complexity: O(k)
     */
}
