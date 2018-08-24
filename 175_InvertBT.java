/*
 * 175. Invert Binary Tree
Description
Invert a binary tree.

Example
  1         1
 / \       / \
2   3  => 3   2
   /       \
  4         4

Challenge
Do it in recursion is acceptable, can you do it without recursion?

*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    // non-recursive in-order traversal
    public void invertBinaryTree(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> stack = new Stack();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        
        while (!stack.isEmpty()) {
            TreeNode curt = stack.pop();
            TreeNode tmp = curt.right;
            if (tmp != null) {
                stack.push(tmp);
            }
            
            curt.right = curt.left;
            curt.left = tmp;
        }
    }
    
    /*
    // pre-order Recursion
    public void invertBinaryTree(TreeNode root) {
        if (root == null) {
            return;
        }    
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertBinaryTree(root.left);
        invertBinaryTree(root.right);
    } */
    
    /*
    // Divide & Conquer
    public void invertBinaryTree(TreeNode root) {
        // write your code here
        postorderInvert(root);
    }
    
    public TreeNode postorderInvert(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        TreeNode left = postorderInvert(root.left);
        TreeNode right = postorderInvert(root.right);
        root.left = right;
        root.right = left;
        return root;
    } */
}