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

/* Recursion, Time: O(n), Space: O(n) */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        /* both p and q are null */
        if (p == null && q == null) {
            return true;
        }

        /* one of the tree is null */
        if (p == null || q == null) {
            return false;
        }

        /* base case */
        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }
}