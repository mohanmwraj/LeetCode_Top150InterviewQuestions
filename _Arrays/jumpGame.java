package _Arrays;

public class jumpGame {

    /*
        * Approach: Backtracking [TLE]
        * This is the inefficient solution where we try every single jump pattern that takes us from the first position to the last.
        * We start from the first position and jump to every index that is reachable.
        * We repeat the process until last index is reached. When stuck, backtrack.

     */

    public boolean canJump(int[] nums){
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

}
