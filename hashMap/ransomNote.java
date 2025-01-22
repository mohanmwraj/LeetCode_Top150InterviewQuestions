package hashMap;
/*
    https://leetcode.com/problems/ransom-note/description/
 */
import java.util.HashMap;
import java.util.Map;

public class ransomNote {
    /*
     * Approach: Simulation
     *
     * To create our ransom note, for every character we have in the note,
     * we need to take a copy of that character out of the magazine so that it can go into the note.
     * If a character we need isn't in the magazine, then we should stop and return False.
     * Otherwise, if we manage to get all the characters we need to complete the note, then we should return True.
     *
     */
    public boolean canConstruct_1(String ransomNote, String magazine) {

        for (char c : ransomNote.toCharArray()) {
            int index = magazine.indexOf(c);
            if (index == -1) {
                return false;
            }

            magazine = magazine.substring(0, index) + magazine.substring(index + 1);
        }
        return true;
    }

    /*
        Time Complexity: O(m * n)
        Space Complexity: O(m)
     */

    /*
     * Approach: Hash Maps
     *
     * Store the characters frequency of both ransom note and magazine into the hashmap.
     * Start iterating the ransom note map,
     * check if the frequency of character in the magazine map is less than the frequency in ransom note map,
     * return false, else return true.
     */

    private Map<Character, Integer> makeCountsMap(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            int currentCount = counts.getOrDefault(c, 0);
            counts.put(c, currentCount + 1);
        }
        return counts;
    }

    public boolean canConstruct_2(String ransomNote, String magazine) {

        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        Map<Character, Integer> ransomNoteCounts = makeCountsMap(ransomNote);
        Map<Character, Integer> magazineCounts = makeCountsMap(magazine);

        for (char c : ransomNoteCounts.keySet()) {
            int countInMagazine = magazineCounts.getOrDefault(c, 0);
            int countInRansomNote = ransomNoteCounts.get(c);
            if (countInMagazine < countInRansomNote) {
                return false;
            }
        }

        return true;
    }
    /*
        Time Complexity: O(m)
        Space Complexity: O(k)
     */

    /*
     * Approach: One Hash Map
     *
     * get the current character of ransom note in the magazine map.
     * if it is 0, return false.
     * subtract the frequency of every character of ransom note in magazine map.
     *
     */
    public boolean canConstruct_3(String ransomNote, String magazine) {

        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        Map<Character, Integer> magazineCounts = makeCountsMap(magazine);

        for (char c : ransomNote.toCharArray()) {
            int countInMagazine = magazineCounts.getOrDefault(c, 0);
            // If there are none of c left, return false.
            if (countInMagazine == 0) {
                return false;
            }
            magazineCounts.put(c, countInMagazine - 1);
        }

        return true;
    }
}
