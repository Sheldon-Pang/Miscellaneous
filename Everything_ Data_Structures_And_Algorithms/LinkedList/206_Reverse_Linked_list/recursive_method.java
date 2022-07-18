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
/* Recursive method, Time: O(n), Space: O(n) */
class Solution {
    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = reverseList(head.next);
        /* We want nk+1’s next node to point to nk. */
        /* Threfore, nk.next.next = nk; */
        head.next.next = head;
        /* n1's next must point to Ø, otherwise will have cycle */
        head.next = null;

        return p;
    }
}