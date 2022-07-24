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
/* Time: O(n), Space: O(n) */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {

        LinkedList<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        if (root == null)
            return result;

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pollLast();
            result.addFirst(currentNode.val);

            if (currentNode.left != null)
                stack.add(currentNode.left);
            if (currentNode.right != null)
                stack.add(currentNode.right);
        }

        return result;
    }
}