package bitManipulation;

import java.util.*;

/*
    https://leetcode.com/problems/single-number/description/
 */
public class singleNumber {
    /*
        * Approach: List Operation
        *
        * If the number is not present in the list, add it.
        * else remove from the list.
        *
        * At end, list will have one non-duplicate element.
        *
     */
    public int singleNumber_1(int[] nums) {
        List<Integer> no_duplicate_list = new ArrayList<>();

        for (int i : nums) {
            if (!no_duplicate_list.contains(i)) {
                no_duplicate_list.add(i);
            } else {
                no_duplicate_list.remove(i);
            }
        }
        return no_duplicate_list.get(0);
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(n)
     */

    /*
        * Approach: Hash Table
        *
        * Count the frequency of element.
        * If the element frequency is 1, return it.
        *
     */
    public int singleNumber_2(int[] nums) {
        HashMap<Integer, Integer> hash_table = new HashMap<>();

        for (int i : nums) {
            hash_table.put(i, hash_table.getOrDefault(i, 0) + 1);
        }
        for (int i : nums) {
            if (hash_table.get(i) == 1) {
                return i;
            }
        }
        return 0;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Math
        *
        * 2∗(a+b+c)−(a+a+b+b+c)=c
        *
     */
    public int singleNumber_3(int[] nums) {
        int sumOfSet = 0, sumOfNums = 0;
        Set<Integer> set = new HashSet();

        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
                sumOfSet += num;
            }
            sumOfNums += num;
        }
        return 2 * sumOfSet - sumOfNums;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Bit Manipulation
        *
        * XOR of same numbers will become zero.
        *
     */
    public int singleNumber_4(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        return a;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
