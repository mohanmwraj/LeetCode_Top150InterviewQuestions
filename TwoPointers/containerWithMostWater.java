package TwoPointers;
/*
    https://leetcode.com/problems/container-with-most-water/description/
 */
public class containerWithMostWater {
    /*
        * Approach: Brute Force
     */
    public int maxArea_1(int[] height) {
        int maxarea = 0;
        for (int left = 0; left < height.length; left++) {
            for (int right = left + 1; right < height.length; right++) {
                int width = right - left;
                maxarea = Math.max(
                        maxarea,
                        Math.min(height[left], height[right]) * width
                );
            }
        }
        return maxarea;
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */

    /*
        * Approach: Two Pointer Approach
        * The intuition behind this approach is that the area formed between the lines will always be limited by the height of the shorter line.
        * Further, the farther the lines, the more will be the area obtained.
     */
    public int maxArea_2(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while(left < right){
            int width = right - left;
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * width);

            if(height[left] < height[right]){
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
