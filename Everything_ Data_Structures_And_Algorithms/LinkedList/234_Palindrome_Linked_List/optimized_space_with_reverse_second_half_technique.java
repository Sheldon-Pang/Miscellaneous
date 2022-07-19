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

/* Optimized space with reversing second half of linkedlist */
/* Time: O(n), Space: O(1) */
class Solution {
    public boolean isPalindrome(ListNode head) {

        if (head == null)
            return true;

        ListNode endOfFirstHalf = findEndOfFirstHalf(head);
        /* pass the endOfFirstHalf.next meaning the start of the second half */
        ListNode startOfSecondHalf = reverseLinkedList(endOfFirstHalf.next);

        /* Check for palindrome */
        ListNode p1 = head;
        ListNode p2 = startOfSecondHalf;
        while (p2 != null) {
            if (p1.val != p2.val)
                return false;
            p1 = p1.next;
            p2 = p2.next;
        }

        /* Restore the original order of the linked list */
        endOfFirstHalf.next = reverseLinkedList(startOfSecondHalf);
        return true;
    }

    /* Private function to reverse a linked list */
    private ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        ListNode currentNode = head;

        while (currentNode != null) {
            ListNode tempNext = currentNode.next;
            currentNode.next = prev;
            prev = currentNode;
            currentNode = tempNext;
        }

        return prev;
    }


    /* Find the linked list's end of first half using two pointer method */
    private ListNode findEndOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}