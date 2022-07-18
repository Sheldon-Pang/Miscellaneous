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
/* Iterative approach with one pass, Time: O(n), Space: O(1) */
class Solution {
    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode currentNode = head;

        while (currentNode != null) {
            ListNode tempNode = currentNode.next;
            currentNode.next = prev;
            prev = currentNode;
            currentNode = tempNode;
        }

        return prev;
    }
}