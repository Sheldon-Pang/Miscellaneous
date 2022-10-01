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
    public int maxDepth(TreeNode root) {

        int depth = 0, curDepth = 0;
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> depthStack = new LinkedList<>();

        if (root == null) {
            return 0;
        }

        stack.add(root);
        depthStack.add(1);

        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pollLast();
            curDepth = depthStack.pollLast();
            if (curNode != null) {
                depth = Math.max(depth, curDepth);
                stack.add(curNode.left);
                stack.add(curNode.right);
                depthStack.add(curDepth + 1);
                depthStack.add(curDepth + 1);
            }
        }

        return depth;
    }
}