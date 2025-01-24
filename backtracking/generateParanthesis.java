package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
    https://leetcode.com/problems/generate-parentheses/description/
 */
public class generateParanthesis {
    /*
        * Approach: Backtracking
        *
        * Use Include and Exclude Pattern
        *
     */

    public List<String> generateParenthesis(int n){
        List<String> result = new ArrayList<>();
        backtrack(0, 0, n, "", result);
        return result;
    }

    private void backtrack(int open, int closed, int n, String current, List<String> result){
        if(closed == n){
            result.add(current);
            return;
        }

        if(open < n) backtrack(open + 1, closed, n, current +'(', result);
        if(closed < open) backtrack(open, closed + 1, n, current + ')', result);
    }

    /*
        Time Complexity: O(4^n / square root of n)
        Space Complexity: O(n)
     */
}
