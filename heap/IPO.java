package heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/*
    https://leetcode.com/problems/ipo/description/

    Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
    Output: 4

 */
public class IPO {

    /*
        * Approach: Greedy
        *
        * When you don't know, how to solve the problem completely, think of its simpler subproblems.
        *
        * First, we greedily choose the most profitable available project.
        * Then our capital increases by the profit of this project,
        * and some new projects that were unavailable before might become available now.
        *
        * If we choose a project other than the most profitable one,
        * our capital increases by a value less than the maximum possible,
        * and fewer new options become available.
        *
        * It means we should greedily choose the maximum profit every time.
        *
     */
    class Project implements Comparable<Project>{
        int capital, profit;

        public Project(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }

        @Override
        public int compareTo(Project project) {
            return capital - project.capital;
        }
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital){
        int n = profits.length;

        Project[] projects = new Project[n];
        for(int i = 0; i < n; ++i){
            projects[i] = new Project(capital[i], profits[i]);
        }

        Arrays.sort(projects);
        PriorityQueue<Integer>  pq = new PriorityQueue<>(n, Collections.reverseOrder());
        int ptr = 0;

        for(int i = 0; i < n; ++i){
            while(ptr < n && projects[ptr].capital <= w){
                pq.add(projects[ptr++].profit);
            }

            if(pq.isEmpty()) break;
            w += pq.poll();
        }
        return w;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */
}
