package _Arrays;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

 * we need to find max(prices[j]−prices[i]), for every i and j such that j>i.
 */
public class BestTimetoBuyandSellStock {
    /*
        * Approach: Brute Force
        *
        * Find the maximum profit can be achieved from every pair of numbers.
     */

    public int maxProfit(int[] profits){
        int maxProfit = 0;
        for(int i = 0; i < profits.length; ++i){
            for(int j = i + 1; j < profits.length; ++j){
                maxProfit = Math.max(maxProfit, (profits[j] - profits[i]));
            }
        }
        return maxProfit;
    }

    /*
        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */

    /*
        * Approach: One Pass
        *
        * If we plot the numbers of the given array on a graph, we get:
        * The points of interest are the peaks and valleys in the given graph.
        *
        * We need to find the largest price following each valley, which difference could be the max profit.
        * We can maintain two variables - minprice and maxprofit corresponding to the smallest valley and maximum profit (maximum difference between selling price and minprice) obtained so far respectively.
     */

    public int maxProfit_2(int[] profits){
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int i = 0; i < profits.length; ++i){
            if(profits[i] < minPrice) minPrice = profits[i];
            else if(profits[i] - minPrice > maxProfit){
                maxProfit = profits[i] - minPrice;
            }
        }

        return maxProfit;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
