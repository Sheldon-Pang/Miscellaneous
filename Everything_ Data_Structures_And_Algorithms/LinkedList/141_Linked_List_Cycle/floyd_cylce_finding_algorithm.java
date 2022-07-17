/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

/* LeetCode 141. Linked List Cycle */
/* Floyd's Cycle Finding Algorithm, two pointer fast and slow */
/* slow move one step while fast move two steps at the same time */
/* if linkedlist contain cycle, fast will eventually meet slow */
/* or fast reach null, meaning no cycle is found */
/* Time:O(n), Space: O(1) */
public class Solution {
    public boolean hasCycle(ListNode head) {

        if (head == null)
            return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {

            /* fast pointer reached the end of linkedlist */
            if (fast == null || fast.next == null)
                return false;

            slow = slow.next;
            fast = fast.next.next;

        }

        return true; /* loop will exit when fast meet with slow meaning cycle exist */
    }
}