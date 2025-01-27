package OneDimensionalDP;

import java.util.Arrays;

/*
   https://leetcode.com/problems/house-robber/description/
 */
public class HouseRobber {
    /*
        * Approach: Recursion
        *
        * Include and Exclude Pattern.
        * Current index value either contribute or not contribute to the sum.
        *
     */
    private int func_1(int ind, int[] arr) {
        // Base cases
        if (ind == 0) {
            return arr[ind];
        }
        if (ind < 0) {
            return 0;
        }

        // Choosing the current element
        int pick = arr[ind] + func_1(ind - 2, arr);

        // Skipping the current element
        int nonPick = 0 + func_1(ind - 1, arr);

        // Return the maximum
        return Math.max(pick, nonPick);
    }

    // Function to find the maximum money robber can rob
    public int houseRobber_1(int[] money) {
        int n = money.length;
        int[] arr1, arr2;

        // If array has only one element, return that
        if (n == 1)
            return money[0];

        // Create arr1 (excluding last element)
        arr1 = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            arr1[i] = money[i];
        }

        // Create arr2 (excluding first element)
        arr2 = new int[n - 1];
        for (int i = 1; i < n; i++) {
            arr2[i - 1] = money[i];
        }

        int ans1 = func_1(arr1.length - 1, arr1);
        int ans2 = func_1(arr2.length - 1, arr2);

        // Return the maximum of ans1 and ans2
        return Math.max(ans1, ans2);
    }
    /*
        Time Complexity: O(2^N)
        Space Complexity: O(N)
     */

    /*
        * Approach: Memoizaiton
        *
        *
     */
    private int func_2(int ind, int[] arr, int[] dp) {
        // Base cases
        if (ind == 0)
            return arr[ind];
        if (ind < 0)
            return 0;

        // If dp[ind] already has a value, return it
        if (dp[ind] != -1) {
            return dp[ind];
        }

        // Choosing the current element
        int pick = arr[ind] + func_2(ind - 2, arr, dp);

        // Skipping the current element
        int nonPick = func_2(ind - 1, arr, dp);

        /* Store the result in dp
        array and return the maximum*/
        return dp[ind] = Math.max(pick, nonPick);
    }

    // Function to find the maximum money robber can rob
    public int houseRobber_2(int[] money) {
        int n = money.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return money[0];

        int[] arr1 = new int[n - 1];
        int[] arr2 = new int[n - 1];

        // Populate arr1 and arr2
        for (int i = 0; i < n; i++) {
            if (i != n - 1)
                arr1[i] = money[i];
            if (i != 0)
                arr2[i - 1] = money[i];
        }

        // Initialize the dp arrays with -1
        int[] dp1 = new int[n];
        Arrays.fill(dp1, -1);
        int ans1 = func_2(arr1.length - 1, arr1, dp1);

        int[] dp2 = new int[n];
        Arrays.fill(dp2, -1);
        int ans2 = func_2(arr2.length - 1, arr2, dp2);

        // Return the maximum of ans1 and ans2
        return Math.max(ans1, ans2);
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(N)
     */
    /*
        * Approach: Tabulation
     */
    private int func_3(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        // Base case
        dp[0] = nums[0];

        // Iterate through the elements of the array
        for (int i = 1; i < n; i++) {

            /* Calculate maximum value by either picking
            the current element or not picking it */
            int pick = nums[i];
            if (i > 1)
                pick += dp[i - 2];
            int nonPick = dp[i - 1];

            // Store the maximum value in dp array
            dp[i] = Math.max(pick, nonPick);
        }

        /* The last element of the dp array
        will contain the maximum sum */
        return dp[n - 1];
    }

    // Function to find the maximum money robber can rob
    public int houseRobber_3(int[] money) {
        int n = money.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return money[0];

        int[] arr1 = new int[n - 1];
        int[] arr2 = new int[n - 1];

        // Populate arr1 and arr2
        for (int i = 0; i < n; i++) {
            if (i != n - 1)
                arr1[i] = money[i];
            if (i != 0)
                arr2[i - 1] = money[i];
        }
        int ans1 = func_3(arr1);

        int ans2 = func_3(arr2);

        // Return the maximum of ans1 and ans2
        return Math.max(ans1, ans2);
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(N)
     */
    /*
        * Approach: Space Optimization
        *
     */
    private int func_4(int[] nums) {
        int n = nums.length;
        int prev = nums[0];
        int prev2 = 0;

        for (int i = 1; i < n; i++) {
            // Maximum sum if we pick current element
            int pick = nums[i];

            if (i > 1) {
                // Add the maximum sum two elements ago
                pick += prev2;
            }
            // Maximum sum if we don't pick current element
            int nonPick = 0 + prev;

            // Maximum at the current element
            int cur_i = Math.max(pick, nonPick);

            prev2 = prev;
            prev = cur_i;
        }
        // Return the maximum sum
        return prev;
    }

    // Function to find the maximum money robber can rob
    public int houseRobber_4(int[] money) {
        int n = money.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return money[0];

        int[] arr1 = new int[n - 1];
        int[] arr2 = new int[n - 1];

        // Populate arr1 and arr2
        for (int i = 0; i < n; i++) {
            if (i != n - 1)
                arr1[i] = money[i];
            if (i != 0)
                arr2[i - 1] = money[i];
        }
        int ans1 = func_4(arr1);

        int ans2 = func_4(arr2);

        // Return the maximum of ans1 and ans2
        return Math.max(ans1, ans2);
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(N)
     */

    /*
        * House Robber I
     */
    public int rob(int[] nums) {
        int N = nums.length;

        // Special handling for empty array case.
        if (N == 0) {
            return 0;
        }

        int robNext, robNextPlusOne;

        // Base case initializations.
        robNextPlusOne = 0;
        robNext = nums[N - 1];

        // DP table calculations. Note: we are not using any
        // table here for storing values. Just using two
        // variables will suffice.
        for (int i = N - 2; i >= 0; --i) {
            // Same as the recursive solution.
            int current = Math.max(robNext, robNextPlusOne + nums[i]);

            // Update the variables
            robNextPlusOne = robNext;
            robNext = current;
        }

        return robNext;
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(1)
     */

}
