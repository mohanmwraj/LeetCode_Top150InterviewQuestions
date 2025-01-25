package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
    https://leetcode.com/problems/find-median-from-data-stream/description/
 */
public class findMedianFromDataStreams {
    /*
        * Approach: Two Heaps
        *
        * A max-heap to store the smaller half of the input numbers
        * A min-heap to store the larger half of the input numbers
        *
     */
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public findMedianFromDataStreams() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        maxHeap.add(num);
        minHeap.add(maxHeap.remove());

        if(maxHeap.size() < minHeap.size()){
            maxHeap.add(minHeap.remove());
        }
    }

    public double findMedian() {
        if(maxHeap.size() > minHeap.size()){
            return maxHeap.peek();
        }

        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
    /*
        Time Complexity: O(log N)
        Space Complexity: O(n)
     */
}
