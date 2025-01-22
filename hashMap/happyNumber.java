package hashMap;

/*
    https://leetcode.com/problems/happy-number/description/
 */

import java.util.HashSet;
import java.util.Set;

public class happyNumber {
    /*
        * Approach: Detect Cycle with a HashSet
        *
        * Three Possibilities:
        * 1. It eventually gets to 1.
        * 2. It eventually gets stuck in a cycle.
        * 3. It keeps going higher and higher, up towards infinity.
        *
        *
        Digits	Largest	        Next
            1	9	            81
            2	99	            162
            3	999	            243
            4	9999	        324
            13	9999999999999	1053
     */

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy_1(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    /*
        Time Complexity: O(log n)
        Space Complexity: O(log n)
     */

    /*
        * Approach: Floyd's Cycle-Finding Algorithm
        *
        * use fast and slow runner
        *
     */
    public boolean isHappy_2(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);

        while(fastRunner != 1 && slowRunner != fastRunner){
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }

        return fastRunner == 1;
    }
    /*
        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
}
