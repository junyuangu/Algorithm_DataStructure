/*
900. Closest Binary Search Tree Value
Description
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.

Example
Given root = {1}, target = 4.428571, return 1.
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
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return 0;
        }
        double closest = Double.MAX_VALUE;
        return traversal(root, target, closest, root.val);
    }
    private int traversal(TreeNode root, double target, double closest, int value) {
        if (root == null) {
            return value;
        }
        double curtClose = Math.abs(target - root.val);
        if ( curtClose < closest ) {
            closest = curtClose;
            value = root.val;
        }
        if (target > root.val) {
            int right = traversal(root.right, target, closest, value);
            double rightClose = Math.abs(target - right);
            if (rightClose < curtClose) {
                return right;
            }
        }
        
        if (target < root.val) {
            int left = traversal(root.left, target, closest, value);
            double leftClose = Math.abs(target - left);
            if (leftClose < curtClose) {
                return left;
            }
        }
        
        return root.val;
    }
    /* 
    // 没有利用BST特性的D & C + traversal
    public int closestValue(TreeNode root, double target) {
        // write your code here
        if (root == null) {
            return 0;
        }
        double closest = Double.MAX_VALUE;
        return dfsHelper(root, target, closest, root.val);
    }
    
    private int dfsHelper(TreeNode root, double target, double closest, int value) {
        if (root == null) {
            return value;
        }
        
        double curtClose = Math.abs(target - root.val);
        if ( curtClose < closest ) {
            closest = curtClose;
            value = root.val;
        }
        int left = dfsHelper(root.left, target, closest, value);
        int right = dfsHelper(root.right, target, closest, value);
        
        double leftClose = Math.abs(target - left);
        double rightClose = Math.abs(target - right);
        if( leftClose < curtClose && leftClose < rightClose ) {
            return left;
        }
        if (rightClose < curtClose && rightClose < leftClose) {
            return right;
        }
        return root.val;
    } */
}