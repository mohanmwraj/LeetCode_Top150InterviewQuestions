package _Arrays;

import java.util.Arrays;

enum Index{
    GOOD,
    BAD,
    UNKNOWN,
}

public class jumpGame {

    /*
        * Approach: Backtracking [TLE]
        * This is the inefficient solution where we try every single jump pattern that takes us from the first position to the last.
        * We start from the first position and jump to every index that is reachable.
        * We repeat the process until last index is reached. When stuck, backtrack.

     */

    public boolean canJump_1(int[] nums){
        return canJumpFromPosition(0, nums);
    }

    private boolean canJumpFromPosition(int position, int[] nums){
        if(position == nums.length - 1) {
            return true;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for(int nextPostion = position + 1; nextPostion <= furthestJump; ++nextPostion){
            if(canJumpFromPosition(nextPostion, nums)){
                return true;
            }
        }
        return false;
    }

    /*
        Time complexity : O(2n). There are 2n (upper bound) ways of jumping from the first position to the last, where n is the length of array nums.
        Space complexity : O(n)
     */
    /*
        * Approach: Dynamic Programming Top - Down [Memoization]
        * For every index there are three possible options - GOOD, BAD, UNKNOWN.
     */

    Index[] memo;
    public boolean canJump_2(int[] nums){
        memo = new Index[nums.length];
        Arrays.fill(memo, Index.UNKNOWN);
        memo[nums.length - 1] = Index.GOOD;
        return canJumpFromPosition_2(0, nums);
    }

    private boolean canJumpFromPosition_2(int position, int[] nums){
        // if(position == nums.length - 1) {
        //     return true;
        // }

        if(memo[position] != Index.UNKNOWN){
            return memo[position] == Index.GOOD ? true: false;
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for(int nextPostion = position + 1; nextPostion <= furthestJump; ++nextPostion){
            if(canJumpFromPosition(nextPostion, nums)){
                memo[position] = Index.GOOD;
                return true;
            }
        }

        memo[position] = Index.BAD;
        return false;
    }

    /*
        Time complexity : O(n^2).
            For every element in the array, say i, we are looking at the next nums[i] elements to its right aiming to find a GOOD index. nums[i] can be at most n, where n is the length of array nums.

        Space Complexity: O(n)
     */

    /*
        * Approach: Dynamic Programming Bottom Up [Tabulation]
     */

    public boolean canJump_3(int[] nums) {
        Index[] memo = new Index[nums.length];
        Arrays.fill(memo, Index.UNKNOWN);

        memo[nums.length - 1] = Index.GOOD;

        for(int i = nums.length - 2; i >= 0; --i){
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for(int nextPostion = i + 1; nextPostion <= furthestJump; ++nextPostion){
                if(memo[nextPostion] == Index.GOOD){
                    memo[i] = Index.GOOD;
                    //return true;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }
    /*
        Time complexity : O(n^2).
            For every element in the array, say i, we are looking at the next nums[i] elements to its right aiming to find a GOOD index. nums[i] can be at most n, where n is the length of array nums.

        Space Complexity: O(n)
     */

    /*
        * Approach: Greedy
        *
        * Iterate from last position, check whether we can reach 0th index.
        * if index and current value sum, will give a jump greater than the last position update the last position to current index.
     */

    public boolean canJump_4(int[] nums) {
        int lastPos = nums.length - 1;
        for(int i = nums.length - 1; i >= 0; --i){
            if(i + nums[i] >= lastPos){
                lastPos = i;
            }
        }

        return lastPos == 0;
    }
    /*
        Time complexity : O(n)
        Space Complexity: O(1)
     */
}
