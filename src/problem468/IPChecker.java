// Runtime: 1 ms, faster than 98.31% of Java online submissions for Validate IP Address.
// Memory Usage: 36.9 MB, less than 95.15% of Java online submissions for Validate IP Address.
package problem468;

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
        int i = 0;
        for (; i <= ipLen && curSectionNo <= getnSections(); ++i) {
            char c = (i == ipLen) ? getSeparator() : ipAddr.charAt(i);
            if (isSeparator(c)) {
                if (curSectionPos == 0) {
                    return NEITHER; // double separator (section missing)
                }
                ++curSectionNo;
                curSectionPos = 0;
            } else if (isSectionChar(c, curSectionPos)) {
                ++curSectionPos;
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
