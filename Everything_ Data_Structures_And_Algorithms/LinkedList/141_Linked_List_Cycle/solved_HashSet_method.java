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
/* Using HashSet to find if a linked list contain a cycle or not */
/* Time:O(n), Space: O(n) */
public class Solution {
    public boolean hasCycle(ListNode head) {

        Set<ListNode> visited = new HashSet<>();

        while (head != null) {

            if (visited.contains(head))
                return true;

            visited.add(head);
            head = head.next;
        }

        return false;
    }
}