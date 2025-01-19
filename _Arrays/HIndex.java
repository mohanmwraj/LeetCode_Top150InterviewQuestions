package _Arrays;

/*
    * https://leetcode.com/problems/h-index/description/
    * The h-index is defined as the maximum value of h such that the given researcher has published at least h papers that have each been cited at least h times.
 */

import java.util.Arrays;

public class HIndex {
    /*
        * Approach: Sorting
        *
        * Think geometrically. Imagine plotting a histogram where the y-axis represents the number of citations for each paper.
        * After sorting in descending order, h-index is the length of the largest square in the histogram.
        * To find such a square length, we first sort the citations array in descending order.
        * After sorting, if citations[i]>i, then papers 0 to i all have at least i+1 citations.
        * Thus, to find h-index, we search for the largest i (let's call it i′) such that.
        * citations[i]>i
        * and therefore the h-index is i′+1.
        *
        *
          i	                0	    1	    2	    3	    4	    5	    6
          sorted citations	10	    9	    5	    3	    3	    2	    1
          citations[i]>i?	true	true	true	false	false	false	false
     */
    public int hIndex_1(int[] citations) {
        Arrays.sort(citations);
        int i = 0;

        while(i < citations.length && citations[citations.length - 1 - i] > i){
            i++;
        }
        return i;
    }
    /*
        Time complexity : O(nlogn)
        Space complexity : O(1)
     */

    /*
        * Approach: Counting
        *
        * From Approach #1, we sort the citations to find the h-index.
        * However, it is well known that comparison sorting algorithms such as heapsort, mergesort and quicksort have a lower bound of O(nlogn).
        * The most commonly used non-comparison sorting is counting sort.
        *
        * Any citation larger than n can be replaced by n and the h-index will not change after the replacement
        *
        * The reason is that h-index is upper bounded by total number of papers n, i.e. h ≤ n
        * Apparently, cutting that area off will not change the largest square and the h-index.
        * After we have the counts, we can get a sorted citations by traversing the counts array. And the rest is the same as Approach #1.
        * But we can do even better. The idea is that we don't even need to get sorted citations. We can find the h-index by using the paper counts directly.
        *
        * citations=[1,3,2,3,100]
        *
            k	0	1	2	3	4	5
        count	0	1	1	2	0	1
        sk  	5	5	4	3	1	1
        *
        * The value sk is defined as "the sum of all counts with citation ≥k" or
        * "the number of papers having, at least, k citations".
        * By definition of the h-index, the largest k with k≤sk is our answer.
     */
    public int hIndex_2(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];

        for(int c: citations) papers[Math.min(c, n)]++;

        int k = n;
        for(int s = papers[n]; s < k; s += papers[k]){
            k--;
        }
        return k;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
