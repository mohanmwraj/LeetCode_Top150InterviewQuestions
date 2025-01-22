package SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
    https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class longestSubstringWithoutRepeatingCharacters {
    /*
        * Approach: Brute Force
        *
        * Check all the substring one by one to see if it has no duplicate character.
     */

    public int lengthOfLongestSubstring_1(String s) {
        int n = s.length();

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (checkRepetition(s, i, j)) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }

        return res;
    }

    private boolean checkRepetition(String s, int start, int end) {
        Set<Character> chars = new HashSet<>();

        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (chars.contains(c)) {
                return false;
            }
            chars.add(c);
        }

        return true;
    }

    /*
        Time Complexity: O(n^3)
        Space Complexity: O(min(n, m))
     */
    /*
        * Approach: Sliding Window
        *
        * Given a substring with a fixed end index in the string,
        * maintain a HashMap to record the frequency of each character in the current substring.
        * If any character occurs more than once, drop the leftmost characters until there are no duplicate characters.
        *
     */
    public int lengthOfLongestSubstring_2(String s) {
        Map<Character, Integer> chars = new HashMap();

        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            chars.put(r, chars.getOrDefault(r, 0) + 1);

            while (chars.get(r) > 1) {
                char l = s.charAt(left);
                chars.put(l, chars.get(l) - 1);
                left++;
            }

            res = Math.max(res, right - left + 1);

            right++;
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(min(n, m))
     */

    /*
        * Approach: Sliding Window Optimized
        *
        * The above solution requires at most 2n steps.
        * In fact, it could be optimized to require only n steps.
        * Instead of using a set to tell if a character exists or not, we could define a mapping of the characters to its index.
        * Then we can skip the characters immediately when we found a repeated character.
     */
    public int lengthOfLongestSubstring_3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> charToNextIndex = new HashMap<>();
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (charToNextIndex.containsKey(s.charAt(j))) {
                i = Math.max(charToNextIndex.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            charToNextIndex.put(s.charAt(j), j + 1);
        }
        return ans;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(min(n, m))
     */
}
