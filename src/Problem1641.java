// 1641.Count Sorted Vowel Strings

public class Problem1641 {

    private static String[] vowels = {"a","e","i","o","u"};

    public int countVowelStrings(int n) {
        if (n <= 0)
            return 0;

        int[] multipliers = new int[vowels.length];
        multipliers[0] = 1;
        while (--n > 0) {
            for (int i = 1; i < vowels.length; i++) {
                multipliers[i] += multipliers[i - 1];
            }
        }

        int ret = 0;
        for (int i = 0; i < vowels.length; i++) {
            ret += (vowels.length - i) * multipliers[i];
        }
        return ret;
    }

    public static void main(String[] args) {
        Problem1641 countVowels = new Problem1641();
        for (int i = 1; i < 34; i++) {
            System.out.print(countVowels.countVowelStrings(i) + ",");
        }
    }
}
