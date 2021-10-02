import java.util.*;

// https://leetcode.com/problems/group-anagrams/
// 49. Group Anagrams
// Given an array of strings strs, group the anagrams together. You can return the answer in any order.

// Try 2 with custom HashCode method, not better than String's :( (8 ms, faster than 61.36%)

public class Problem49b {
    // Compares and Hashes strings per anagram equality
    static class AnaString {
        final static int maxIntByte = 31;
        final private char[] strChars;
        private int svHash = 0;
        private boolean bHash = false;

        AnaString(String s) {
            strChars = s.toCharArray();
            Arrays.sort(strChars);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof AnaString))
                return false;

            AnaString other = (AnaString)o;

            if (other.strChars.length != this.strChars.length)
                return false;

            return Arrays.equals(other.strChars, this.strChars);
        }

        @Override
        public int hashCode() {
            if (bHash)
                return svHash;

            bHash = true;
            int byteToSet;

            for (char strChar : strChars) {
                byteToSet = maxIntByte - (strChar - 'a');
                byteToSet = 1 << byteToSet;

                if ((svHash & byteToSet) == 0) {
                    svHash |= byteToSet;
                } else { // Letter repetition, just add its code to make hash more unique
                    svHash += strChar;
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
