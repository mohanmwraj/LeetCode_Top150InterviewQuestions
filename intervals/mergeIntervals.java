package intervals;

import java.util.Arrays;
import java.util.LinkedList;

/*
    https://leetcode.com/problems/merge-intervals/description/
 */
public class mergeIntervals {
    /*
        * Approach: Sorting
        *
        * If we sort the intervals by their start value,
        * then each set of intervals that can be merged will appear as a contiguous "run" in the sorted list.
        *
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for(int[] interval: intervals){
            if(merged.isEmpty() || merged.getLast()[1] < interval[0]){
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    /*
        Time Complexity: O(n log n)
        Space Complexity: O(log N) or O(n)
     */
}
