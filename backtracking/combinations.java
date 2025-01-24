package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
    https://leetcode.com/problems/combinations/description/

    Input: n = 4, k = 2
    Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 */
public class combinations {
    /*
        * Approach: Backtracking
        *
        * Use Include and Exclude pattern
        *
     */
    public List<List<Integer>> combine(int n, int k){
        List<List<Integer>> answer = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), answer);
        return answer;
    }

    private void backtrack(int firstNum, int n, int k, List<Integer> current, List<List<Integer>> answer){
        if(firstNum > n) return;
        if(current.size() == k){
            answer.add(new ArrayList<>(current));
            return;
        }

        //Include
        current.add(firstNum);
        backtrack(firstNum + 1, n, k, current, answer);

        //Exclude
        current.removeLast();
        backtrack(firstNum + 1, n, k, current, answer);
    }

    /*
        Time Complexity: O(n! / (k - 1)!.(n - k)!)
        Space Complexity: O(k)
     */
}
