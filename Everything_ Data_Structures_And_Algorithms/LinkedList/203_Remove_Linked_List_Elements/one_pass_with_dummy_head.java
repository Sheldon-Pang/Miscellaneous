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

 /* Time: O(n), Space: O(1) */
class Solution {
    public ListNode removeElements(ListNode head, int val) {

        /* dummyHead will prevent complication when the head needs to be deleted */
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        ListNode currentNode = head;

        while (currentNode != null) {

            if (currentNode.val == val) {
                prev.next = currentNode.next;
            } else {
                prev = currentNode;
            }

            currentNode = currentNode.next;
        }

        return dummyHead.next;
    }
}