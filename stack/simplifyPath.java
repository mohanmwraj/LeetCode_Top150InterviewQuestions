package stack;

import java.util.Stack;

/*
    https://leetcode.com/problems/simplify-path/description/
 */
public class simplifyPath {
    /*
        * Approach: Stacks
        *
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] components = path.split("/");

        for(String directory: components){
            if(directory.equals(".") || directory.isEmpty()){
                continue;
            } else if(directory.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            } else {
                stack.add(directory);
            }
        }

        StringBuilder result = new StringBuilder();
        for(String dir: stack){
            result.append("/");
            result.append(dir);
        }

        return result.length() > 0 ? result.toString() : "/";
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
