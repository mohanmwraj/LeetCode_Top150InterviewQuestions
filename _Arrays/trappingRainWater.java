package _Arrays;

/*
    https://leetcode.com/problems/trapping-rain-water/description/
 */

public class trappingRainWater {

    /*
        * Approach: Brute Force
        *
        * For each element in the array, we find the maximum level of water it can trap after the rain,
        * which is equal to the minimum of maximum height of bars on both the sides minus its own height.
     */

    public int trap_1(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int left_max = 0, right_max = 0;

            for (int j = i; j >= 0; j--) {
                left_max = Math.max(left_max, height[j]);
            }

            for (int j = i; j < size; j++) {
                right_max = Math.max(right_max, height[j]);
            }
            ans += Math.min(left_max, right_max) - height[i];
        }
        return ans;
    }

    /*
        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */

    /*
        * Approach: Dynamic Array
        *
        * Use left to right array, to calculate left max for the current element.
        * Use right to left array, to calculate right max for the current element.
     */
    public int trap_2(int[] height) {
        if (height.length == 0) return 0;
        int ans = 0;
        int size = height.length;

        int[] left_max = new int[size];
        int[] right_max = new int[size];

        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {

            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }

        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {

            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }

        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }

        return ans;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Using 2 Pointers
        *
        * Instead of computing the left and right parts separately,
        * we may think of some way to do it in one iteration.
        *
        * we can say that if there is a larger bar at one end (say right), we are assured
        * that the water trapped would be dependent on height of bar in current direction (from left to right).
        *
        * We must maintain left_max and right_max during the iteration,
        * but now we can do it in one iteration using 2 pointers, switching between the two.
     */

    public int trap_3(int[] heights){
        int left = 0;
        int right = heights.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int answer = 0;

        while(left < right){
            if(heights[left] < heights[right]){
                leftMax = Math.max(leftMax, heights[left]);
                answer += leftMax - heights[left++];

            } else {
                rightMax = Math.max(rightMax, heights[right]);
                answer += rightMax - heights[right++];
            }
        }

        return answer;
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
