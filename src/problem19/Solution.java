// https://leetcode.com/problems/remove-nth-node-from-end-of-list/
// Given the head of a linked list, remove the nth node from the end of the list and return its head.
// RESULT: Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Nth Node From End of List.

package problem19;

import java.security.InvalidParameterException;

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Single node short case
        if (head.next == null) {
            return null;
        }

        ListNode retHead = head;

        // As n is limited to 30 by design, array of 30 nodes will be quite cheap storage for one pass approach
        ListNode[] nodeList = new ListNode[30];
        int i = 0;
        while (head != null) {
            nodeList[i++] = head;
            head = head.next;

            if (i == 31) {
                throw new InvalidParameterException("List size > 30");
            }
        }

        // i = size of list now

        if (n > i) {
            throw new InvalidParameterException("n > list size");
        } else if (i == n) {    // First node removal
            return retHead.next;
        }

        nodeList[i - n - 1].next = nodeList[i - n].next;

        return retHead;
    }
}
