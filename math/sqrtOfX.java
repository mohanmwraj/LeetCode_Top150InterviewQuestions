package math;
/*
    https://leetcode.com/problems/sqrtx/description/
 */
public class sqrtOfX {
    /*
        * Approach: Pocket Calculator Algorithm
        *
        * Square root of x = e ^ (1/2 * log x)
        *
     */
    public int mySqrt_1(int x){
        if(x < 2) return x;

        int left = (int) Math.pow(Math.E, 0.5 * Math.log(x));
        int right = left + 1;

        return (long) right * right > x ? left : right;
    }

    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */

    /*
        * Approach: Binary Search
        *
        * for x >= 2, the square root is always smaller than x/2 and larger than 0.
        * o < a < x / 2.
        *
     */

    public int mySqrt_2(int x){
        if(x < 2) return x;

        long num;
        int pivot = 0;
        int left = 2, right = x / 2;

        while(left <= right){
            pivot = left + (right - left) / 2;
            num = (long)pivot * pivot;

            if(num > x) right = pivot - 1;
            else if(num < x) left = pivot + 1;
            else return pivot;
        }

        return pivot;
    }

    /*
        Time Complexity = O(log N)
        Space Complexity = O(1)
     */

    /*
        * Approach: Recursion + Bit Shifts
        *
        * mySqrt(x) = 2 * mySqrt(x / 2)
        *
        * x << y that means x * 2 ^ y
        * x >> y that means x / 2 ^ y
        *
        * mySqrt(x) = mySqrt(x >> 2) << 1.
        *
     */

    public int mySqrt_3(int x){
        if(x < 2) return x;

        int left = mySqrt_3(x >> 2) << 1;
        int right = left + 1;

        return (long)right * right > x ? left : right;
    }
    /*
        Time Complexity = O(log N)
        Space Complexity = O(log N)
     */
}
