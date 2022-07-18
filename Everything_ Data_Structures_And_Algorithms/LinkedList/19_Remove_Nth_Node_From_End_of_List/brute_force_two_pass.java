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
/* Brute Force method, required two passes */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode currentNode = head;
        ListNode nodeToDelete = head;
        ListNode tempHead = head;
        int length = 0;

        /* Loop and count number of nodes */
        while (currentNode != null) {
            length++;
            currentNode = currentNode.next;
        }

        System.out.print(length);

        if (head.next == null && n == 1)
            return null;
        if (n == length)
            return head.next;

        /* Perform deletion */
        for (int i = 0; i < length - n - 1; i++) {
            nodeToDelete = nodeToDelete.next;
        }
        nodeToDelete.next = nodeToDelete.next.next;

        return tempHead;
    }
}