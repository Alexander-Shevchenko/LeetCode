package problem468;

public class IPCheckerFactory {
    private final String ipAddr;
    public IPCheckerFactory(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public IPChecker getChecker() {
        return ipAddr.contains(".") ? new IPv4Checker(ipAddr) : new IPv6Checker(ipAddr);
    }
}
