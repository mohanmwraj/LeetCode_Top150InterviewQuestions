package stack;

import java.util.HashMap;
import java.util.Stack;

/*
    https://leetcode.com/problems/valid-parentheses/description/
 */
public class validParantheses {
    /*
        * Approach: Stacks
        *
     */
    public boolean isValid(String s) {
        HashMap<Character, Character> mappings = new HashMap<>();
        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');

        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);

            if(mappings.containsKey(c)){
                char topElement = stack.empty() ? '#' : stack.pop();

                if(topElement != mappings.get(c)){
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
