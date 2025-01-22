package SlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
    https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/

    Input: s = "barfoothefoobarman", words = ["foo","bar"]
    Output: [0,9]
 */
public class subStringWithConcatenationOfAllWords {
    /*
        * Approach: Check All Indices Using a Hash Table
        *
        * all elements in words have the same length.
        * This gives us valuable information about all valid substrings - we know what length they will be.
        * valid substring has a length of words.length * words[0].length.
        *
        * This makes it easy for us to take a given index and check if a valid substring starting at this index exists.
        *
        * Iterate from the starting index to the starting index plus the size of a valid substring.
        * Iterate words[0].length characters at a time.
        * At each iteration, we will look at a substring with the same length as the elements in words.
        * If the substring doesn't exist in words, or it does exist but we already found the necessary amount of it, then return false.
        * We can use a hash table to keep an updated count of the words between the starting index and the current index.
        *
     */
    private final HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
    private int wordLength;
    private int substringSize;
    private int k;

    private boolean check(int i, String s) {
        // Copy the original dictionary to use for this index
        HashMap<String, Integer> remaining = new HashMap<>(wordCount);
        int wordsUsed = 0;

        // Each iteration will check for a match in words
        for (int j = i; j < i + substringSize; j += wordLength) {
            String sub = s.substring(j, j + wordLength);
            if (remaining.getOrDefault(sub, 0) != 0) {
                remaining.put(sub, remaining.get(sub) - 1);
                wordsUsed++;
            } else {
                break;
            }
        }

        return wordsUsed == k;
    }

    public List<Integer> findSubstring_1(String s, String[] words) {
        int n = s.length();
        k = words.length;
        wordLength = words[0].length();
        substringSize = wordLength * k;

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n - substringSize + 1; i++) {
            if (check(i, s)) {
                answer.add(i);
            }
        }

        return answer;
    }
    /*
        Time complexity: O(n⋅a⋅b−(a⋅b)^2)
        Space Complexity: O(a + b)
     */
    /*
        * Approach: Sliding Window
        *
        * we repeated a lot of computation - each character of s is iterated over many times.
        * Maintain a sliding window of characters.
     */

//    private HashMap<String, Integer> wordCount_1 = new HashMap<String, Integer>();
     private int n;
//    private int wordLength_1;
//    private int substringSize_1;
//    private int k;

    private void slidingWindow(int left, String s, List<Integer> answer) {
        HashMap<String, Integer> wordsFound = new HashMap<>();
        int wordsUsed = 0;
        boolean excessWord = false;

        // Do the same iteration pattern as the previous approach - iterate
        // word_length at a time, and at each iteration we focus on one word
        for (int right = left; right <= n - wordLength; right += wordLength) {
            String sub = s.substring(right, right + wordLength);
            if (!wordCount.containsKey(sub)) {
                // Mismatched word - reset the window
                wordsFound.clear();
                wordsUsed = 0;
                excessWord = false;
                left = right + wordLength;
            } else {
                // If we reached max window size or have an excess word
                while (right - left == substringSize || excessWord) {
                    String leftmostWord = s.substring(left, left + wordLength);
                    left += wordLength;
                    wordsFound.put(
                            leftmostWord,
                            wordsFound.get(leftmostWord) - 1
                    );

                    if (
                            wordsFound.get(leftmostWord) >=
                                    wordCount.get(leftmostWord)
                    ) {
                        // This word was an excess word
                        excessWord = false;
                    } else {
                        // Otherwise we actually needed it
                        wordsUsed--;
                    }
                }

                // Keep track of how many times this word occurs in the window
                wordsFound.put(sub, wordsFound.getOrDefault(sub, 0) + 1);
                if (wordsFound.get(sub) <= wordCount.get(sub)) {
                    wordsUsed++;
                } else {
                    // Found too many instances already
                    excessWord = true;
                }

                if (wordsUsed == k && !excessWord) {
                    // Found a valid substring
                    answer.add(left);
                }
            }
        }
    }

    public List<Integer> findSubstring_2(String s, String[] words) {
        n = s.length();
        k = words.length;
        wordLength = words[0].length();
        substringSize = wordLength * k;

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < wordLength; i++) {
            slidingWindow(i, s, answer);
        }

        return answer;
    }
    /*
        Time complexity: O(a + n.b)
        Space Complexity: O(a + b)
     */

}

