package _Arrays;

/*
https://leetcode.com/problems/jump-game-ii/description/
 */
public class JumpGameII {
    /*
        * Approach: Greedy
        *
        * Return the minimum number of jumps to reach nums[n - 1].
        *
        * current far, current end,
        * during iteration when the current index reaches the current far, that is one jump,
        * and also update the current end to current far.
     */

    private int jump(int[] nums){
        int answer = 0;
        int currFar = 0;
        int currEnd = 0;

        for(int i = 0; i < nums.length - 1; ++i){
            currFar = Math.max(currFar, i + nums[i]);
            if(i == currEnd){
                answer++;
                currEnd = currFar;
            }
        }

        return answer;
    }
}
