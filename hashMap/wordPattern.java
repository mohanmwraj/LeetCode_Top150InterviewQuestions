package hashMap;

import java.util.HashMap;

public class wordPattern {
    /*
        * Approach: Hash Map
        *
        * two hash maps, one for mapping characters to words and the other for mapping words to characters.
        *
        * As you scan each character-word pair, update this hash map for characters which are not in the mapping.
        * If you see a character which already is one of the keys in mapping, check whether the current word matches with the word the character maps to.
        * If they do not match, you can immediately return False, otherwise, just keep on scanning until the end.
        *
     */
    public boolean wordPattern_1(String pattern, String s) {
        HashMap <Character, String> map_char = new HashMap();
        HashMap <String, Character> map_word = new HashMap();
        String[] words = s.split(" ");

        if (words.length != pattern.length())
            return false;

        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String w = words[i];
            if (!map_char.containsKey(c)) {
                if (map_word.containsKey(w)) {
                    return false;
                } else {
                    map_char.put(c, w);
                    map_word.put(w, c);
                }

            } else {
                String mapped_word = map_char.get(c);
                if (!mapped_word.equals(w))
                    return false;
            }
        }

        return true;
    }
    /*
        Time Complexity: O(N + M)
        Space Complexity: O(N)
     */

    /*
        * Approach: Single Index Hash Map
        *
        * Rather than having two hash maps,
        * we can have a single index hash map which keeps track of the first occurrences of each character in pattern and each word in s.
        * The goal is to make sure that the indices of each character and word match up.
        * As soon as we find a mismatch, we can return False.
        *
     */
    public boolean wordPattern_2(String pattern, String s) {
        HashMap mapIndex = new HashMap<>();
        String[] words = s.split(" ");

        if(words.length != pattern.length()){
            return false;
        }

        for(Integer i = 0; i < words.length; ++i){
            char c = pattern.charAt(i);
            String w = words[i];

            if(!mapIndex.containsKey(c)){
                mapIndex.put(c, i);
            }

            if(!mapIndex.containsKey(w)){
                mapIndex.put(w, i);
            }

            if(mapIndex.get(c) != mapIndex.get(w)){
                return false;
            }
        }

        return true;
    }
}
