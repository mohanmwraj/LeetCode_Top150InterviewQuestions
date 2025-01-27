package OneDimensionalDP;
/*
    https://leetcode.com/problems/climbing-stairs/description/
 */
import java.util.Arrays;
/*
 * How to write ruccurence relation:
 * Once the problem has been identified, the following three steps comes handy in solving the problem:
 *
 * - Try to represent the problem in terms of indexes.
 * - Try all possible choices/ways at every index according to the problem statement.
 * - If the question states "count all the ways," then the sum of all choices/ways should be returned.
 * - If the question asks to find "the maximum/minimum", then the choice/way with the maximum/minimum output should be returned.
 *
 */
public class climbingStairs {



    /*
     * Approach: Recursion
     *
     * Similar to Fibonacci Numbers
     *
     */
    public static int climbStairs_1(int n){
        //Base case
        if(n == 0) return 1;
        if(n == 1) return 1;

        //Taking 1 step at a time
        int oneStep = climbStairs_1(n-1);

        //Taking 2 steps at a time
        int twoSteps = climbStairs_1(n-2);

        //Return total ways
        return oneStep+twoSteps;
    }
    /*
        Time Complexity: O(2^n)
        Space Complexity: O(n)
     */
    /*
     * Approach: Memoization [Top-Down]
     *
     */
    private int func(int n, int dp[]){
        //Base condition
        if (n <= 1) {
            return 1;
        }

        //Check if the subproblem is already solved
        if (dp[n] != -1) {
            return dp[n];
        }

        //Return the calculated value
        return dp[n] = func(n-1, dp) + func(n-2, dp);
    }
    // Function to count total ways to reach nth stair
    public int climbStairs_2(int n) {
        int[] dp = new int[n+1];

        // Initialize dp array with -1
        Arrays.fill(dp, -1);

        // Return the calculated value
        return dp[n] = func(n, dp);
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
     * Approach: Tabulation [Bottom Up]
     *
     * Tabulation involves solving a problem by building a solution from the bottom up.
     * This means start with the smallest subproblems and iteratively compute solutions for larger subproblems until the desired solution has been found.
     *
     * Declare an Array dp[] of Size n+1.
     * Setting Base Cases in the Array.
     * Iterative Computation Using a Loop.
     * Returning the last element.
     *
     */
    public int climbStairs_3(int n) {
        // Declare dp array of size n+1
        int[] dp = new int[n + 1];

        // Insert the values of base conditions
        dp[0] = 1;
        dp[1] = 1;

        // Iterate and update every index of dp array
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // Return the answer
        return dp[n];
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
     * Approach: Space Optimization
     *
     *
     */
    public int climbStairs_4(int n) {
        /*Initialize two variables to
        store previous results*/
        int prev2 = 1;
        int prev = 1;

        //Iterate and update the variables
        for (int i = 2; i <= n; i++) {
            int cur_i = prev2 + prev;
            prev2 = prev;
            prev = cur_i;
        }
        //Return the answer as prev
        return prev;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
