package hashMap;

import java.util.*;

/*
    https://leetcode.com/problems/longest-consecutive-sequence/description/
 */

public class longestConsecutiveSequence {
    /*
        * Approach: Brute Force
        *
        * For every num, check num + 1 is in the array, if it is
        * increment the current streak and current number.
     */

    private boolean arrayContains(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }

        return false;
    }

    public int longestConsecutive_1(int[] nums) {
        int longestStreak = 0;

        for (int num : nums) {
            int currentNum = num;
            int currentStreak = 1;

            while (arrayContains(nums, currentNum + 1)) {
                currentNum += 1;
                currentStreak += 1;
            }

            longestStreak = Math.max(longestStreak, currentStreak);
        }

        return longestStreak;
    }
    /*
        Time Complexity: O(n^3)
        Space Complexity: O(1)
     */

    /*
        * Approach: Sorting
        *
        * If we can iterate over the numbers in ascending order,
        * then it will be easy to find sequences of consecutive numbers.
        * To do so, we can sort the array.
     */
    public int longestConsecutive_2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == nums[i - 1] + 1) {
                    currentStreak += 1;
                } else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, currentStreak);
    }
    /*
        Time Complexity: O(n * log n)
        Space Complexity: O(log n) or O(n)
     */

    /*
        * Approach: Hashset and Intelligent Sequence Building
        *
        * the numbers are stored in a HashSet to allow O(1) lookups.
        * we only attempt to build sequences from numbers that are not already part of a longer sequence.
        * This is accomplished by first ensuring that the number that
        * would immediately precede the current number in
        * a sequence is not present, as that number would necessarily be part of a longer sequence.
        *
     */
    public int longestConsecutive_3(int[] nums) {

        Set<Integer> numSet = new HashSet<>();
        for(int num: nums){
            numSet.add(num);
        }

        int longestStreak = 0;
        for(int num: numSet){
            if(!numSet.contains(num - 1)){
                int currentNum = num;
                int currentStreak = 1;

                while(numSet.contains(currentNum + 1)){
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(currentStreak, longestStreak);
            }
        }

        return longestStreak;
    }
        /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
