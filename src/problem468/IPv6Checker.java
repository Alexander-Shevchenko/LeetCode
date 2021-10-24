package problem468;

public class IPv6Checker extends IPChecker{

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
