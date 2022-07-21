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

/* Iterations approach, preorder traversal */
/* Top->Bottom and Left->Right */
/* start from the root and then at each iteration pop the current node out of the stack and push its child nodes */
/* Time: O(n), Space: O(n) */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {

        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();

        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {

            TreeNode node = stack.pollLast();
            output.add(node.val);

            if (node.right != null) {
                stack.add(node.right);
            }

            if (node.left != null) {
                stack.add(node.left);
            }
        }

        return output;
    }
}