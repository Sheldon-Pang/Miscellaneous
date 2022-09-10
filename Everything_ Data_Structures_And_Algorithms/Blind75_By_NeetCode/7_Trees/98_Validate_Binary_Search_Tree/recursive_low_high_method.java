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

/*

[5,1,4,null,null,3,6]

                    5
                  /    \
      high=5     1       4     low=5
                      /    \
    low=5,high=4     3        6   low=4


   The idea is the node.val should never go beyond high and go below low

*/
class Solution {

    public boolean isValidHelper(TreeNode root, Integer low, Integer high) {
        if (root == null) {
            return true;
        }

        if (low != null && root.val <= low) {
            return false;
        } else if (high != null && root.val >= high) {
            return false;
        }

        return isValidHelper(root.right, root.val, high) && isValidHelper(root.left, low, root.val);
    }

    public boolean isValidBST(TreeNode root) {

        return isValidHelper(root, null, null);

    }
}