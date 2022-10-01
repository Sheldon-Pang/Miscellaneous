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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        LinkedList<TreeNode> stackP = new LinkedList<>();
        LinkedList<TreeNode> stackQ = new LinkedList<>();

        if (p == null && q == null) {
            return true;
        }

        if (p != null && q == null) {
            return false;
        } else if (p == null && q != null) {
            return false;
        }

        if (p != null && q != null) {
            stackP.add(p);
            stackQ.add(q);

            while (!stackP.isEmpty() && !stackQ.isEmpty()) {
                TreeNode curNodeP = stackP.pollLast();
                TreeNode curNodeQ = stackQ.pollLast();
                if (curNodeP == null && curNodeQ != null) {
                    return false;
                }
                if (curNodeP != null && curNodeQ == null) {
                    return false;
                }
                if (curNodeP != null && curNodeQ != null) {
                    if (curNodeP.val != curNodeQ.val) {
                        return false;
                    }
                    stackP.add(curNodeP.left);
                    stackP.add(curNodeP.right);
                    stackQ.add(curNodeQ.left);
                    stackQ.add(curNodeQ.right);
                }
            }
        }

        return true;
    }
}