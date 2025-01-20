package _Arrays;

import java.util.Arrays;

/*
    https://leetcode.com/problems/candy/description/
 */
public class candy {
    /*
        * Approach: Brute Force
        *
        * Assign one candy each child initially.
        * In each traversal, check left and right neighbours ratings, if they are greater than no need to update,
        * else we increment the candy for current kid.
        * Track changes in boolean variable.
        * If no change happened in any of the traversal, we stop the iteration.
        *
        *
     */
    public int candy_1(int[] ratings){
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        boolean hasChanged = true;
        while(hasChanged){
            hasChanged = false;

            for(int i = 0; i < ratings.length; ++i){
                if(i != ratings.length - 1 && ratings[i] > ratings[i + 1]
                    && candies[i] <= candies[i + 1]){
                    candies[i] = candies[i + 1] + 1;
                    hasChanged = true;
                }

                if(i > 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]){
                    candies[i] = candies[i - 1] + 1;
                    hasChanged = true;
                }
            }
        }

        int sum = 0;
        for(int num: candies) sum += num;
        return sum;
    }

    /*
        Time complexity : O(n^2)
        Space Complexity: O(n)
     */
    /*
        * Approach: Using Two Arrays
        *
        * The left2right array is used to store the number of candies required by the current student
        * taking care of the distribution relative to the left neighbors only.
        * i.e. Assuming the distribution rule is: The student with a higher rating than
        * their left neighbor should always get more candies than its left neighbor.
        *
        * Similarly, the right2left array is used to store the number of candies required by
        * the current student taking care of the distribution relative to the right neighbors only.
        * i.e. Assuming the distribution rule to be: The student with a higher rating than
        * their right neighbor should always get more candies than their right neighbor.
     */

    public int candy_2(int[] ratings){
        int len = ratings.length;

        int[] left2Right = new int[len];
        Arrays.fill(left2Right, 1);
        for(int i = 1; i < len; ++i){
            if(ratings[i] > ratings[i - 1]){
                left2Right[i] = left2Right[i - 1] + 1;
            }
        }

        int[] right2Left = new int[len];
        Arrays.fill(right2Left, 1);
        for(int i = len - 2; i >= 0; --i){
            if(ratings[i] > ratings[i + 1]){
                right2Left[i] = right2Left[i + 1] + 1;
            }
        }

        int sum = 0;
        for(int i = 0; i < len; ++i){
            sum += Math.max(right2Left[i], left2Right[i]);
        }
        return sum;
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Using One Array
     */
    public int candy_3(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        int sum = candies[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            sum += candies[i];
        }
        return sum;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Single Pass Approach with Constant Space
        *
        * The candies are always distributed in terms of increments of 1.
        * Further, while distributing the candies, the local minimum number of candies given to a student is 1.
        * Thus, the sub-distributions always take the following form: 1, 2, 3, ..., n or n,..., 2, 1. Which, can simply be added using the formula n(n+1)/2.
        *
        * we can view the given rankings as some rising and falling slopes.
        * Whenever the slope is rising, the distribution takes the form: 1, 2, 3, ..., m.
        * Similarly, a falling slope takes the form: k,..., 2, 1.
        *
        * A challenge that arises now is that the local peak point can be included in only one of the slopes.
        * Should we include the local peak point, n, in the rising slope or the in falling slope?
        *
        * In order to decide, we can observe that in order to satisfy both the right neighbor
        * and the left neighbor criteria, the peak point's count
        * needs to be the max. of the counts determined by the rising and the falling slopes.
     */
    private int count(int n){
        return (n * (n + 1)) / 2;
    }
    public int candy_4(int[] ratings){
        int candies = 0;
        int up = 0, down = 0;
        int oldSlope = 0; // 1 - increasing slope, -1 - falling slope, 0 - flat.

        for(int i = 1; i < ratings.length; ++i){
            int newSlope = (ratings[i] > ratings[i - 1]) ? 1 : (ratings[i] < ratings[i - 1]) ? -1 : 0;

            if((oldSlope > 0 && newSlope == 0) || (oldSlope < 0 && newSlope >= 0)){
                candies += count(up) + count(down) + Math.max(up, down);
                up = 0;
                down = 0;
            }

            if(newSlope > 0) up++;
            else if(newSlope < 0) down++;
            else candies++;

            oldSlope = newSlope;
        }
        candies += count(up) + count(down) + Math.max(up, down);
        return candies;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
