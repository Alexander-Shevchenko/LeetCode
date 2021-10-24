package problem468;

public class IPv4Checker extends IPChecker{

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
