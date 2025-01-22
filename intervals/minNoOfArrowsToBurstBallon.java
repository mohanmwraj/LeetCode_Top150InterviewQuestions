package intervals;

import java.util.Arrays;

/*
    https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/
 */
public class minNoOfArrowsToBurstBallon {
    /*
        * Approach: Greedy
        *
        * Sort the balloons by end coordinate x_end.
        * Initiate the end coordinate of a balloon which ends first: first_end = points[0][1].
        * Initiate the number of arrows: arrows = 1.
        * Iterate over all balloons:
        *    -  If the balloon starts after first_end:
        *       *   Increase the number of arrows by one.
        *       *   Set first_end to be equal to the end of the current balloon.
        * Return arrows.
        *
     */
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        Arrays.sort(points, (a, b) ->{
            if(a[1] == b[1]) return 0;
            if(a[1] < b[1]) return -1;
            return 1;
        });

        int arrows = 1;
        int xStart, xEnd, firstEnd = points[0][1];
        for(int[] p: points){
            xStart = p[0];
            xEnd = p[1];

            if(firstEnd < xStart){
                arrows++;
                firstEnd = xEnd;
            }
        }
        return arrows;
    }

    /*
        Time Complexity: O(n * log n)
        Space Complexity: O(n) or O(log n)
     */
}
