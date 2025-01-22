package intervals;

/*
    https://leetcode.com/problems/insert-interval/description/
 */

import java.util.ArrayList;
import java.util.List;

public class insertInterval {
    /*
        * Approach: Linear Search
        *
        * We can do a linear search by iterating through all the intervals and checking which one of the three conditions the intervals fall under:
        *
        * 1. No Overlaps before Merging
        * 2. Overlapping and Merging
        * 3. No Overlapping after Merging
     */
    public int[][] insert_1(int[][] intervals, int[] newInterval) {
        int n = intervals.length, i = 0;
        List<int[]> res = new ArrayList<>();

        // Case 1: No overlapping before merging intervals
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

        // Case 2: Overlapping and merging intervals
        while (i < n && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);

        // Case 3: No overlapping after merging newInterval
        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        // Convert List to array
        return res.toArray(new int[res.size()][]);
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */

    /*
        * Approach: Binary Search
        *
        * Finding the Insertion Position using binary search
        * insert the new interval.
        * handling merging using merge interval concept.
        *
     */
    public int[][] insert_2(int[][] intervals, int[] newInterval) {
        if(intervals.length == 0){
            return new int[][]{newInterval};
        }

        int n = intervals.length;
        int target = newInterval[0];
        int left = 0;
        int right = n - 1;

        while(left <= right){
            int mid = (left + right) / 2;
            if(intervals[mid][0] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        List<int[]> result = new ArrayList<>();
        for(int i = 0; i < left; ++i){
            result.add(intervals[i]);
        }
        result.add(newInterval);
        for(int i = left; i < n; ++i){
            result.add(intervals[i]);
        }

        List<int[]> merged = new ArrayList<>();
        for(int[] interval: result){
            if(merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]){
                merged.add(interval);
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        return merged.toArray(new int[0][]);
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
