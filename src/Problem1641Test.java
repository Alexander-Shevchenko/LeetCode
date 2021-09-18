import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem1641Test {

    @Test
    void countVowelStrings() {
        Problem1641 countVowels = new Problem1641();
        int[] expectations = {5,15,35,70,126,210,330,495,715,1001,1365,1820,2380,3060,3876,4845,5985,7315,
                8855,10626,12650,14950,17550,20475,23751,27405,31465,35960,40920,46376,52360,58905,66045};

        for (int i = 0; i < expectations.length; ++i) {
            assertEquals(expectations[i], countVowels.countVowelStrings(i + 1));
        }
    }
}