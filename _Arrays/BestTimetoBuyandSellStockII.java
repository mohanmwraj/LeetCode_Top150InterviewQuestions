package _Arrays;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/

 */

public class BestTimetoBuyandSellStockII {
    /*
        * Approach: Brute Force
        *
        * we simply calculate the profit corresponding to all the possible sets of transactions and find out the maximum profit out of them.
     */

    public int maxProfits_1(int[] profits){
        return calculate(profits, 0);
    }

    private int calculate(int[] profits, int index){
        if(index >= profits.length) return 0;

        int max = 0;
        for(int i = index; i < profits.length; ++i){
            int maxProfit = 0;
            for(int j = i + 1; j < profits.length; ++j){
                int profit = profits[j] - profits[i] + calculate(profits, j + 1);
                if(profit > maxProfit) maxProfit = profit;
            }
            if(maxProfit > max) max = maxProfit;
        }
        return max;
    }

    /*
        Time complexity : O(n^n). Recursive function is called n^n times.
        Space Complexity: O(n)
     */

    /*
        * Approach: Peak Valley Approach
        *
        * Total Profit=∑i(height(peak of i)−height(valley of i))
        *
        * find all peak and valley and add it to the profits.

     */

    public int maxProfit_2(int[] profits){
        int valley = profits[0];
        int peak = profits[0];
        int maxProfit = 0;
        int i = 0;

        while(i < profits.length - 1){
            while(i < profits.length - 1 && profits[i] >= profits[i + 1]) i++;
            valley = profits[i];

            while(i < profits.length - 1 && profits[i] <= profits[i + 1]) i++;
            peak = profits[i];

            maxProfit += peak - valley;
        }

        return maxProfit;
    }
    /*
        Time complexity : O(n)
        Space Complexity: O(1)
     */

    /*
        * Approach: Simple One Pass
        *
        * This solution follows the logic used in Approach 2 itself, but with only a slight variation.
        * In this case, instead of looking for every peak following a valley, we can simply go on crawling over the slope
        * and keep on adding the profit obtained from every consecutive transaction.
        * In the end,we will be using the peaks and valleys effectively, but we need not track the costs corresponding to the peaks and valleys along with the maximum profit,
        * but we can directly keep on adding the difference between the consecutive numbers of the array if the second number is larger than the first one,
        * and at the total sum we obtain will be the maximum profit.
     */

    public int maxProfits_3(int[] profits){
        int n = profits.length;
        int maxProfit = 0;
        for(int i = 1; i < n; ++i){
            if(profits[i] > profits[i - 1]){
                maxProfit += profits[i] - profits[i -1];
            }
        }

        return maxProfit;
    }
    /*
        Time complexity : O(n).
        Space Complexity: O(1)
     */
}
