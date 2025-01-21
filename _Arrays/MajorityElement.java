package _Arrays;

/*
    https://leetcode.com/problems/majority-element/description/
 */


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    /*
        * Approach: Brute Force
        *
        * The brute force algorithm iterates over the array, and then iterates again for each number to count its occurrences.
        * As soon as a number is found to have appeared more than any other can possibly have appeared, return it.
        *
     */
    public int majorityElement_1(int[] nums) {
        int majorityCount = nums.length / 2;
        for(int num: nums){
            int count = 0;
            for(int ele: nums){
                if(ele == num){
                    count += 1;
                }
            }

            if(count >= majorityCount) return num;
        }
        return  -1;
    }

    /*
        Time complexity : O(n^2)
        Space complexity : O(1)
     */

    /*
        * Approach: HashMap
        *
        * Create frequency map.
        * If the frequency of the element are greater than the nums.length / 2 then return.
     */
    public int majorityElement_2(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);
        Map.Entry<Integer, Integer> majorityEntry = null;

        for(Map.Entry<Integer, Integer> entry: counts.entrySet()){
            if(entry.getValue() > nums.length / 2){
                return entry.getKey();
            }
        }

        return majorityEntry.getKey();
    }

    private Map<Integer, Integer> countNums(int[] nums){
        Map<Integer, Integer> answer = new HashMap<>();

        for(int num: nums){
            answer.put(num, answer.getOrDefault(num, 0) + 1);
        }

        return answer;
    }

    /*
        Time complexity : O(n)
        Space complexity : O(n)
     */

    /*
        * Approach: Sorting
        *
        * Sort the array, return the arr[array.length/2].
     */
    public int majorityElement_3(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /*
        Time complexity : O(nlgn)
        Space complexity : O(1)
     */

    /*
        * Approach: Bit Manipulation
        *
        *
        * If an element majority_element occurs more than [n / 2] times, then there are at least [n / 2] elements of identical values with num at each bit.
        * That is, we can reconstruct the exact value of num by combining the most frequent value (0 or 1) at each bit.
        * Starting from the least significant bit, we enumerate each bit to determine which value is the majority at this bit, 0 or 1, and put this value to the corresponding bit of the result.
        * Finally, we end up with the least significant bit of all elements and return the result.
        * Because all numbers are in the range [−10^9,10^9], which can be represented in 32-bit, we only need to enumerate 32 bits.
     */
    public int majorityElement_4(int[] nums) {
        int n = nums.length;
        int majorityElement = 0;

        for(int i = 0; i < 32; ++i){
            int bit = 1 << i;
            int bitCount = 0;

            for(int num: nums){
                if((num & bit) != 0){
                    bitCount++;
                }
            }

            // If this bit is present in more than n / 2 elements
            // then it must be set in the majority element.
            if(bitCount > n / 2){
                majorityElement |= bit;
            }
        }

        return majorityElement;
    }
    /*
        Time complexity : O(nlogC)
        C is the max absolute value in nums, i.e., 105 in this problem. We enumerate all logC bits for each number in nums.

        Space complexity: O(1)
     */

    /*
        * Approach: Boyer-Moore Voting Algorithm
        *
        *
        * counting instances of the majority element as +1 and instances of any other element as −1,
        * summing them would make it obvious that the majority element is indeed the majority element.
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for(int num: nums){
            if(count == 0){
                candidate = num;
            }
            count += (candidate == num) ? 1 : -1;
        }

        return candidate;
    }

    /*
        Time complexity : O(n)
        Space complexity : O(1)
     */
}
