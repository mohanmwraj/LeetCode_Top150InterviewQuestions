package _Arrays;

/*
    https://leetcode.com/problems/roman-to-integer/description/

    Symbol          Value
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000
 */

import java.util.HashMap;
import java.util.Map;

public class romanToInteger {
    /*
        * Approach: Left to Right Pass
        *
        * Let's hard-code a mapping with the value of each symbol so that we can easily look them up.
        *
        * Now, recall that each symbol adds its own value, except for
        * when a smaller valued symbol is before a larger valued symbol.
        * In those cases, instead of adding both symbols to the total,
        * we need to subtract the large from the small, adding that instead.
     */
    static Map<String, Integer> values = new HashMap<>();

    static {
        values.put("M", 1000);
        values.put("D", 500);
        values.put("C", 100);
        values.put("L", 50);
        values.put("X", 10);
        values.put("V", 5);
        values.put("I", 1);
    }

    public int romanToInt_1(String s) {
        int sum = 0;
        int i = 0;
        while (i < s.length()) {
            String currentSymbol = s.substring(i, i + 1);
            int currentValue = values.get(currentSymbol);
            int nextValue = 0;
            if (i + 1 < s.length()) {
                String nextSymbol = s.substring(i + 1, i + 2);
                nextValue = values.get(nextSymbol);
            }

            if (currentValue < nextValue) {
                sum += (nextValue - currentValue);
                i += 2;
            } else {
                sum += currentValue;
                i += 1;
            }
        }
        return sum;
    }

    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */

    /*
        * Approach: Left-to-Right Pass Improved
        *
        * Instead of viewing a Roman Numeral as having 7 unique symbols,
        * we could instead view it as having 13 unique symbols—some of length-1 and some of length-2.
     */
    static {
        values.put("I", 1);
        values.put("V", 5);
        values.put("X", 10);
        values.put("L", 50);
        values.put("C", 100);
        values.put("D", 500);
        values.put("M", 1000);
        values.put("IV", 4);
        values.put("IX", 9);
        values.put("XL", 40);
        values.put("XC", 90);
        values.put("CD", 400);
        values.put("CM", 900);
    }

    public int romanToInt_2(String s) {
        int sum = 0;
        int i = 0;
        while (i < s.length()) {
            if (i < s.length() - 1) {
                String doubleSymbol = s.substring(i, i + 2);
                // Check if this is the length-2 symbol case.
                if (values.containsKey(doubleSymbol)) {
                    sum += values.get(doubleSymbol);
                    i += 2;
                    continue;
                }
            }
            // Otherwise, it must be the length-1 symbol case.
            String singleSymbol = s.substring(i, i + 1);
            sum += values.get(singleSymbol);
            i += 1;
        }
        return sum;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */

    /*
        * Approach: Right-to-Left Pass
        *
        * We need to determine whether our current symbol should be added or
        * subtracted by looking at the neighbour though.
        *
     */
    static {
        values.put("M", 1000);
        values.put("D", 500);
        values.put("C", 100);
        values.put("L", 50);
        values.put("X", 10);
        values.put("V", 5);
        values.put("I", 1);
    }

    public int romanToInt_3(String s) {
        String lastSymbol = s.substring(s.length() - 1);
        int lastValue = values.get(lastSymbol);
        int total = lastValue;

        for (int i = s.length() - 2; i >= 0; i--) {
            String currentSymbol = s.substring(i, i + 1);
            int currentValue = values.get(currentSymbol);
            if (currentValue < lastValue) {
                total -= currentValue;
            } else {
                total += currentValue;
            }
            lastValue = currentValue;
        }
        return total;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
}
