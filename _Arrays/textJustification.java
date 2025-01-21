package _Arrays;

/*
    https://leetcode.com/problems/text-justification/description/

 */

import java.util.ArrayList;
import java.util.List;

public class textJustification {
    /*
        * Approach:
        *
        * Determine which words should be on the line.
        * Take the words from the first task and create a line.
        *
        * the length of this subarray should be maximized without the length of the line exceeding maxWidth.
        * On a line, every word except the final one must be followed by a space.
        * Therefore, each word contributes word.length + 1 to the line's length, except for the final word.
        * We want to add as many words as possible without exceeding maxWidth.
        *
        *
        *
        *
        *
        *
     */

    public List<String> fullJustify(String[] words, int maxWidth){
        List<String> answer = new ArrayList<>();
        int i = 0;

        while(i < words.length){
            List<String> currentLine = getWords(i, words, maxWidth);
            i += currentLine.size();
            answer.add(createLine(currentLine, i, words, maxWidth));
        }

        return answer;
    }

    private List<String> getWords(int i, String[] words, int maxWidth){
        List<String> currentLine = new ArrayList<>();
        int currLength = 0;

        while(i < words.length && currLength + words[i].length() <= maxWidth){
            currentLine.add(words[i]);
            currLength += words[i].length() + 1;
            i++;
        }

        return currentLine;
    }

    private String createLine(List<String> line, int i, String[] words, int maxWidth){
        int baseLength = -1;
        for(String word: line){
            baseLength += word.length() + 1;
        }

        int extraSpaces = maxWidth - baseLength;
        if(line.size() == 1 || i == words.length){
            return String.join(" ", line) + " ".repeat(extraSpaces);
        }

        if(line.size() == 1 || i == words.length){
            return String.join(" ", line) + " ".repeat(extraSpaces);
        }

        int wordCount = line.size() - 1;
        int spacePerWord = extraSpaces / wordCount;
        int needsExtraSpace = extraSpaces % wordCount;

        for(int j = 0; j < needsExtraSpace; ++j){
            line.set(j, line.get(j) + " ");
        }

        for(int j = 0; j < wordCount; ++j){
            line.set(j, line.get(j) + " ".repeat(spacePerWord));
        }

        return String.join(" ", line);
    }

    /*
        Time complexity: O(n⋅k)
        Space Complexity: O(m)
     */
}
