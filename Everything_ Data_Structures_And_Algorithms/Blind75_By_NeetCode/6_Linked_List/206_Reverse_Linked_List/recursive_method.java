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

/* 206. Reverse Linked List */
/* Recursive method, Time: O(n), Space: O(n) */
class Solution {
    public ListNode reverseList(ListNode head) {
        // Base case: if head input is null, or head.next is null then return the head back to previous call stack
        if (head == null || head.next == null) {
            return head;
        }
        // used for storing the head of the last node from original input
        ListNode reversedHead = reverseList(head.next);
        // set curr node's next node's next pointer to curr node
        head.next.next = head;
        // set curr node's next pointer to null
        head.next = null;
        // return the reversed head node back to previous call stack
        return reversedHead;
    }
}