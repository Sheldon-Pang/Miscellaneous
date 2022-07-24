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
class Solution {
    private void postorderTraversal(TreeNode root, LinkedList<Integer> result) {
        if (root == null) {
            return;
        }
        postorderTraversal(root.left, result);   // traverse left subtree
        postorderTraversal(root.right, result);  // traverse right subtree
        result.add(root.val);                   // visit the root
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        postorderTraversal(root, result);
        return result;
    }
}