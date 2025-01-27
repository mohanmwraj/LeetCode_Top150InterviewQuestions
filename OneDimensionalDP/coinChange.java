package OneDimensionalDP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
    https://leetcode.com/problems/coin-change/description/
 */
public class coinChange {
    /*
        * Approach: Recursion
        *
     */

    public int dfs_1(int[] coins, int amount) {
        if (amount == 0) return 0;

        int res = (int) 1e9;
        for (int coin : coins) {
            if (amount - coin >= 0) {
                res = Math.min(res,
                        1 + dfs_1(coins, amount - coin));
            }
        }
        return res;
    }

    public int coinChange_1(int[] coins, int amount) {
        int minCoins = dfs_1(coins, amount);
        return (minCoins >= 1e9) ? -1 : minCoins;
    }
    /*
        Time Complexity: O(n^t)
        Space Complexity: O(t)

        where n is the length of the array coins and t is the amount.
     */
    /*
        * Approach: Memoization Top - Down
        *
     */
    HashMap<Integer, Integer> memo = new HashMap<>();

    public int dfs_2(int amount, int[] coins) {
        if (amount == 0) return 0;
        if (memo.containsKey(amount))
            return memo.get(amount);

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (amount - coin >= 0) {
                int result = dfs_2(amount - coin, coins);
                if (result != Integer.MAX_VALUE) {
                    res = Math.min(res, 1 + result);
                }
            }
        }

        memo.put(amount, res);
        return res;
    }

    public int coinChange_2(int[] coins, int amount) {
        int minCoins = dfs_2(amount, coins);
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }
    /*
        Time Complexity: O(n * t)
        Space Complexity: O(t)

        where n is the length of the array coins and t is the amount.
     */
    /*
        * Approach: DP Bottom Up
        *
     */
    public int coinChange_3(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
    /*
        Time Complexity: O(n * t)
        Space Complexity: O(t)

        where n is the length of the array coins and t is the amount.
     */
    /*
        * Approach: BFS
        *
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        boolean[] seen = new boolean[amount + 1];
        seen[0] = true;
        int res = 0;

        while (!q.isEmpty()) {
            res++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                for (int coin : coins) {
                    int nxt = cur + coin;
                    if (nxt == amount) return res;
                    if (nxt > amount || seen[nxt]) continue;
                    seen[nxt] = true;
                    q.add(nxt);
                }
            }
        }

        return -1;
    }
    /*
        Time Complexity: O(n * t)
        Space Complexity: O(t)

        where n is the length of the array coins and t is the amount.
     */
}
