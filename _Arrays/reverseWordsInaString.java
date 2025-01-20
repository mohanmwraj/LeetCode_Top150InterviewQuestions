package _Arrays;
/*
    https://leetcode.com/problems/reverse-words-in-a-string/description/
 */

import java.util.*;

public class reverseWordsInaString {
    /*
     * Approach: Built-in Split + Reverse
     */
    public String reverseWords_1(String s) {
        // remove leading spaces
        s = s.trim();
        // split by multiple spaces
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
     * Approach: Reverse the Whole String and then Reverse Each Word
     */

    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // remove leading spaces
        while (left <= right && s.charAt(left) == ' ') ++left;

        // remove trailing spaces
        while (left <= right && s.charAt(right) == ' ') --right;

        // reduce multiple spaces to single one
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') sb.append(c);
            else if (sb.charAt(sb.length() - 1) != ' ') sb.append(c);

            ++left;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // go to the end of the word
            while (end < n && sb.charAt(end) != ' ') ++end;
            // reverse the word
            reverse(sb, start, end - 1);
            // move to the next word
            start = end + 1;
            ++end;
        }
    }

    public String reverseWords_2(String s) {
        // convert string to string builder
        // and trim spaces at the same time
        StringBuilder sb = trimSpaces(s);

        // reverse the whole string
        reverse(sb, 0, sb.length() - 1);

        // reverse each word
        reverseEachWord(sb);

        return sb.toString();
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Deque of Words
     */
    public String reverseWords_3(String s){
        int left = 0;
        int right = s.length() - 1;

        StringBuilder sb = new StringBuilder();
        Deque<String> dq = new ArrayDeque<>();

        while(left <= right && s.charAt(left) == ' ') left++;
        while(left <= right && s.charAt(right) == ' ') right--;

        while(left <= right){
            char c = s.charAt(left);
            if((!sb.isEmpty()) && c == ' '){
                dq.offerFirst(sb.toString());
                sb.setLength(0);
            } else if(c != ' '){
                sb.append(c);
            }
            left++;
        }

        dq.offerFirst(sb.toString());
        return String.join(" ", dq);
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
