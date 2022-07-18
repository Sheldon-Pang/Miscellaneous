/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/* Optimized with two pointer, only one pass required */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        /* Create a dummy head in case head gets deleted */
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode p1 = dummyHead;
        ListNode p2 = dummyHead;

        /* Advance p1 by n step */
        for (int i = 0; i <= n; i++) {
            p1 = p1.next;
        }

        /* Advance both pointer until p1 reach the end */
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        /* Use p2 to delete the node */
        p2.next = p2.next.next;

        return dummyHead.next;
    }
}