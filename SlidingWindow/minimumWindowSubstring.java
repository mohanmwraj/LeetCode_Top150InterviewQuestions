package SlidingWindow;

import java.util.*;
//import javafx.util.*;

/*
    https://leetcode.com/problems/minimum-window-substring/description/

    Input: s = "ADOBECODEBANC", t = "ABC"
    Output: "BANC"
 */
public class minimumWindowSubstring {
    /*
        * Approach: Sliding Window
        *
        * The question asks us to return the minimum window from the string S which has all the characters of the string T.
        *
        * We start with two pointers, left and right initially pointing to the first element of the string S.
        * We use the right pointer to expand the window until we get a desirable window i.e. a window that contains all of the characters of T.
        * Once we have a window with all the characters, we can move the left pointer ahead one by one.
        * If the window is still a desirable one we keep on updating the minimum window size.
        * If the window is not desirable any more, we repeat step2 onwards.
     */
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        // Dictionary which keeps a count of all the unique characters in t.
        Map<Character, Integer> dictT = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }

        // Number of unique characters in t, which need to be present in the desired window.
        int required = dictT.size();

        // Left and Right pointer
        int l = 0, r = 0;

        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;

        // Dictionary which keeps a count of all the unique characters in the current window.
        Map<Character, Integer> windowCounts = new HashMap<
                Character,
                Integer
                >();

        // ans list of the form (window length, left, right)
        int[] ans = { -1, 0, 0 };

        while (r < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (
                    dictT.containsKey(c) &&
                            windowCounts.get(c).intValue() == dictT.get(c).intValue()
            ) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = s.charAt(l);
                // Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }

                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (
                        dictT.containsKey(c) &&
                                windowCounts.get(c).intValue() < dictT.get(c).intValue()
                ) {
                    formed--;
                }

                // Move the left pointer ahead, this would help to look for a new window.
                l++;
            }

            // Keep expanding the window once we are done contracting.
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
    /*
        Time Complexity: O(∣S∣+∣T∣)
        Space Complexity: O(∣S∣+∣T∣)
     */

    /*
        * Approach: Optimized Sliding Window
        *
        * A small improvement to the above approach can reduce the time complexity of the algorithm to O(2∗∣filtered_S∣+∣S∣+∣T∣),
        * where filtered_S is the string formed from S by removing all the elements not present in T.
        *
        * This complexity reduction is evident when ∣filtered_S∣<<<∣S∣.
        *
            S = "ABCDDDDDDEEAFFBC" T = "ABC"
            filtered_S = [(0, 'A'), (1, 'B'), (2, 'C'), (11, 'A'), (14, 'B'), (15, 'C')]
            Here (0, 'A') means in string S character A is at index 0.
      */
//    public record Pair<k, v>(k key, v value){
//
//    }
    public class Pair<K, V> {

        private final K element0;
        private final V element1;

        public <K, V> Pair<K, V> createPair(K element0, V element1) {
            return new Pair<K, V>(element0, element1);
        }

        public Pair(K element0, V element1) {
            this.element0 = element0;
            this.element1 = element1;
        }

        public K getKey() {
            return element0;
        }

        public V getValue() {
            return element1;
        }

    }

    class Solution {
        public String minWindow(String s, String t) {
            if (s.length() == 0 || t.length() == 0) {
                return "";
            }

            Map<Character, Integer> dictT = new HashMap<Character, Integer>();

            for (int i = 0; i < t.length(); i++) {
                int count = dictT.getOrDefault(t.charAt(i), 0);
                dictT.put(t.charAt(i), count + 1);
            }

            int required = dictT.size();

            // Filter all the characters from s into a new list along with their index.
            // The filtering criteria is that the character should be present in t.
            List<Pair<Integer, Character>> filteredS = new ArrayList<
                                Pair<Integer, Character>
                                >();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (dictT.containsKey(c)) {
                    filteredS.add(new Pair<Integer, Character>(i, c));
                }
            }

            int l = 0, r = 0, formed = 0;
            Map<Character, Integer> windowCounts = new HashMap<
                    Character,
                    Integer
                    >();
            int[] ans = { -1, 0, 0 };

            // Look for the characters only in the filtered list instead of entire s.
            // This helps to reduce our search.
            // Hence, we follow the sliding window approach on as small list.
            while (r < filteredS.size()) {
                char c = filteredS.get(r).getValue();
                int count = windowCounts.getOrDefault(c, 0);
                windowCounts.put(c, count + 1);

                if (
                        dictT.containsKey(c) &&
                                windowCounts.get(c).intValue() == dictT.get(c).intValue()
                ) {
                    formed++;
                }

                // Try and contract the window till the point where it ceases to be 'desirable'.
                while (l <= r && formed == required) {
                    c = filteredS.get(l).getValue();

                    // Save the smallest window until now.
                    int end = filteredS.get(r).getKey();
                    int start = filteredS.get(l).getKey();
                    if (ans[0] == -1 || end - start + 1 < ans[0]) {
                        ans[0] = end - start + 1;
                        ans[1] = start;
                        ans[2] = end;
                    }

                    windowCounts.put(c, windowCounts.get(c) - 1);
                    if (
                            dictT.containsKey(c) &&
                                    windowCounts.get(c).intValue() < dictT.get(c).intValue()
                    ) {
                        formed--;
                    }
                    l++;
                }
                r++;
            }
            return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
        }
    }
    /*
        Time Complexity : O(∣S∣+∣T∣)
        Space Complexity : O(∣S∣+∣T∣)
     */
}
