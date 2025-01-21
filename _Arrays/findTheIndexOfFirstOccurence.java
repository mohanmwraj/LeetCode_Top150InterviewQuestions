package _Arrays;

/*
    https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
 */

public class findTheIndexOfFirstOccurence {
    /*
        * Approach: Sliding Window
        *
        * We will create a window of size m and slide it across the haystack.
        * We will keep track of the starting index of the window in a variable window_start.
        * For every window_start, we will iterate till window_start + m. During each iteration,
        * if the ith character in the window is equal to the ith character in the needle, then we will increment i by 1.
        * if the ith character in the window is not equal to the ith character in the needle, then we conclude that the substring of length m starting from index window_start cannot be equal to the needle, and we will reset window_start to window_start + 1.
        *
        * If all the ith characters in the window are equal to the ith characters of needle, then we will return the window_start.
     */

    public int strStr(String hayStack, String needle){
        int n = hayStack.length();
        int m = needle.length();

        for(int windowStart = 0; windowStart <= n - m; ++windowStart){
            for(int i = 0; i < m; ++i){
                if(needle.charAt(i) != hayStack.charAt(windowStart + i)){
                    break;
                }
                if(i == m - 1) return windowStart;
            }
        }
        return -1;
    }

    /*
        Time Complexity: O(N*M)
        Space Complexity: O(1)
     */
}
