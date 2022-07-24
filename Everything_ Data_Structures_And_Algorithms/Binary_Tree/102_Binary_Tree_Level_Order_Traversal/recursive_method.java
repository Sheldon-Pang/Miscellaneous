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
/* Recursive method, Time: O(n), Space: O(n) */
class Solution {

    LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();

    public void helper(TreeNode node, int level) {

        /* Start current level */
        if (result.size() == level)
            result.add(new LinkedList<Integer>());

        /* Fulfill current level */
        result.get(level).add(node.val);

        /* Process child nodes fro the next level */
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null)
            return result;

        helper(root, 0);

        return result;
    }
}