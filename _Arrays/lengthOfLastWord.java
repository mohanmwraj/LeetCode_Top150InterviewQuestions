package _Arrays;

/*
    https://leetcode.com/problems/length-of-last-word/description/
 */
public class lengthOfLastWord {

    /*
        * Approach: String Index Manipulation
        *
     */
    public int lengthOfLastWord_1(String s) {
        // trim the trailing spaces
        int p = s.length() - 1;
        while (p >= 0 && s.charAt(p) == ' ') {
            p--;
        }

        // compute the length of last word
        int length = 0;
        while (p >= 0 && s.charAt(p) != ' ') {
            p--;
            length++;
        }
        return length;
    }
    /*
    Time Complexity: O(n)
    Space Complexity: O(1)
    */

    /*
        * Approach: One-Loop Iteration
     */
    public int lengthOfLastWord_2(String s) {
        int p = s.length();
        int length = 0;

        while(p > 0){
            p--;

            if(s.charAt(p) != ' '){
                length++;
            } else if(length > 0){
                return length;
            }
        }
        return length;
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */

    /*
        * Approach: Built-in String Functions
        *
     */
    public int lengthOfLastWord_3(String s) {
        s = s.trim();
        return s.length() - s.lastIndexOf(" ") - 1;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

}
