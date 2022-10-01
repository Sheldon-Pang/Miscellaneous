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
[4,2,7,1,3,6,9] return -> [[4],[2,7],[1,3,6,9]]

                    4
                 /      \
                2          7
            /     \      /   \
           1       3    6     9     front[1,3,6,9]
                                    size = 4
                                    current node = 4
                                    reuslt 0[4],1[2,7],2[]
                                    current level = 2

           -use queue to keep track of node

           -add root to queue
           -int variable level to keep track of current level(0)

           -while(queue is not empty)
                -create a list to sotre result for current level
                -vriable size to store queue size for current level
                -for loop iterate thorugh size variable
                    -current node euqal to queue remove()
                    -add to current level of result
                    -if left node is not null
                        -add node to queue
                    -if right node is not null
                        -add node to queue
                -increment level

            return result

           Time:  O(n)
           Space: O(n), max number of nodes on a level

*/
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new LinkedList<List<Integer>>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();

        if (root == null) {
            return result;
        }

        nodeQueue.add(root);
        int level = 0;

        while (!nodeQueue.isEmpty()) {
            result.add(new LinkedList<Integer>());
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = nodeQueue.remove();
                result.get(level).add(currentNode.val);
                if(currentNode.left != null) {
                    nodeQueue.add(currentNode.left);
                }
                if(currentNode.right != null) {
                    nodeQueue.add(currentNode.right);
                }
            }
            level++;
        }

        return result;
    }
}