package _Arrays;

/*
    https://leetcode.com/problems/gas-station/description/
 */


public class gasStation {

    /*
        * Approach: One Pass
        *
        * An intuitive idea is to skip this "valley" and try completing the circular route starting from the next gas station with a non-negative net gain of gas.
        * Consequently, we treat the previous part as a single segment, which we failed to pass through with 0 initial gas.
        * Let's temporarily leave it behind and start over from the next station with 0 initial gas.
        *
        * During the following journey, we keep track of the current accumulated gas at each station.
        * If we have a negative gas value after passing gas station i, we treat the stations that have been passed as a separate segment and start over from the next station i + 1.
        * This means that we will test if station i + 1 is a valid starting station answer.
        *
        * By visualizing this process, we can see that it involves dividing our journey into segments, each of which ends in a "valley" station where the accumulated gas becomes negative.
        * These segments cannot be crossed with 0 initial gas, and any station within them is not a valid starting station.
        *
        * To pass these segments, we must hold enough extra gas before entering them.
        * When we complete the entire trip, there may still be some gas stations that do not belong to any segment.
        * Since we visit the gas stations in a circular manner, these ending stations are actually the starting part of the first segment.
        *
        *
        * We have verified that none of the segments is crossable.
        * However, with the addition of these stations by the end of the journey, the situation with segment 1 may change, and we have to try again.
     *
     */

    public int canCompleteCircuit(int[] gas, int[] cost){
        int totalGain = 0;
        int currentGain = 0;
        int answer = 0;

        for(int i = 0; i < gas.length; ++i){
            totalGain += gas[i] - cost[i];
            currentGain += gas[i] - cost[i];

            if(currentGain < 0){
                currentGain = 0;
                answer = i + 1;
            }
        }

        return totalGain >= 0 ? answer : -1;
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(1)
     */
}
