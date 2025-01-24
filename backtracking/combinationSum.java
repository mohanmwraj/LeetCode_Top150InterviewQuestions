package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
    https://leetcode.com/problems/combination-sum/description/
 */
public class combinationSum {
    /*
        * Approach: Backtracking
        *
        * Use Include and Exclude Pattern
        *
     */
    public List<List<Integer>> combinationalSum(int[] nums, int target){
        List<List<Integer>> answer = new ArrayList<>();
        backtrack(0, target, nums, new ArrayList<>(), answer);
        return answer;
    }

    private void backtrack(int idx, int target, int[] nums, List<Integer> current, List<List<Integer>> answer){
        if(target == 0){
            answer.add(new ArrayList<>(current));
        } else if(target < 0){
            return;
        }

        for(int i = idx; i < nums.length; ++i){
            current.add(nums[i]);
            backtrack(i, target - nums[i], nums, current, answer);
            current.removeLast();
        }
    }

    /*
        Let N be the number of candidates, T be the target value, and M be the minimal value among the candidates.

        Time Complexity: O(N^((T/M)+1))
        Space Complexity: O(T/M)
     */
}
