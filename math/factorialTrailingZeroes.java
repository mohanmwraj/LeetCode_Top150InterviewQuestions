package math;

import java.math.BigInteger;

/*
    https://leetcode.com/problems/factorial-trailing-zeroes/description/
 */
public class factorialTrailingZeroes {
    /*
        * Approach: Compute the Factorial
        *
        * define function zero_count(x):
            zero_count = 0
            while x is divisible by 10:
                zero_count += 1
                x = x / 10
            return zero_count
     */
    public int trailingZeroes_1(int n) {
        // Calculate n!
        BigInteger nFactorial = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            nFactorial = nFactorial.multiply(BigInteger.valueOf(i));
        }

        // Count how many 0's are on the end.
        int zeroCount = 0;

        while (nFactorial.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
            nFactorial = nFactorial.divide(BigInteger.TEN);
            zeroCount++;
        }

        return zeroCount;
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(logn!)=O(nlogn).
     */

    /*
        * Approach: Counting Factors of 5
        *
        * 3150 = 42 * 75
        * 42 = 2 * 3 * 7
        * 75 = 3 * 5 * 5
        * 42 * 25 = 2 * 3 * 7 * 3 * 5 * 5
        *
        * in order to determine how many zeroes are on the end,
        * we should look at how many complete pairs of 2 and 5 are among the factors.
        *
        * In the case of the example above, we have one 2 and two 5s, giving us one complete pair.
        *
        * In this calculation 2 appears more time than the 5.
        * so we completely remove the 2, count only the factors of 5.
        *
        * Instead of starting from 1 to n, we can start from 5 to n and increment by 5 every time.
        *
        *   twos = 0
            for i from 1 to n inclusive:
                remaining_i = i
                while remaining_i is divisible by 2:
                    twos += 1
                    remaining_i = remaining_i / 2

            fives = 0
            for i from 1 to n inclusive:
                remaining_i = i
                while remaining_i is divisible by 5:
                    fives += 1
                    remaining_i = remaining_i / 5

            tens = min(twos, fives)
     */
    public int trailingZeroes_2(int n) {
        int zeroCount = 0;
        for (int i = 5; i <= n; i += 5) {
            int currentFactor = i;
            while (currentFactor % 5 == 0) {
                zeroCount++;
                currentFactor /= 5;
            }
        }
        return zeroCount;
    }
    public int trailingZeroes_3(int n) {
        int zeroCount = 0;
        for (int i = 5; i <= n; i += 5) {
            int powerOf5 = 5;
            while (i % powerOf5 == 0) {
                zeroCount += 1;
                powerOf5 *= 5;
            }
        }
        return zeroCount;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Counting Factors of 5
        *
        * n/5 + n/25 + n/625 + ....
        *
        *   fives = 0
            power_of_5 = 5
            while n >= power_of_5:
                fives += n / power_of_5
                power_of_5 *= 5

            tens = fives
     */
    public int trailingZeroes_4(int n) {
        int zeroCount = 0;
        // We need to use long because currentMultiple can potentially become
        // larger than an int.
        long currentMultiple = 5;
        while (n >= currentMultiple) {
            zeroCount += (n / currentMultiple);
            currentMultiple *= 5;
        }
        return zeroCount;
    }
    /*
        * n/5 + n/25 + n/625 + ....
        *
        * we can write above equation as
        *
        * (n/5)/5 = n/25 ...
        * (((n/5)/5)/5) = n / 625..
     */
    public int trailingZeroes_5(int n) {
        int zeroCount = 0;
        long currentMultiple = 5;
        while (n > 0) {
            n /= 5;
            zeroCount += n;
        }
        return zeroCount;
    }

    /*
        Time Complexity: O(log N)
        Space Complexity: O(1)
     */
}
