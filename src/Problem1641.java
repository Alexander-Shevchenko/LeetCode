// 1641.Count Sorted Vowel Strings

import java.util.ArrayList;
import java.util.List;

public class Problem1641 {

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

    public static void main(String[] args) {
        Problem1641 countVowels = new Problem1641();
        for (int i = 1; i < 34; i++) {
            System.out.print(countVowels.countVowelStrings(i) + ",");
        }
    }
}
