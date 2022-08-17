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

/* 21. Merge Two Sorted Lists */
/* Iterative method, Time: O(n + m), Space: O(1) */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        /* Pesudo head node */
        ListNode pesudoHead = new ListNode(-1);

        /* Prev node to track the lastly connected node */
        ListNode prev = pesudoHead;

        /* Merge two list */
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            /* prev become the recently connected node */
            prev = prev.next;
        }

        /* After loop, there might still have nodes left in list1 or 2 */
        /* Connect non-null list to end of the merged list */
        if (list1 == null && list2 != null) {
            prev.next = list2;
        } else if (list1 != null && list2 == null) {
            prev.next = list1;
        }


        return pesudoHead.next;
    }
}