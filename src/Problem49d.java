import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/group-anagrams/
// 49. Group Anagrams
// Given an array of strings strs, group the anagrams together. You can return the answer in any order.

// Try 4 - use double sort, still not much better: 11 ms, faster than 53.53% of Java online submissions
public class Problem49d {
    static class Solution {
        private class StrAndIndex implements Comparable {
            public final String s;
            public final int i;

            StrAndIndex(String s, int i) {
                this.s = s;
                this.i = i;
            }

            @Override
            public int compareTo(Object o) {
                return s.compareTo(((StrAndIndex)o).s);
            }

            @Override
            public boolean equals(Object o) {
                return compareTo(o) == 0;
            }
        }

        public List<List<String>> groupAnagrams(String[] strs) {

            StrAndIndex[] strsSorted = new StrAndIndex[strs.length];
            // Sort all strings, so anagrams match
            for (int i = 0; i < strs.length; ++i) {
                char[] charArray = strs[i].toCharArray();
                Arrays.sort(charArray);
                strsSorted[i] = new StrAndIndex(new String(charArray), i);
            }

            Arrays.sort(strsSorted);

            List<List<String>> ret = new ArrayList<>();

            // Form groups
            List<String> group = null;
            for (int i = 0; i < strsSorted.length; ++i) {
                if (group == null) {
                    group = new ArrayList<>();
                } else if (!strsSorted[i].equals(strsSorted[i - 1])) {
                    ret.add(group);
                    group = new ArrayList<>();
                }

                group.add(strs[strsSorted[i].i]);
            }

            if (group != null && !group.isEmpty())
                ret.add(group);

            return ret;
        }
    }

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> ret = new Solution().groupAnagrams(strs);
        System.out.println(ret);
    }
}
