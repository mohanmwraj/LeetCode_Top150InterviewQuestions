package _Arrays;

/*
    https://leetcode.com/problems/integer-to-roman/description/

    Symbol	Value
        I	1
        V	5
        X	10
        L	50
        C	100
        D	500
        M	1000
        CM  900
        CD 400
        XC  90
        XL  40
        IX  9
        IV  4
 */

public class IntegerToRoman {
    /*
        * Approach: Greedy
        * Representing a given integer as a Roman Numeral requires finding a sequence of the above 13 symbols,
        * where their corresponding values add up to the integer.
        * This sequence must be in order from largest to smallest, based on symbol value.
        *
     */
    private static final int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman_1(int num) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < values.length && num > 0; ++i){
            while(values[i] <= num){
                num -= values[i];
                sb.append(symbols[i]);
            }

        }

        return sb.toString();
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
}
