package math;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
    https://leetcode.com/problems/max-points-on-a-line/description/

    Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.

    Input: points = [[1,1],[2,2],[3,3]]
    Output: 3
 */
public class maxPointsonaLine {
    /*
        * Approach:
        *
        * use atan formula to find two points lies on the same line.
        *
     */
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n == 1) {
            return 1;
        }
        int result = 2;
        for (int i = 0; i < n; i++) {
            Map<Double, Integer> cnt = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    cnt.merge(
                            Math.atan2(
                                    points[j][1] - points[i][1],
                                    points[j][0] - points[i][0]
                            ),
                            1,
                            Integer::sum
                    );
                }
            }
            result = Math.max(result, Collections.max(cnt.values()) + 1);
        }
        return result;
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(n)
     */
}
