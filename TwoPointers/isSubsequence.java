package TwoPointers;
/*
    https://leetcode.com/problems/is-subsequence/description/
 */

public class isSubsequence {
    /*
        * Approach: Divide and Conquer with Greedy
        * isSubsequence(source, target)={
        *                           isSubsequence(source[1:], target[1:])
        *                                   isSubsequence(source, target[1:])
        *
     */

    String source, target;
    Integer leftBound, rightBound;

    private boolean rec_isSubsequence(int leftIndex, int rightIndex) {
        // base cases
        if (leftIndex == leftBound)
            return true;
        if (rightIndex == rightBound)
            return false;

        // consume both strings or just the target string
        if (source.charAt(leftIndex) == target.charAt(rightIndex))
            ++leftIndex;
        ++rightIndex;

        return rec_isSubsequence(leftIndex, rightIndex);
    }

    public boolean isSubsequence_1(String s, String t) {
        this.source = s;
        this.target = t;
        this.leftBound = s.length();
        this.rightBound = t.length();

        return rec_isSubsequence(0, 0);
    }
    /*
        Time Complexity: O(∣T∣)
        Space Complexity: O(∣T∣)
     */

    /*
        * Approach: Two Pointers
     */

    public boolean isSubsequence_2(String s, String t) {
        int leftBound = s.length();
        int rightBound = t.length();
        Integer pLeft = 0, pRight = 0;

        while(pLeft < leftBound && pRight < rightBound){
            if(s.charAt(pLeft) == t.charAt(pRight)) pLeft++;
            pRight++;
        }

        return pLeft == leftBound;
    }
    /*
        Time Complexity: O(∣T∣)
        Space Complexity: O(1)
     */
}
