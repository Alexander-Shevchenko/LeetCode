import java.util.*;

// https://leetcode.com/problems/group-anagrams/
// 49. Group Anagrams
// Given an array of strings strs, group the anagrams together. You can return the answer in any order.
public class Problem49 {
    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            // Sort all strings, so anagrams match
            List<String> sortedStrs = new ArrayList<>(strs.length);
            for (String s: strs) {
                char charArray[] = s.toCharArray();
                Arrays.sort(charArray);
                sortedStrs.add(new String(charArray));
            }

            // Group anagrams
            Map<String, List<Integer>> groups = new HashMap<>();
            for (int i = 0; i < sortedStrs.size(); ++i) {
                List<Integer> anogramIdxs = groups.get(sortedStrs.get(i));
                if (anogramIdxs == null) {
                    anogramIdxs = new ArrayList<>(1);
                    anogramIdxs.add(i);
                    groups.put(sortedStrs.get(i), anogramIdxs);
                } else {
                    anogramIdxs.add(i);
                }
            }

            List<List<String>> ret = new ArrayList<>();

            // Form groups
            for (List<Integer> anogramIdxs: groups.values()) {
                List<String> group = new ArrayList<>(anogramIdxs.size());
                for (Integer idx : anogramIdxs) {
                    group.add(strs[idx]);
                }
                ret.add(group);
            }

            return ret;
        }
    }

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> ret = new Solution().groupAnagrams(strs);
        System.out.println(ret);
    }
}
