package hashMap;

/*
    https://leetcode.com/problems/two-sum/description/
 */

import java.util.HashMap;
import java.util.Map;

public class twoSum {
    /*
        * Approach: Brute Force
        *
        * Loop through each element x and find if there is another value that equals to target−x.
        *
     */
    public int[] twoSum_1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] {};
    }
    /*
        Time Complexity: O(N^2)
        Space Complexity: O(1)
     */

    /*
        * Approach: Two Pass Hash Table
        *
        * We can reduce the lookup time from O(n) to O(1) by trading space for speed.
        * A hash table is well suited for this purpose because it supports fast lookup in near constant time.
        * However, lookup in a hash table should be amortized O(1) time as long as the hash function was chosen carefully.
        *
        *
     */

    public int[] twoSum_2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        return new int[] {};
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: One-Pass Hash Table
        *
        *
     */

    public int[] twoSum_3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; ++i){
            int complement = target - nums[i];

            if(map.containsKey(complement)){
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }

        return new int[]{};
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
