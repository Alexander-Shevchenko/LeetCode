// https://leetcode.com/problems/substring-with-concatenation-of-all-words/
/* You are given a string s and an array of strings words of the same length.
    Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once,
    in any order, and without any intervening characters.
 */

// Runtime: 50 ms, faster than 83.43%

package problem30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    // words and their count ([0] - in words, [1] - currently used)
    HashMap<String, ArrayList<Integer>> wordToCount = new HashMap<>();

    private void initWordIndices(String[] words) {
        int i = 0;
        for (String word : words) {
            ArrayList<Integer> count = wordToCount.computeIfAbsent(word, k -> new ArrayList<>(2));
            if (count.isEmpty())
                count.add(1);
            else
                count.set(0, count.get(0) + 1);
        }
    }

    private void resetUsedCounts() {
        for (ArrayList<Integer> count : wordToCount.values()) {
            if (count.size() == 2) {
                count.set(1, 0);
            }
        }
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new ArrayList<>();
        
        if (words.length == 0)
            return ret;

        int wordLen = words[0].length();
        int allWordsLen = wordLen * words.length;
        
        initWordIndices(words);
        boolean bCountsClean = true;

        for (int i = 0; i <= s.length() - allWordsLen; ++i) {

            String startStr = s.substring(i, i + wordLen);
            List<Integer> count = wordToCount.get(startStr);
            if (count == null) {
                continue;
            }

            // Shortcut for 1 char len words
            if (words.length == 1) {
                ret.add(i);
                continue;
            }

            // OK, we've found a potential start of occurrence
            if (!bCountsClean) {
                resetUsedCounts();
            }

            if (count.size() == 1) {
                count.add(1);
            } else {
                count.set(1, 1);
            }
            bCountsClean = false;

            int wordsFound = 1;
            for (int j = i + wordLen; j <= s.length() - wordLen; j += wordLen) {
                startStr = s.substring(j, j + wordLen);
                count = wordToCount.get(startStr);
                if (count == null) {
                    break; // not used all words, no luck this time
                }

                if (count.size() == 1) { // Not inited yet
                    count.add(1);
                } else {
                    if (count.get(0).equals(count.get(1))) { // Used too many occurrences of this word already
                        break;
                    }
                    count.set(1, count.get(1) + 1);
                }

                if (++wordsFound == words.length) {
                    ret.add(i);
                    break;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        String [] words2 = new String[9];
        words2[0] = "c";
        words2[1] = "b";
        words2[2] = "a";
        words2[3] = "c";
        words2[4] = "a";
        words2[5] = "a";
        words2[6] = "a";
        words2[7] = "b";
        words2[8] = "c";
        List<Integer> ret = new Solution().findSubstring("bcabbcaabbccacacbabccacaababcbb", words2);

        System.out.println("ret = " + ret.toString());
    }
}

/* LARGE test data

        int i = 0;
        String [] words = new String[208];
        words[i++] = "toiscumkhociglkvispihvyoatxcx";
        words[i++] = "ndojyyephstlonsplrettspwepipw";
        words[i++] = "yzfkyoqlkrmmfirchzrphveuwmvga";
        words[i++] = "mxxihihnrfbamcyojqpkzodbejtmm";
        words[i++] = "fenjcjfdlvcpiatuhjdujhaffqsvq";
        words[i++] = "ehghndyqjodnnblfwmaygdstotfkv";
        words[i++] = "heoldutddnksutjakhtghpxxnjykx";
        words[i++] = "cvrwdewsxdeumhzfrvoilmvksuhyq";
        words[i++] = "ftqjvzyssocftjwemroghrncynmtc";
        words[i++] = "idiwclhuepgyynoslhzahtdqwlikt";
        words[i++] = "eurttrfrmstrbeokzhuzvbfmwywoh";
        words[i++] = "jxlluilzpysjcnwguyofnhfvhacez";
        words[i++] = "uskegagtlonducdogwbevugppsptd";
        words[i++] = "xmcxwufajmnveuwuoyosqnoqwvtjk";
        words[i++] = "wolpsfxdypmlbjotuxewskisnmczf";
        words[i++] = "fjryanrmzmpzoefapmnsjdgecrdyw";
        words[i++] = "jgmxawmndhsvwnjdjvjtxcsjapfog";
        words[i++] = "wuhkzghvmjhawcfszbhzrbpgsidnb";
        words[i++] = "yelbldxympctbzfupeocwhkypchuy";
        words[i++] = "vzduzxudwwqhpftwdspuimioanlzo";
        words[i++] = "bdpdeofidldoymakfnpgekmsltcrr";
        words[i++] = "fmyeodowglzyjzuhencufcwdobyds";
        words[i++] = "dhtypunakzituezjyhbrpuksbamui";
        words[i++] = "bdmiruibwznqcuczculujfiavzwyn";
        words[i++] = "eudzjxwbjvagnsjntskmocmpgkybq";
        words[i++] = "tuynydoakejmwkvojuwbfltqjfgxq";
        words[i++] = "psrdswqxqsegulcwrwsjnihxedfcq";
        words[i++] = "cokfdmtsgboidkpgpnmdeyhawkqqs";
        words[i++] = "fujhvgzdussqbwynylzvtjapvqtid";
        words[i++] = "rqeuglrsjnmvdsihicsgkybcjltcs";
        words[i++] = "vhybsbmvymjppfrqmlfrbkpjwpyyy";
        words[i++] = "aukagphzycvjtvwdhhxzagkevvucc";
        words[i++] = "hwkduzbxpdhtpvrzrfjndmsqfizmq";
        words[i++] = "ywnuzzmxeppokxksrfwrpuzqhjgqr";
        words[i++] = "qbajmepmmizaycwcgmjeopbivsyph";
        words[i++] = "uamscbxnqnfmmjyehvidnoimmxmtc";
        words[i++] = "nxvspywfggjrmxryybdltmsfykstm";
        words[i++] = "amrjbrsiovrxmqsyxhqmritjeauwq";
        words[i++] = "yorwboxdauhrkxehiwaputeouwxdf";
        words[i++] = "qkewycsdjglkiwaacdqterkixkgra";
        words[i++] = "ycngvlvpyvczfxvlwhjgicvempfob";
        words[i++] = "jgphsxzzqlvujkwwgiodbfjesnbsb";
        words[i++] = "mkxhemwbbclwdxwgngicplzgajmar";
        words[i++] = "mryvkeevlthvflsvognbxfjilwkdn";
        words[i++] = "mezrxffujeysplvavtjqjxsgujqsj";
        words[i++] = "rtotxqmzxvsqazajvrwsxyeyjteak";
        words[i++] = "sabctaegttffigupnwgakylngrrxu";
        words[i++] = "xccuoccdkbboymjtimdrmerspxpkt";
        words[i++] = "xusnnvngksbjabqjaohdvrniezhmx";
        words[i++] = "oyuejenqgjheulkxjnqkwvzznricl";
        words[i++] = "mxszcosgovisnbemrjlndqwkvhqso";
        words[i++] = "wsgnznrfmxjbdrkwjopylxezxgvet";
        words[i++] = "dxmisfskvevpcnujqxrqedleuyowk";
        words[i++] = "dhrgijeplijcvqbormrqglgmzsprt";
        words[i++] = "vuxchgerokejovrqonxxstibuniki";
        words[i++] = "lumyzmnzjzhzfpslwsukykwckvkts";
        words[i++] = "inwkbqmcobubjjpshucechrqrffqs";
        words[i++] = "ywtxruxokcubekzcrqengviwbtgnz";
        words[i++] = "ccpnmreqaqjrxwulpunagwxesbila";
        words[i++] = "pesxtpypenunfpjuyoevzztctecil";
        words[i++] = "sygfymdcjgvdxutlrhffhnpyjuxmx";
        words[i++] = "uisdfrvbxzxzhmuektssuktoknkfb";
        words[i++] = "cejvgynwouzhtfwuuukdbwpmkjrqx";
        words[i++] = "oudcoagcxjcuqvenznxxnprgvhasf";
        words[i++] = "sxnlkwgpbznzszyudpwrlgrdgwdyh";
        words[i++] = "qqbxkaqcyhiobvtqgqruumvvhxolb";
        words[i++] = "mkhleanvfpemuublnnyzfabtxsest";
        words[i++] = "bibaxwnriowoavosminabvfxastkc";
        words[i++] = "bcxgixgrhpfiofpwruzvpqyjzvoll";
        words[i++] = "lzccnsztxfyqhqyhkuppapvgvdtkm";
        words[i++] = "pdjkpshvrmqlhindhabubyokzdfrw";
        words[i++] = "qbbnhwpdokcpfpxinlfmkfrfqrtzk";
        words[i++] = "rnyelfschnagucguuqqqwitviynry";
        words[i++] = "qtrjwhrpisocwderqfiqxsdpkphjs";
        words[i++] = "vxttqosgpplkmxwgmsgtpantazppg";
        words[i++] = "tyisidnhlksfznubucqxwaheamndj";
        words[i++] = "kgaqzsckonjuhxdhqztjfxstjvikd";
        words[i++] = "jeuslzsdwvuoodipdpnlhdihaywzm";
        words[i++] = "vdzrwwkqvacxwgdhffyvjldgvchoi";
        words[i++] = "cftbefxgasawzagfugmuthjahylkh";
        words[i++] = "xraytcolbhkiiasaazkvqzvfxbaax";
        words[i++] = "oyqtzozufvvlktnvahvsseymtpeyf";
        words[i++] = "rnnujgyjugrzjoefmghjfhcrnbrtg";
        words[i++] = "rfzvgvptbgpwajgtysligupoqeoqx";
        words[i++] = "igbdclqtbikiacwpjrbxhmzejozpy";
        words[i++] = "dyzwwxgdbeqwlldyezmkopktzugxg";
        words[i++] = "hmetreydbcstkwoexwsfhfekfvfpl";
        words[i++] = "zcnftuzrvzjjudsgcqmmfpnmyrenu";
        words[i++] = "zzmvkskzeglxaqrrvmrgcwcnvkhwz";
        words[i++] = "vjswvekfyqhjnsusefdtakejxbejr";
        words[i++] = "rwwzwbcjwiqzkwzfuxfclmsxpdyvf";
        words[i++] = "fdbdychmupcsxvhazvrihhnxfyumo";
        words[i++] = "vdtevyducpdksntgyaqtkrrkwiyuh";
        words[i++] = "nbvqeyoghccxfuwacxzxqkezxefxa";
        words[i++] = "vpgbefpqpsjmdecmixmmbsjxzwvjd";
        words[i++] = "jwgqmsvhnykclexepxqxqzghwfxfd";
        words[i++] = "olyfxbvdrspxqnxnuoygkruczddgs";
        words[i++] = "qgmxtdfoiaakorebqpbbpegawrqym";
        words[i++] = "liaivbhcgvjjnxpggrewglalthmzv";
        words[i++] = "choncklguqgnyrcslwztbstmycjzi";
        words[i++] = "fpkdpenxlewyxxgrkmwrmshhzfnor";
        words[i++] = "hhhcaqxbqpthuaafwgrouaxonzocl";
        words[i++] = "ipahojoysepzhpljpaugrghgjimtd";
        words[i++] = "wosrmnouwpstgbrvhtlqcnmqbygbf";
        words[i++] = "nwyskffpxlragrnfffawqtgyfpmzx";
        words[i++] = "bcvvadhnssbvneecglnqxhavhvxpk";
        words[i++] = "hoavxqksjreddpmibbodtbhzfehgl";
        words[i++] = "lazxadnftllhmjslfbrtdlahkgwle";
        words[i++] = "uuukupjmbbvshzxyniaowdjamlfss";
        words[i++] = "tpqtazbphmfoluliznftodyguessh";
        words[i++] = "ychqumiscfkwmqqxunqrfbgqjdwmk";
        words[i++] = "rkdclgzjvqrjofjjvbyfragofeoaz";
        words[i++] = "pphhedxdepgfgrqerpuhgmaawhnhq";
        words[i++] = "cacrsvutylalqrykehjuofisdookj";
        words[i++] = "kyldfriuvjranikluqtjjcoiqffdx";
        words[i++] = "bnwvrwgoskzqkgffpsyhfmxhymqin";
        words[i++] = "uzmlliugckuljfkljoshjhlvvlnyw";
        words[i++] = "abfxqbdqnexvwsvzvcsbrmkbkuzsd";
        words[i++] = "xotbbyvxyovzxgtcyzgqnsvcfhczo";
        words[i++] = "bwtpqcqhvyyssvfknfhxvtodpzipu";
        words[i++] = "nsfbpjqkijvudpriqrfsrdfortimg";
        words[i++] = "tgwyqugeuahpuvvzmgarbsyuutmbx";
        words[i++] = "upnwqzbsazplkyaxqorqsshhlljjl";
        words[i++] = "edfyahijobxyhimebctobsjudkqst";
        words[i++] = "ialhfmgjohzoxvdaxuywfqrgmyahh";
        words[i++] = "jlhcpegmtrelbosyajljvwwedtxbd";
        words[i++] = "tpfppjzowoltyqijfoabgzejerpat";
        words[i++] = "mgogyhzpmsdemugqkspsmoppwbnwa";
        words[i++] = "nubmpwcdqkvhwfuvcahwibniohiqy";
        words[i++] = "ukfadjvdnrievszilfinxbyrvknfi";
        words[i++] = "dgnepdiimmkcxhattwglbkicvsfsw";
        words[i++] = "syqxmarjkshjhxobandwyzggjibjg";
        words[i++] = "bnwxjytnaejivivriamhgqsskqhnq";
        words[i++] = "hzyjdcbyuijjnkqluaczrnrbbwaee";
        words[i++] = "yscnqoohcsxenypyqhfklloudgmkl";
        words[i++] = "habidqszhxorzfypcjcnopzwigmbz";
        words[i++] = "wjdqxdrlsqvsxwxpqkljeyjpulbsw";
        words[i++] = "tytawuuyjrwxboogfessmltwdcssd";
        words[i++] = "pfixglatdvuogdoizdtsgsztsfcih";
        words[i++] = "apkvhvsqojyixaechvuoemmyqdlfk";
        words[i++] = "ouaehwnnxwkdplodpuqxdbemfwahp";
        words[i++] = "ixuckaralemvsnbgooorayceuedto";
        words[i++] = "ymxdjrqikughquwtenyucjdgrmipi";
        words[i++] = "smrwrlkvpnhqrvpdekmtpdfuxzjwp";
        words[i++] = "bhjakgajafgzxpqckmhdbbnqmcszp";
        words[i++] = "beqsmluixgsliatukrecgoldmzfhw";
        words[i++] = "greuevnjssjifvlqlhkllifxrxkdb";
        words[i++] = "yzsqcrdchhdqprtkkjsccowrjtyjj";
        words[i++] = "sviyovhitxeajqahshpejaqtcdkuv";
        words[i++] = "qtwomymjskujjtmxiueopwacrwfuq";
        words[i++] = "mzyjtctvtwgyiesxhynvogxnjdjph";
        words[i++] = "dyfbxcaypyquodcpwxkstbthuvjqg";
        words[i++] = "hfmflesfabvanxlrurjtigkjotftq";
        words[i++] = "mxydechlraajjmoqpcyoqmrjwoium";
        words[i++] = "nabesvshjmdbhyhirfrkqkmfwdguj";
        words[i++] = "bhrfxxemhgbkgmkhjtkzyzdqmxxwq";
        words[i++] = "gziobrjeanlvyukwlscexbkibvdjh";
        words[i++] = "mcwwubbnwenybmfqglaceuyqnoadz";
        words[i++] = "xyzvyblypeongzrttvwqzmrccwkzi";
        words[i++] = "ncfalqenfcswgerbfcqsapzdtscnz";
        words[i++] = "dtqpezboimeuyyujfjxkdmbjpizpq";
        words[i++] = "wmuhplfueqnvnhukgjarxlxvwmriq";
        words[i++] = "qwapdkoqswyclqyvbvpedzyoyedvu";
        words[i++] = "uoqbztnftzgahhxwxbgkilnmzfydy";
        words[i++] = "zsddaahohbszhqxxgripqlwlomjbw";
        words[i++] = "bwadkiavdswyuxdttoqaaykctprkw";
        words[i++] = "eixdbntdfcaeatyyainfpkclbgaaq";
        words[i++] = "nmjnpttflsmjifknezrneedvgzfmn";
        words[i++] = "avlzyhfmeasmgrjawongccgfbgoua";
        words[i++] = "kklimhhjqkmuaifnodtpredhqygme";
        words[i++] = "xzbwenvteifxuuefnimnadwxhruvo";
        words[i++] = "ugmwlmidtxkvqhbuaecevwhmwkfqm";
        words[i++] = "rhpyjfxbjjryslfpqoiphrwfjqqha";
        words[i++] = "eeaipxrokncholathupdetgaktmvm";
        words[i++] = "ltuimrnsphqslmgvmmojawwptghon";
        words[i++] = "azitvyhvlspvoaeipdsjhgyfjbxhi";
        words[i++] = "efrelxezcgikdliyhvpocvvpkvagv";
        words[i++] = "znxforctwzecxyrkwufpdxadrgzcz";
        words[i++] = "kcqgynjcpbylayvgdqfpbqmshksyf";
        words[i++] = "hrljvedsywrlyccpaowjaqyfaqioe";
        words[i++] = "cjmfyvfybxiuqtkdlzqedjxxbvdsf";
        words[i++] = "zeqljuypthkmywaffmcjkickqqsuh";
        words[i++] = "wnfzoyvkiogisdfyjmfomcazigukq";
        words[i++] = "zyaaqxorqxbkenscbveqbaociwmqx";
        words[i++] = "ahnpivdtlcnptnxjyiaafislqavam";
        words[i++] = "edtqirqmjtvsfhadhafktyrmkzmvi";
        words[i++] = "wponuefgdtcrgxswiddygeeflpjee";
        words[i++] = "xozgwhtbhlkvrzismnozqpfthajaf";
        words[i++] = "ptnfnojnlinbfmylhdlijcvcxzjhd";
        words[i++] = "uxekzlgigjpsukjvsdihrjzgovnre";
        words[i++] = "rbohxlytsmoeleqrjvievpjipsgdk";
        words[i++] = "fxtzaxpcfrcovwgrcwqptoekhmgpo";
        words[i++] = "tvxvvgjbyxpgwpganjiaumojpyhhy";
        words[i++] = "vqjjhfaupylefbvbsbhdncsshmrhx";
        words[i++] = "urhedneauccrkyjfiptjfxmpxlssr";
        words[i++] = "ltvgknnlodtbhnbhjkmuhwxvzgmkh";
        words[i++] = "ucztsneqttsuirmjriohhgunzatyf";
        words[i++] = "rbzryfaeuqkfxrbldyusoeoldpbwa";
        words[i++] = "atlgpnkuksuesrduxkodwjzgubpsm";
        words[i++] = "lrdniqbzxrbpcvmzpyqklsskpwctg";
        words[i++] = "qvnvgzkyhistydagsgnujiviyijdn";
        words[i++] = "uzatydzcnktnkeyztoqvogodxxznh";
        words[i++] = "ocbvphmtpwhcgjbnmxgidtlqcnnwt";
        words[i++] = "koudovxrjkusxdazxaawmvoostlvv";
        words[i++] = "ptruqmjtbaapgmkfnbwnlvzlxwdpz";
        words[i++] = "xdxtpbpoemekvxzrrakwjxcxqsdas";
        words[i++] = "gdpclnsguabtgbfwdmrmbvydorfrb";
        words[i++] = "htwxdbarwuxykgduxjlkxppwyruih";
        List<Integer> ret = new Solution().findSubstring("ejwwmybnorgshugzmoxopwuvshlcwasclobxmckcvtxfndeztdqiakfusswqsovdfwatanwxgtctyjvsmlcoxijrahivwfybbbudosawnfpmomgczirzscqvlaqhfqkithlhbodptvdhjljltckogcjsdbbktotnxgwyuapnxuwgfirbmdrvgapldsvwgqjfxggtixjhshnzphcemtzsvodygbxpriwqockyavfscvtsewyqpxlnnqnvrkmjtjbjllilinflkbfoxdhocsbpirmcbznuioevcojkdqvoraeqdlhffkwqbjsdkfxstdpxryixrdligpzldgtiqryuasxmxwgtcwsvwasngdwovxzafuixmjrobqbbnhwpdokcpfpxinlfmkfrfqrtzkhabidqszhxorzfypcjcnopzwigmbznmjnpttflsmjifknezrneedvgzfmnhoavxqksjreddpmibbodtbhzfehgluuukupjmbbvshzxyniaowdjamlfssndojyyephstlonsplrettspwepipwcjmfyvfybxiuqtkdlzqedjxxbvdsfurhedneauccrkyjfiptjfxmpxlssrkyldfriuvjranikluqtjjcoiqffdxaukagphzycvjtvwdhhxzagkevvuccxccuoccdkbboymjtimdrmerspxpktsmrwrlkvpnhqrvpdekmtpdfuxzjwpvqjjhfaupylefbvbsbhdncsshmrhxoyuejenqgjheulkxjnqkwvzznriclrbzryfaeuqkfxrbldyusoeoldpbwadhrgijeplijcvqbormrqglgmzsprtmryvkeevlthvflsvognbxfjilwkdndyzwwxgdbeqwlldyezmkopktzugxgkklimhhjqkmuaifnodtpredhqygmedtqpezboimeuyyujfjxkdmbjpizpqltvgknnlodtbhnbhjkmuhwxvzgmkhbcvvadhnssbvneecglnqxhavhvxpkjxlluilzpysjcnwguyofnhfvhaceztoiscumkhociglkvispihvyoatxcxbeqsmluixgsliatukrecgoldmzfhwkgaqzsckonjuhxdhqztjfxstjvikdrhpyjfxbjjryslfpqoiphrwfjqqhaamrjbrsiovrxmqsyxhqmritjeauwqbwtpqcqhvyyssvfknfhxvtodpzipueixdbntdfcaeatyyainfpkclbgaaqrwwzwbcjwiqzkwzfuxfclmsxpdyvfbnwxjytnaejivivriamhgqsskqhnqeurttrfrmstrbeokzhuzvbfmwywohmgogyhzpmsdemugqkspsmoppwbnwabdmiruibwznqcuczculujfiavzwynsyqxmarjkshjhxobandwyzggjibjgzyaaqxorqxbkenscbveqbaociwmqxxyzvyblypeongzrttvwqzmrccwkzidyfbxcaypyquodcpwxkstbthuvjqgialhfmgjohzoxvdaxuywfqrgmyahhtpqtazbphmfoluliznftodyguesshcacrsvutylalqrykehjuofisdookjhrljvedsywrlyccpaowjaqyfaqioesxnlkwgpbznzszyudpwrlgrdgwdyhucztsneqttsuirmjriohhgunzatyfrfzvgvptbgpwajgtysligupoqeoqxoyqtzozufvvlktnvahvsseymtpeyfvxttqosgpplkmxwgmsgtpantazppgnubmpwcdqkvhwfuvcahwibniohiqyywnuzzmxeppokxksrfwrpuzqhjgqryorwboxdauhrkxehiwaputeouwxdfoudcoagcxjcuqvenznxxnprgvhasffxtzaxpcfrcovwgrcwqptoekhmgpoywtxruxokcubekzcrqengviwbtgnzvdzrwwkqvacxwgdhffyvjldgvchoiwnfzoyvkiogisdfyjmfomcazigukqlumyzmnzjzhzfpslwsukykwckvktswjdqxdrlsqvsxwxpqkljeyjpulbswwmuhplfueqnvnhukgjarxlxvwmriqjgmxawmndhsvwnjdjvjtxcsjapfogpesxtpypenunfpjuyoevzztctecilqqbxkaqcyhiobvtqgqruumvvhxolbyzsqcrdchhdqprtkkjsccowrjtyjjmkhleanvfpemuublnnyzfabtxsestncfalqenfcswgerbfcqsapzdtscnzugmwlmidtxkvqhbuaecevwhmwkfqmvpgbefpqpsjmdecmixmmbsjxzwvjdmxydechlraajjmoqpcyoqmrjwoiumuzatydzcnktnkeyztoqvogodxxznhvzduzxudwwqhpftwdspuimioanlzobhjakgajafgzxpqckmhdbbnqmcszpuoqbztnftzgahhxwxbgkilnmzfydyxusnnvngksbjabqjaohdvrniezhmxmkxhemwbbclwdxwgngicplzgajmaryzfkyoqlkrmmfirchzrphveuwmvgaxzbwenvteifxuuefnimnadwxhruvoavlzyhfmeasmgrjawongccgfbgoualiaivbhcgvjjnxpggrewglalthmzvgziobrjeanlvyukwlscexbkibvdjhdgnepdiimmkcxhattwglbkicvsfswocbvphmtpwhcgjbnmxgidtlqcnnwtfujhvgzdussqbwynylzvtjapvqtidpdjkpshvrmqlhindhabubyokzdfrwqvnvgzkyhistydagsgnujiviyijdnabfxqbdqnexvwsvzvcsbrmkbkuzsdehghndyqjodnnblfwmaygdstotfkvxozgwhtbhlkvrzismnozqpfthajafuxekzlgigjpsukjvsdihrjzgovnreqwapdkoqswyclqyvbvpedzyoyedvuuamscbxnqnfmmjyehvidnoimmxmtcinwkbqmcobubjjpshucechrqrffqsyscnqoohcsxenypyqhfklloudgmklcejvgynwouzhtfwuuukdbwpmkjrqxeeaipxrokncholathupdetgaktmvmftqjvzyssocftjwemroghrncynmtchhhcaqxbqpthuaafwgrouaxonzocljeuslzsdwvuoodipdpnlhdihaywzmymxdjrqikughquwtenyucjdgrmipiidiwclhuepgyynoslhzahtdqwliktzsddaahohbszhqxxgripqlwlomjbwtuynydoakejmwkvojuwbfltqjfgxqhwkduzbxpdhtpvrzrfjndmsqfizmqxdxtpbpoemekvxzrrakwjxcxqsdasptruqmjtbaapgmkfnbwnlvzlxwdpzfjryanrmzmpzoefapmnsjdgecrdywsabctaegttffigupnwgakylngrrxurtotxqmzxvsqazajvrwsxyeyjteakeudzjxwbjvagnsjntskmocmpgkybqbnwvrwgoskzqkgffpsyhfmxhymqinrbohxlytsmoeleqrjvievpjipsgdkrqeuglrsjnmvdsihicsgkybcjltcswolpsfxdypmlbjotuxewskisnmczfgreuevnjssjifvlqlhkllifxrxkdbjlhcpegmtrelbosyajljvwwedtxbdccpnmreqaqjrxwulpunagwxesbilalrdniqbzxrbpcvmzpyqklsskpwctgqtrjwhrpisocwderqfiqxsdpkphjsapkvhvsqojyixaechvuoemmyqdlfkuzmlliugckuljfkljoshjhlvvlnywvjswvekfyqhjnsusefdtakejxbejrchoncklguqgnyrcslwztbstmycjziuskegagtlonducdogwbevugppsptdqbajmepmmizaycwcgmjeopbivsyphtvxvvgjbyxpgwpganjiaumojpyhhywosrmnouwpstgbrvhtlqcnmqbygbfnabesvshjmdbhyhirfrkqkmfwdgujhzyjdcbyuijjnkqluaczrnrbbwaeeupnwqzbsazplkyaxqorqsshhlljjlpphhedxdepgfgrqerpuhgmaawhnhqwsgnznrfmxjbdrkwjopylxezxgvetcvrwdewsxdeumhzfrvoilmvksuhyqltuimrnsphqslmgvmmojawwptghonigbdclqtbikiacwpjrbxhmzejozpypfixglatdvuogdoizdtsgsztsfcihtgwyqugeuahpuvvzmgarbsyuutmbxuisdfrvbxzxzhmuektssuktoknkfbmcwwubbnwenybmfqglaceuyqnoadzfenjcjfdlvcpiatuhjdujhaffqsvqvuxchgerokejovrqonxxstibunikiedfyahijobxyhimebctobsjudkqstbcxgixgrhpfiofpwruzvpqyjzvollheoldutddnksutjakhtghpxxnjykxjwgqmsvhnykclexepxqxqzghwfxfdhfmflesfabvanxlrurjtigkjotftqnwyskffpxlragrnfffawqtgyfpmzxfpkdpenxlewyxxgrkmwrmshhzfnorolyfxbvdrspxqnxnuoygkruczddgssygfymdcjgvdxutlrhffhnpyjuxmxefrelxezcgikdliyhvpocvvpkvagvmezrxffujeysplvavtjqjxsgujqsjznxforctwzecxyrkwufpdxadrgzczrnyelfschnagucguuqqqwitviynrypsrdswqxqsegulcwrwsjnihxedfcqychqumiscfkwmqqxunqrfbgqjdwmkyelbldxympctbzfupeocwhkypchuyvhybsbmvymjppfrqmlfrbkpjwpyyytytawuuyjrwxboogfessmltwdcssdqtwomymjskujjtmxiueopwacrwfuqazitvyhvlspvoaeipdsjhgyfjbxhityisidnhlksfznubucqxwaheamndjxmcxwufajmnveuwuoyosqnoqwvtjkwuhkzghvmjhawcfszbhzrbpgsidnbmxxihihnrfbamcyojqpkzodbejtmmipahojoysepzhpljpaugrghgjimtdahnpivdtlcnptnxjyiaafislqavamqgmxtdfoiaakorebqpbbpegawrqymqkewycsdjglkiwaacdqterkixkgraedtqirqmjtvsfhadhafktyrmkzmvidxmisfskvevpcnujqxrqedleuyowkjgphsxzzqlvujkwwgiodbfjesnbsbzcnftuzrvzjjudsgcqmmfpnmyrenuxotbbyvxyovzxgtcyzgqnsvcfhczoptnfnojnlinbfmylhdlijcvcxzjhdixuckaralemvsnbgooorayceuedtomzyjtctvtwgyiesxhynvogxnjdjphcftbefxgasawzagfugmuthjahylkhatlgpnkuksuesrduxkodwjzgubpsmzzmvkskzeglxaqrrvmrgcwcnvkhwzbibaxwnriowoavosminabvfxastkcrkdclgzjvqrjofjjvbyfragofeoazzeqljuypthkmywaffmcjkickqqsuhsviyovhitxeajqahshpejaqtcdkuvgdpclnsguabtgbfwdmrmbvydorfrbcokfdmtsgboidkpgpnmdeyhawkqqshtwxdbarwuxykgduxjlkxppwyruihkcqgynjcpbylayvgdqfpbqmshksyfbhrfxxemhgbkgmkhjtkzyzdqmxxwqvdtevyducpdksntgyaqtkrrkwiyuhukfadjvdnrievszilfinxbyrvknfihmetreydbcstkwoexwsfhfekfvfplmxszcosgovisnbemrjlndqwkvhqsofdbdychmupcsxvhazvrihhnxfyumonbvqeyoghccxfuwacxzxqkezxefxarnnujgyjugrzjoefmghjfhcrnbrtgouaehwnnxwkdplodpuqxdbemfwahptpfppjzowoltyqijfoabgzejerpatwponuefgdtcrgxswiddygeeflpjeelzccnsztxfyqhqyhkuppapvgvdtkmxraytcolbhkiiasaazkvqzvfxbaaxkoudovxrjkusxdazxaawmvoostlvvnsfbpjqkijvudpriqrfsrdfortimgdhtypunakzituezjyhbrpuksbamuiycngvlvpyvczfxvlwhjgicvempfobbwadkiavdswyuxdttoqaaykctprkwfmyeodowglzyjzuhencufcwdobydslazxadnftllhmjslfbrtdlahkgwlebdpdeofidldoymakfnpgekmsltcrrnxvspywfggjrmxryybdltmsfykstmlnzjitaipfoyohkmzimcozxardydxtpjgquoluzbznzqvlewtqyhryjldjoadgjlyfckzbnbootlzxhupieggntjxilcqxnocpyesnhjbauaxcvmkzusmodlyonoldequfunsbwudquaurogsiyhydswsimflrvfwruouskxjfzfynmrymyyqsvkajpnanvyepnzixyteyafnmwnbwmtojdpsucthxtopgpxgnsmnsrdhpskledapiricvdmtwaifrhnebzuttzckroywranbrvgmashxurelyrrbslxnmzyeowchwpjplrdnjlkfcoqdhheavbnhdlltjpahflwscafnnsspikuqszqpcdyfrkaabdigogatgiitadlinfyhgowjuvqlhrniuvrketfmboibttkgakohbmsvhigqztbvrsgxlnjndrqwmcdnntwofojpyrhamivfcdcotodwhvtuyyjlthbaxmrvfzxrhvzkydartfqbalxyjilepmemawjfxhzecyqcdswxxmaaxxyifmouauibstgpcfwgfmjlfhketkeshfcorqirmssfnbuqiqwqfhbmol", words);
*/
