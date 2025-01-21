package TwoPointers;

/*
    https://leetcode.com/problems/valid-palindrome/description/
 */
public class validPalindrome {
    /*
     * Approach: Compare With Reverse
     *
     * reverse the given string and compare it with the original. If those are equivalent, it's a palindrome.
     *
     */
    public boolean isPalindrome_1(String s) {
        StringBuilder builder = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                builder.append(Character.toLowerCase(ch));
            }
        }

        String filteredString = builder.toString();
        String reversedString = builder.reverse().toString();

        return filteredString.equals(reversedString);
    }

    /**
     * An alternate solution using Java 8 Streams
     */
    public boolean isPalindromeUsingStreams(String s) {
        StringBuilder builder = new StringBuilder();

        s
                .chars()
                .filter(Character::isLetterOrDigit)
                .mapToObj(c -> Character.toLowerCase((char) c))
                .forEach(builder::append);

        return builder.toString().contentEquals(builder.reverse());
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(N)
     */

    /*
     * Approach: Two Pointers
     *
     */
    public boolean isPalindrome_2(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;

            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(1)
     */
}