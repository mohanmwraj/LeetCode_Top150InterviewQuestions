package _Arrays;

/*
    https://leetcode.com/problems/longest-common-prefix/description/

 */

public class longestCommonPrefix {
    /*
        * Approach: Horizontal Scanning
        *
        * LCP(S1…Sn)=LCP(LCP(LCP(S1,S2),S3),…Sn)
     */

    public static String longestCommonPrefix_1(String[] strs){
        if(strs.length == 0) return "";
        String prefix = strs[0];

        for(int i = 1; i < strs.length; ++i){
            System.out.println("Prefix: "+prefix);
            System.out.println("String: "+strs[i]);

            while(strs[i].indexOf(prefix) != 0){
                //When the whole string matches it returns the index value as 0.
                System.out.println("String index: "+strs[i].indexOf(prefix));
                System.out.println("Inside While: "+prefix);
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix_1(strs));
    }

    /*
        Time Complexity: O(S), where S is the sum of all characters in all string.
        Space Complexity: O(1)
     */

    /*
        * Approach: Vertical Scanning
        *
        * We compare characters from top to bottom on the same column
        * (same character index of the strings) before moving on to the next column.
     */

    public String longestCommonPrefix_2(String[] str){
        if(str == null || str.length == 0) return "";

        for(int i = 0; i < str[0].length(); ++i){
            char c = str[0].charAt(i);
            for(int j = 1; j < str.length; ++j){
                if(i == str[j].length() || str[j].charAt(i) != c){
                    return str[0].substring(0, i);
                }
            }
        }
        return str[0];
    }
    /*
        Time Complexity: O(S), where S is the sum of all characters in all string.
        Space Complexity: O(1)
     */
}
