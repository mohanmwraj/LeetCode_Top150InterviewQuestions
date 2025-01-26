package bitManipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
    https://leetcode.com/problems/single-number-ii/description/

    every element appears three times except for one, which appears exactly once.
 */
public class singleNumberII {
    /*
        * Approach: Sorting
        *
        * After sorting, we can check every integer with its next integer starting from the zeroth index.
        *
        * If they are the same, we can conclude that the integer is not the loner.
        * We will jump three indices ahead. This is because we are given that if an integer is not the loner,
        * it appears exactly three times.
        * So, we can skip the next two indices.
        * Otherwise, we can conclude that the integer is the loner. We will return it.
     */
    public int singleNumber_1(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i += 3) {
            if (nums[i] == nums[i + 1]) {
                continue;
            } else {
                return nums[i];
            }
        }

        return nums[nums.length - 1];
    }
    /*
        Time Complexity: O(N log N)
        Space Complexity: O(N)
     */

    /*
        * Approach: Hash Map
        *
        * Count the frequencies of an elements, if it is 1, return it.
        *
     */
    public int singleNumber_2(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            if (!freq.containsKey(num)) {
                freq.put(num, 1);
            } else {
                freq.put(num, freq.get(num) + 1);
            }
        }

        int loner = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 1) {
                loner = entry.getKey();
                break;
            }
        }

        return loner;
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(N)
     */

    /*
        * Approach: Bit Manipulation
        *
        * Count freq of each bit in each num.
        * bitSum % 3 will give loner bit.
        *
     */

    public int singleNumber_3(int[] nums){
        int loner = 0;

        for(int shift = 0; shift < 32; shift++){
            int bitSum = 0;

            for(int num: nums){
                bitSum += (num >> shift) & 1;
            }

            int lonerBit = bitSum % 3;
            loner |= (lonerBit << shift);
        }
        return loner;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
