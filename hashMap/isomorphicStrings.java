package hashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
    https://leetcode.com/problems/isomorphic-strings/description/
 */
public class isomorphicStrings {
    /*
        * Approach: Character Mapping with Dictionary
        *
        * We will process both of the strings from left to right.
        * At each step, we take one character at a time from the two strings and compare them.
        * There are three cases we need to handle here:
        *   1. If the characters don't have a mapping, we add one in the dictionary and move on.
        *   2. The characters already have a mapping in the dictionary. If that is the case, then we're good to go.
        *   3. The final case is when a mapping already exists for one of the characters but it doesn't map to the other character at hand.
        *      In this case, we can safely conclude that the given strings are not isomorphic and we can return.
     */
    public boolean isIsomorphic_1(String s, String t) {
        int[] mappingDictStoT = new int[256];
        Arrays.fill(mappingDictStoT, -1);

        int[] mappingDictTtoS = new int[256];
        Arrays.fill(mappingDictTtoS, -1);

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // Case 1: No mapping exists in either of the dictionaries
            if (mappingDictStoT[c1] == -1 && mappingDictTtoS[c2] == -1) {
                mappingDictStoT[c1] = c2;
                mappingDictTtoS[c2] = c1;
            }
            // Case 2: Either mapping doesn't exist in one of the dictionaries or Mapping exists and
            // it doesn't match in either of the dictionaries or both
            else if (
                    !(mappingDictStoT[c1] == c2 && mappingDictTtoS[c2] == c1)
            ) {
                return false;
            }
        }

        return true;
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */

    /*
        * Approach: First Occurrence Transformation
        *
        * If we have two isomorphic strings,
        * we can replace the characters in the first string with the corresponding mapped characters to get the second string.
        *
        * Is there any string transformation we can apply to both the strings such that to check for isomorphism,
        * we simply check if their modified versions are exactly the same?
        *
        * For each character in the given string, we replace it with the index of that character's first occurrence in the string.
        *
        * However, we should be mindful of transformations that use both one and two-digit numbers.
        * Under these circumstances, the transformed strings can be misinterpreted.
        *
        * we can add a delimiter to help differentiate the transformed digits.
        *
     */
    public boolean isIsomorphic_2(String s, String t) {
        return transformString(s).equals(transformString(t));
    }

    private String transformString(String s){
        Map<Character, Integer> indexMapping = new HashMap<>();
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < s.length(); ++i){
            char c1 = s.charAt(i);
            if(!indexMapping.containsKey(c1)){
                indexMapping.put(c1, i);
            }

            builder.append(Integer.toString(indexMapping.get(c1)));
            builder.append(" ");
        }

        return builder.toString();
    }
    /*
        Time Complexity: O(N)
        Space Complexity: O(N)
     */
}
