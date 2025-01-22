package hashMap;

import java.util.*;

/*
    https://leetcode.com/problems/group-anagrams/

 */
public class groupAnagrams {
    /*
        * Approach: Categorize by Sorted String
        *
        * Two strings are anagrams if and only if their sorted strings are equal.
     */
    public static List<List<String>> groupAnagrams_1(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            System.out.println("Key: "+key);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
    /*
        Time Complexity: O(N * K * log K)
        Space Complexity: O(N * K)
     */

    /*
        * Approach: Categorize by Count
        *
        * Two strings are anagrams if
        * and only if their character counts (respective number of occurrences of each character) are the same.
        *
        * We can transform each string s into a character count, count,
        * consisting of 26 non-negative integers representing the number of a's, b's, c's, etc.
        *
        * We use these counts as the basis for our hash map.
        * In Java, the hashable representation of our count will be a string delimited with '#' characters.
     */
    public List<List<String>> groupAnagrams_2(String[] strs) {
        if(strs.length == 0){
            return new ArrayList<>();
        }

        Map<String, List> answer = new HashMap<>();
        int[] count = new int[26];

        for(String str: strs){
            Arrays.fill(count, 0);

            for(char c: str.toCharArray()){
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 26; ++i){
                sb.append('#');
                sb.append(count[i]);
            }

            String key = sb.toString();
            if(!answer.containsKey(key)){
                answer.put(key, new ArrayList<>());
            }
            answer.get(key).add(str);
        }

        return new ArrayList(answer.values());
    }

    /*
        Time Complexity: O(N * K)
        Space Complexity: O(N * K)
     */


    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> result = groupAnagrams_1(strs);
    }
}
