// 1641.Count Sorted Vowel Strings

import java.util.ArrayList;
import java.util.List;

class Solution {

    private static String[] vowels = {"a","e","i","o","u"};

    public int countVowelStrings(int n) {
        if (n <= 0)
            return 0;

        List<Integer> counters = new ArrayList<>();
        counters.add(vowels.length);
        while (--n > 0) {
            List<Integer> curCounters = counters;
            counters = new ArrayList<>();
            for (Integer i: curCounters) {
                do {
                    counters.add(i);
                } while (--i > 0);
            }
        }

        return counters.stream().mapToInt(a -> a).sum();
    }
}

public class Problem1641 {
    public static void main(String[] args) {
        Solution countVowels = new Solution();
        for (int i = 1; i < 34; i++) {
            System.out.println("i = " + countVowels.countVowelStrings(i));
        }
    }
}
