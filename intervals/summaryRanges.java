package intervals;
/*
    https://leetcode.com/problems/summary-ranges/description/
 */

import java.util.ArrayList;
import java.util.List;

public class summaryRanges {
    /*
        * Approach: Fixed Left Bound
        *
     */

    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<>();

        for(int i = 0; i < nums.length; ++i){
            int start = nums[i];
            while(i + 1 < nums.length && nums[i] + 1 == nums[i + 1]){
                i++;
            }

            if(start != nums[i]){
                ranges.add(start + "->" + nums[i]);
            } else {
                ranges.add(String.valueOf(start));
            }
        }

        return ranges;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
