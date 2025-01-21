package TwoPointers;

import java.util.*;

/*
    https://leetcode.com/problems/3sum/
 */
public class _3Sum {
    /*
        * Approach: Two Pointers
        *
        * Sort the input array nums.
        * Iterate through the array:

            * If the current value is greater than zero, break from the loop.
            * Remaining values cannot sum to zero.
            * If the current value is the same as the one before, skip it.
            * Otherwise, call twoSumII for the current position i.
         * Call two sum II
         *
     */

    public List<List<Integer>> threeSum_1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i) if (
                i == 0 || nums[i - 1] != nums[i]
        ) {
            twoSumII(nums, i, res);
        }
        return res;
    }

    void twoSumII(int[] nums, int i, List<List<Integer>> res) {
        int lo = i + 1, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[i] + nums[lo] + nums[hi];
            if (sum < 0) {
                ++lo;
            } else if (sum > 0) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                while (lo < hi && nums[lo] == nums[lo - 1]) ++lo;
            }
        }
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(log N) or O(n), depending on the implementation of the soring algorithm.
     */

    /*
        * Approach: Hashset
        *
        * Use two sum approach, to find the negative target.
     */
    public List<List<Integer>> threeSum_2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i) if (
                i == 0 || nums[i - 1] != nums[i]
        ) {
            twoSum(nums, i, res);
        }
        return res;
    }

    void twoSum(int[] nums, int i, List<List<Integer>> res) {
        var seen = new HashSet<Integer>();
        for (int j = i + 1; j < nums.length; ++j) {
            int complement = -nums[i] - nums[j];
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                while (j + 1 < nums.length && nums[j] == nums[j + 1]) ++j;
            }
            seen.add(nums[j]);
        }
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(n).
     */

    /*
        * Approach: No Sort
        *
        * We can adapt the hashset approach above to work for an unsorted array.
        * We can put a combination of three values into a hashset to avoid duplicates.
        * Values in a combination should be ordered (e.g. ascending).
        * Otherwise, we can have results with the same values in the different positions.
     */
    public List<List<Integer>> threeSum_3(int[] nums){
        Set<List<Integer>> result = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();

        for(int i = 0; i < nums.length; ++i){
            if(dups.add(nums[i])){
                for(int j = i + 1; j < nums.length; ++j){
                    int complement = -nums[i] - nums[j];
                    if(seen.containsKey(complement) && seen.get(complement) == i){
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        result.add(triplet);
                    }
                    seen.put(nums[j], i);
                }
            }
        }

        return new ArrayList<>(result);
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(n).
     */
}
