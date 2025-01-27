package OneDimensionalDP;
/*
    https://leetcode.com/problems/longest-increasing-subsequence/description/
 */
import java.util.Arrays;
public class longestIncreasingSubsequence {
    /*
    f(ind, prev_index){
        notTake = 0 + f(ind + 1, prev_index)

        if(prev_index == -1 || arr[ind]>arr[prev_index]){
            take = 1 + f(ind + 1, ind)
        }
    }
     */

    /*
        * Approach: Recursion + Memoization
        *
        * Include and Exclude
        *
        * current index either contributing or not contributing to the sequence.
        *
     */
    static int getAns_1(int arr[], int n, int ind, int prev_index, int[][] dp) {
        // Base condition
        if (ind == n) {
            return 0;
        }

        if (dp[ind][prev_index + 1] != -1) {
            return dp[ind][prev_index + 1];
        }

        int notTake = 0 + getAns_1(arr, n, ind + 1, prev_index, dp);

        int take = 0;

        if (prev_index == -1 || arr[ind] > arr[prev_index]) {
            take = 1 + getAns_1(arr, n, ind + 1, ind, dp);
        }

        dp[ind][prev_index + 1] = Math.max(notTake, take);

        return dp[ind][prev_index + 1];
    }

    // Function to find the length of the longest increasing subsequence
    static int longestIncreasingSubsequence_1(int arr[], int n) {
        int dp[][] = new int[n][n + 1];

        // Initialize dp array with -1 to mark states as not calculated yet
        for (int row[] : dp) {
            Arrays.fill(row, -1);
        }

        return getAns_1(arr, n, 0, -1, dp);
    }
    /*
        Time Complexity: O(N*N)
        Space Complexity: O(N*N) + O(N)
     */
    /*
        * Approach: Tabulation
        *
     */
    static int longestIncreasingSubsequence_2(int arr[], int n){

        int dp[][]=new int[n+1][n+1];

        for(int ind = n-1; ind>=0; ind --){
            for (int prev_index = ind-1; prev_index >=-1; prev_index --){

                int notTake = 0 + dp[ind+1][prev_index +1];

                int take = 0;

                if(prev_index == -1 || arr[ind] > arr[prev_index]){

                    take = 1 + dp[ind+1][ind+1];
                }

                dp[ind][prev_index+1] = Math.max(notTake,take);

            }
        }

        return dp[0][0];
    }
    /*
        Time Complexity: O(N*N)
        Space Complexity: O(N*N)
     */
    /*
        * Approach: Space Optimization
        *
        * If we closely we are using two rows: dp[ind+1][ ], dp[ind][ ],
        * So we are not required to contain an entire array, we can simply have two rows next and cur where next corresponds to dp[ind+1] and cur to dp[ind].
        * After declaring next and cur, replace dp[ind+1] to next and dp[ind] with cur and after the inner loop executes,
        * we will set next = cur, so that the cur row can serve as next for the coming iteration.
        *
     */
    static int longestIncreasingSubsequence(int arr[], int n){

        int next[]=new int[n+1];
        int cur[]=new int[n+1];

        for(int ind = n-1; ind>=0; ind --){
            for (int prev_index = ind-1; prev_index >=-1; prev_index --){

                int notTake = 0 + next[prev_index +1];

                int take = 0;

                if(prev_index == -1 || arr[ind] > arr[prev_index]){

                    take = 1 + next[ind+1];
                }

                cur[prev_index+1] = Math.max(notTake,take);
            }
            next = cur.clone();
        }

        return cur[0];
    }
    /*
        Time Complexity: O(N*N)
        Space Complexity: O(N)
     */

}
