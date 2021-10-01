// https://leetcode.com/problems/reverse-nodes-in-k-group/submissions/
// Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Nodes in k-Group.
// Memory Usage: 39.2 MB, less than 69.98% of Java online submissions for Reverse Nodes in k-Group.
public class Problem25 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder();
            for (ListNode printNode = this; printNode != null; printNode = printNode.next) {
                ret.append(printNode.val);
                if (printNode.next != null)
                    ret.append(" -> ");
            }
            return ret.toString();
        }
    }

    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || k == 1)
                return head;

            ListNode lastNode = null;
            ListNode firstNode = head;
            ListNode retNode = null;

            int groupSteps = 0;
            do {
                ListNode nextNode = head.next;
                head.next = lastNode;
                lastNode = head;
                head = nextNode;

                if (++groupSteps == k || head == null) {
                    if (retNode == null) { // First  reverse group
                        retNode = lastNode;
                        firstNode.next = head;
                    }
                    else {
                        if (groupSteps == k) { // End of reverse group
                            ListNode newFirstNode = firstNode.next;
                            firstNode.next = lastNode;
                            firstNode = newFirstNode;
                            firstNode.next = head;
                        } else { // Some tail after last reversed group remains
                            if (groupSteps == 1)
                                firstNode.next = lastNode;
                            else // There is a tail reversed, roll that back
                                firstNode.next = reverseKGroup(lastNode, groupSteps);
                        }
                    }

                    lastNode = null;
                    groupSteps = 0;
                }

            } while (head != null);

            return retNode;
        }
    }

    public static void main(String[] args) {
        ListNode testNode = null;
        for (int i = 10; i > 0; --i) {
            testNode = new ListNode(i, testNode);
        }

        System.out.println(new Solution().reverseKGroup(testNode, 9));
    }
}
