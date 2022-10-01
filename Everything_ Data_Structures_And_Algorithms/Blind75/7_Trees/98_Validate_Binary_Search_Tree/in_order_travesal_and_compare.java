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
Valid Binary Search Tree

Given a binary tree root node, check if the tree is a valid binary search tree.

Input: Node in a Binary Tree
Output: Boolean
5
           5
        /    \
      2       7
             /  \
             4   9

In order [2,5,4,7,9]
need a variable to store prev val: 5

           5
        /    \
      2       7
             /  \
            6    9

In order [2,5,6,7,9]
[5,2,7,-1,01,6,9]
need a variable to store prev val: 7

time: O(n)
space:O(H)



          In order travesal

        need a stack to store node
        have a curernt node variable
        while loop (as long as current node is not null and stack is not empty)
            while (left node is not null)
                go to the left child.
                push to node stack
            pop off first node
            compare current node value with prev node val
                if prev node val >= current node
                  return false
            prevNode.val = node poped off
            go to right child
      return true
  */

class Solution {
    public boolean isValidBST(TreeNode root) {
        //LinkedList<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;
        Integer prevNodeVal = null;

        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            //result.add(currentNode.val);
            if (prevNodeVal != null && currentNode.val <= prevNodeVal) {
                return false;
            }
            prevNodeVal = currentNode.val;
            currentNode = currentNode.right;
        }
        //return result;
        return true;
    }
}