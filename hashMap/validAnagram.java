package hashMap;

import java.util.Arrays;

/*
    https://leetcode.com/problems/valid-anagram/description/

 */
public class validAnagram {
    /*
        * Approach: Sorting
     */
    public boolean isAnagram_1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    /*
        Time Complexity: O(N log n)
        Space Complexity: O(1)
     */

    /*
        * Approach: Frequency Counter
        *
        * To examine if t is a rearrangement of s, we can count occurrences of each letter in the two strings and compare them.
        * We could use a hash table to count the frequency of each letter, however, since both s and t only contain letters from a to z,
        * a simple array of size 26 will suffice.
     */
    public boolean isAnagram_2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram_3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
     /*
        Time Complexity: O(N)
        Space Complexity: O(1)
     */
}
