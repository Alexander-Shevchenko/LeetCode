/* https://leetcode.com/problems/validate-ip-address
468. Validate IP Address

Runtime: 1 ms, faster than 98.31% of Java online submissions for Validate IP Address.
Memory Usage: 36.9 MB, less than 95.15% of Java online submissions for Validate IP Address.
 */
package problem468;

class Solution {
    public String validIPAddress(String IP) {
        return new IPCheckerFactory(IP).getChecker().check();
    }

    public static void main(String[] args) {
        // Testing
        System.out.println(new Solution().validIPAddress("1.0.1."));
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