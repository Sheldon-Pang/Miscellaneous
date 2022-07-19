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
/* Copying Linked list into an array, then check for palindrome */
class Solution {
    public boolean isPalindrome(ListNode head) {

        List<Integer> myArrayList = new ArrayList<>();

        /* Convert LinkedList to ArrayList */
        ListNode currentNode = head;
        while (currentNode != null) {
            myArrayList.add(currentNode.val);
            currentNode = currentNode.next;
        }

        /* Two pointer method to check for palindrome */
        int start = 0;
        int end = myArrayList.size() - 1;
        while (start < end) {
            if (!myArrayList.get(start).equals(myArrayList.get(end)))
                return false;
            start++;
            end--;
        }

        return true;
    }
}