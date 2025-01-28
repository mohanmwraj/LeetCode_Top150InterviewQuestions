package bitManipulation;

import java.math.BigInteger;

/*
    https://leetcode.com/problems/add-binary/description/
 */
public class addBinary {
    /*
        * Approach: In-Built Functions
        *
     */
    public String addBinary_1(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }

    /*
        * Approach: Bit-by-Bit Computation
        *
     */
    public String addBinary_2(String a, String b) {
        int n = a.length(), m = b.length();
        if (n < m) return addBinary_2(b, a);

        StringBuilder sb = new StringBuilder();
        int carry = 0, j = m - 1;
        for (int i = n - 1; i > -1; --i) {
            if (a.charAt(i) == '1') ++carry;
            if (j > -1 && b.charAt(j--) == '1') ++carry;

            if (carry % 2 == 1) sb.append('1');
            else sb.append('0');

            carry /= 2;
        }
        if (carry == 1) sb.append('1');
        sb.reverse();

        return sb.toString();
    }
    /*
        Time Complexity: O(max(N, M))
        Space Complexity: O(max(N, M))
     */

    /*
        * Approach: Bit Manipulation
        *
        * Iterator until y becomes 0.
        * x ^ y = answer
        * (X & Y) << 1 = carry
        * x = answer
        * y = carry
        *
     */
    public String addBinary_3(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger zero = new BigInteger("0", 2);
        BigInteger carry, answer;
        while (y.compareTo(zero) != 0) {
            answer = x.xor(y);
            carry = x.and(y).shiftLeft(1);
            x = answer;
            y = carry;
        }
        return x.toString(2);
    }
    /*
        Time Complexity: O(N + M)
        Space Complexity: O(max(N, M))
     */
}
