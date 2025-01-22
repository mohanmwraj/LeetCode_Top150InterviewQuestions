package hashMap;

import java.util.HashSet;
import java.util.Set;

/*
    https://leetcode.com/problems/contains-duplicate-ii/description/

    Given an integer array nums and an integer k, return true
    if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

 */
public class containsDuplicateII {
    /*
        * Approach: Naive Linear Search
        *
     */
    public boolean containsNearbyDuplicate_1(int[] nums, int k) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = Math.max(i - k, 0); j < i; ++j) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    /*
        Time complexity : O(n * min(k,n))
        Space complexity : O(1)
     */

    /*
        * Approach: Hash Table
        *
        * Keep a sliding window of k elements using Hash Table.
     */
    public boolean containsNearbyDuplicate_2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < nums.length; ++i){
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);

            if(set.size() > k){
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

}
