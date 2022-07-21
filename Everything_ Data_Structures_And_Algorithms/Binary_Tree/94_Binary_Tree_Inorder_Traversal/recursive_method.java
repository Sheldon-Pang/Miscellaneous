/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/* Recursive Method, inorder traversal */
/* Time: O(n), Space: O(n) */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {

        LinkedList<Integer> result = new LinkedList<>();
        helper(root, result);
        return result;
    }

    public void helper(TreeNode root, LinkedList<Integer> result) {

        if (root != null) {
            helper(root.left, result);
            result.add(root.val);
            helper(root.right, result);
        }
    }
}