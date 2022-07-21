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

/* Iterating method using Stack, inorder traversal */
/* Time: O(n), Space: O(n) */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {

        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> result = new LinkedList<>();

        TreeNode currentNode = root;
        while (currentNode != null || !stack.isEmpty()) {

            while (currentNode != null) {
                stack.add(currentNode);
                currentNode = currentNode.left;
            }

            currentNode = stack.pollLast();
            result.add(currentNode.val);
            currentNode = currentNode.right;
        }

        return result;
    }
}