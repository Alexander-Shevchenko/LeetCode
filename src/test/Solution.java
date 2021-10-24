package test;

class Solution {
    public abstract class IPChecker {
        protected String ipAddr = null;
        protected final String NEITHER = "Neither";
        protected String ipRet = null;
        protected int nSections = 0; // number of IP address sections
        protected char cSeparator = '\0';

        protected boolean isSeparator(char c) {
            return c == cSeparator;
        }

        protected char getSeparator() {
            return cSeparator;
        }

        protected int getnSections() {
            return nSections;
        }

        /***
         * Checks if the provided IP address section char is valid
         * @param c     - character to test
         * @param pos   - position in the current address section
         * @return valid
         */
        protected abstract boolean isSectionChar(char c, int pos);

        public String check() {
            int ipLen = ipAddr.length();
            int curSectionNo = 0;
            int curSectionPos = 0;
            boolean bNewSection = true;
            int i = 0;
            for (; i <= ipLen && curSectionNo <= getnSections(); ++i) {
                char c = (i == ipLen) ? getSeparator() : ipAddr.charAt(i);
                if (isSeparator(c)) {
                    if (bNewSection) {
                        return NEITHER; // section cannot start with a separator
                    }
                    ++curSectionNo;
                    curSectionPos = 0;
                } else if (isSectionChar(c, curSectionPos)) {
                    ++curSectionPos;
                    bNewSection = false;
                } else {
                    return NEITHER;
                }
            }

            if (i == ipLen + 1 && curSectionNo == getnSections()) {
                return ipRet;
            }

            return NEITHER;
        }
    }

    public class IPv4Checker extends IPChecker {

        private int sectionVal = 0;

        public IPv4Checker(String ipAddr) {
            this.ipAddr = ipAddr;
            nSections = 4;
            ipRet = "IPv4";
            cSeparator = '.';
        }

        @Override
        protected boolean isSectionChar(char c, int pos) {
            if (c < '0' || c > '9') {
                return false;
            }

            if (pos == 0) {
                sectionVal = 0;
            } else if (sectionVal == 0) {
                return false; // leading zero
            } else {
                sectionVal *= 10;
            }
            sectionVal += (c - '0');

            return sectionVal <= 255;
        }
    }

    public class IPv6Checker extends IPChecker {

        public IPv6Checker(String ipAddr) {
            this.ipAddr = ipAddr;
            nSections = 8;
            ipRet = "IPv6";
            cSeparator = ':';
        }

        @Override
        protected boolean isSectionChar(char c, int pos) {
            if (pos == 4) {
                return false; // 4 chars in IPv6 section max
            }

            return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
        }
    }

    public class IPCheckerFactory {
        private final String ipAddr;
        public IPCheckerFactory(String ipAddr) {
            this.ipAddr = ipAddr;
        }

        public IPChecker getChecker() {
            return ipAddr.contains(".") ? new IPv4Checker(ipAddr) : new IPv6Checker(ipAddr);
        }
    }
    
    public String validIPAddress(String IP) {
        return new IPCheckerFactory(IP).getChecker().check();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().validIPAddress("1.1.1.1"));
        System.out.println(new Solution().validIPAddress("255.255.255.255"));
        System.out.println(new Solution().validIPAddress("0.0.0.0"));
        System.out.println(new Solution().validIPAddress("1.01.0.0"));
        System.out.println(new Solution().validIPAddress("1.x.0.0"));
        System.out.println(new Solution().validIPAddress("1.0.0.0.1"));
        System.out.println(new Solution().validIPAddress("1.0.0.0.."));
        System.out.println(new Solution().validIPAddress("1.0.0.0.1."));
        System.out.println(new Solution().validIPAddress("..."));
        System.out.println(new Solution().validIPAddress(".1.1."));
        System.out.println(new Solution().validIPAddress("266.1.1.1"));
        System.out.println(new Solution().validIPAddress("36.1.1.1111"));


        System.out.println(new Solution().validIPAddress("a:B:c:D:0:99FF:bc0d:3333"));
        System.out.println(new Solution().validIPAddress("2001:0db8:85a3:0000:0000:8a2e:0370:7334"));
        System.out.println(new Solution().validIPAddress("2001:db8:85a3:0:0:8A2E:0370:7334"));
        System.out.println(new Solution().validIPAddress("2001:0db8:85a3::8A2E:037j:7334"));
        System.out.println(new Solution().validIPAddress("02001:0db8:85a3:0000:0000:8a2e:0370:7334"));
    }
}