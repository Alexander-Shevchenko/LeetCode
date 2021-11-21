// https://leetcode.com/problems/substring-with-concatenation-of-all-words/

package problem30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Solution {
    // words and their indices (as they can repeat)
    HashMap<String, ArrayList<Integer>> wordToIdx = new HashMap<>();

    private void initWordIndices(String[] words) {
        int i = 0;
        for (String word : words) {
            ArrayList<Integer> indices = wordToIdx.computeIfAbsent(word, k -> new ArrayList<>());
            indices.add(i++);
        }
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new ArrayList<>();
        
        if (words.length == 0)
            return ret;

        int wordLen = words[0].length();
        int allWordsLen = wordLen * words.length;
        
        initWordIndices(words);

        for (int i = 0; i <= s.length() - allWordsLen; ++i) {
            String startStr = s.substring(i, i + wordLen);
            ArrayList<Integer> indices = wordToIdx.get(startStr);
            if (indices == null) {
                continue;
            }

            // Shortcut for 1 char len words
            if (words.length == 1) {
                ret.add(i);
                continue;
            }

            // OK, we've found a potential start of occurrence
            HashSet<Integer> usedWordIndices = new HashSet<>();
            usedWordIndices.add(indices.get(0));
            for (int j = i + wordLen; j <= s.length() - wordLen; j += wordLen) {
                String startStr2 = s.substring(j, j + wordLen);
                ArrayList<Integer> indices2 = wordToIdx.get(startStr2);
                if (indices2 == null) {
                    break; // not used all words, no luck this time
                }

                boolean bFound = false;
                for (Integer wIdx : indices2) {
                    if (!usedWordIndices.contains(wIdx)) {
                        // We've found a fitting word which has not been used yet
                        usedWordIndices.add(wIdx);
                        bFound = true;
                        break;
                    }
                }

                if (!bFound)
                    break;

                // All words used
                if (usedWordIndices.size() == words.length) {
                    ret.add(i);
                    break;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        String [] words = new String[2];
        words[0] = "aa";
        words[1] = "aa";

        List<Integer> ret = new Solution().findSubstring("aaaaa", words);
        System.out.println("ret = " + ret.toString());
    }
}
