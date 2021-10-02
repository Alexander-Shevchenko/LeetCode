import java.util.*;

// https://leetcode.com/problems/group-anagrams/
// 49. Group Anagrams
// Given an array of strings strs, group the anagrams together. You can return the answer in any order.

// Try 3 - avoid sorting (still worse: 10 ms, faster than 56.90% of Java online submissions)

public class Problem49c {
    // Compares and Hashes strings per anagram equality
    static class AnaString {
        final static int maxIntByte = 31;
        final private String str;
        private int[] charCnt = null;
        private int maxCharIdx = -1;
        private int svHash = 0;
        private boolean bHash = false;

        AnaString(String s) {
            str = s;
        }

        public int[] getCharCnt() {
            if (charCnt == null) {
                charCnt = new int['z' - 'a' + 1];
                for (int i = 0; i < str.length(); ++i) {
                    ++charCnt[str.charAt(i) - 'a'];
                    if (maxCharIdx < str.charAt(i) - 'a')
                        maxCharIdx = str.charAt(i) - 'a';
                }
            }
            return charCnt;
        }

        public int getMaxCharIdx() {
            if (maxCharIdx == -1) {
                getCharCnt();
            }
            return maxCharIdx;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof AnaString))
                return false;

            AnaString other = (AnaString)o;

            if (other.str.length() != this.str.length())
                return false;
            
            if (other.hashCode() != this.hashCode())
                return false;

            if (this.getMaxCharIdx() != other.getMaxCharIdx())
                return false;

            int[] charsThis  = this.getCharCnt();
            int[] charsOther = other.getCharCnt();
            for (int i = 0; i < maxCharIdx; ++i) {
                if (charsThis[i] != charsOther[i])
                    return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            if (bHash)
                return svHash;

            bHash = true;
            int byteToSet;

            for (int i = 0; i < str.length(); ++i) {
                byteToSet = maxIntByte - (str.charAt(i) - 'a');
                byteToSet = 1 << byteToSet;

                if ((svHash & byteToSet) == 0) {
                    svHash |= byteToSet;
                } else { // Letter repetition, just add its code to make hash more unique
                    svHash += str.charAt(i);
                }
            }
            return svHash;
        }
    }

    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            // Group anagrams into HashMap
            Map<AnaString, List<String>> groups = new HashMap<>();
            for (String str : strs) {
                AnaString anaStr = new AnaString(str);
                List<String> anagrams = groups.get(anaStr);
                if (anagrams == null) {
                    anagrams = new ArrayList<>(1);
                    anagrams.add(str);
                    groups.put(anaStr, anagrams);
                } else {
                    anagrams.add(str);
                }
            }

            // Form groups
            return new ArrayList<>(groups.values());
        }
    }

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> ret = new Solution().groupAnagrams(strs);
        System.out.println(ret);
    }
}
