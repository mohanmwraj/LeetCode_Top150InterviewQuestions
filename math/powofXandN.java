package math;
/*
    https://leetcode.com/problems/powx-n/
 */
public class powofXandN {
    /*
        * Approach: Binary Exponentiation (Recursive)
        *
        * x^n = 1 / x ^ -n, to handle -ve cases.
        *
        *    func pow(x, n):
        *        if n == 0: return 1
        *        if n < 0: return 1 / pow(x, -n)
        *        return x * pow(x, n - 1)
        *
        * Binary exponentiation, also known as exponentiation by squaring,
        * is a technique for efficiently computing the power of a number.
        *
        * (x^2)^n/2 if n is even
        * x∗(x^2)^(n−1)/2 if n is odd (we separate out one x, then n−1 will become even)
        *
        * func binaryExp(x, n):
        *    if n == 0: return 1.0
        *    if n < 0: return 1.0 / binaryExp(x, -n)
        *
        *    // Binary exponentiation steps.
        *    if n is odd: return x * binaryExp(x * x, (n - 1) / 2)
        *    otherwise: return binaryExp(x * x, n / 2)
        *
     */
    private double binaryExp_1(double x, long n) {
        // Base case, to stop recursive calls.
        if (n == 0) {
            return 1;
        }

        // Handle case where, n < 0.
        if (n < 0) {
            return 1.0 / binaryExp_1(x, -1 * n);
        }

        // Perform Binary Exponentiation.
        // If 'n' is odd we perform Binary Exponentiation on 'n - 1' and multiply result with 'x'.
        if (n % 2 == 1) {
            return x * binaryExp_1(x * x, (n - 1) / 2);
        }
        // Otherwise we calculate result by performing Binary Exponentiation on 'n'.
        else {
            return binaryExp_1(x * x, n / 2);
        }
    }

    public double myPow_1(double x, int n) {
        return binaryExp_1(x, (long) n);
    }
    /*
        Time Complexity: O(log N)
        Space Complexity: O(log N)
     */

    /*
        * Approach: Binary Exponentiation (Iterative)
        *
     */
    private double binaryExp_2(double x, long n) {
        if (n == 0) {
            return 1;
        }

        // Handle case where, n < 0.
        if (n < 0) {
            n = -1 * n;
            x = 1.0 / x;
        }

        // Perform Binary Exponentiation.
        double result = 1;
        while (n != 0) {
            // If 'n' is odd we multiply result with 'x' and reduce 'n' by '1'.
            if (n % 2 == 1) {
                result = result * x;
                n -= 1;
            }
            // We square 'x' and reduce 'n' by half, x^n => (x^2)^(n/2).
            x = x * x;
            n = n / 2;
        }
        return result;
    }

    public double myPow_2(double x, int n) {
        return binaryExp_2(x, (long) n);
    }
    /*
        Time Complexity: O(log N)
        Space Complexity: O(1)
     */
}
