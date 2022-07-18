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
class Solution {
    public ListNode oddEvenList(ListNode head) {

        if (head == null)
            return null;

        ListNode pOdd = head;
        ListNode pEven = head.next;
        ListNode tempHeadEven = head.next; /* Used to store head of even nodes */

        while (pEven != null && pEven.next != null) {

            pOdd.next = pEven.next;
            pOdd = pOdd.next;

            pEven.next = pOdd.next;
            pEven = pEven.next;

        }

        pOdd.next = tempHeadEven;

        return head;
    }
}