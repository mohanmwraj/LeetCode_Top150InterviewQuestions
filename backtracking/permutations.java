package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
    https://leetcode.com/problems/permutations/description/
 */
public class permutations {
    /*
        * Approach: Backtracking
        *
        * use Include and Exclude Pattern
     */

    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>>  answer = new ArrayList<>();
        backtrack(new ArrayList<>(), nums, answer);
        return answer;
    }

    private void backtrack(List<Integer> current, int[] nums, List<List<Integer>> answer){
        if(current.size() == nums.length){
            answer.add(new ArrayList<>(current));
            return;
        }

        for(int num: nums){
            if(!current.contains(num)){
                current.add(num);
                backtrack(current, nums, answer);
                current.removeLast();
            }
        }
    }
    /*
        Time Complexity: O(n.n!)
        Space Complexity: O(n)
     */
}
