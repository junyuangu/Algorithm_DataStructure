/*
领扣 597. Subtree with Maximum Average
Description
Given a binary tree, find the subtree with maximum average. Return the root of the subtree.

Example
Given a binary tree:

     1
   /   \
 -5     11
 / \   /  \
1   2 4    -2 
return the node 11.
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
// 2. pure Divide & Conquer
public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    class ResultType {
        int size, sum;
        TreeNode subtree;
        ResultType(int size, int sum, TreeNode subtree) {
            this.size = size;
            this.sum = sum;
            this.subtree = subtree;
        }
    }
    
    ResultType maxResult = null;
    // add the return Type into the ResultType
    public TreeNode findSubtree2(TreeNode root) {
        maxResult = new ResultType(1, Integer.MIN_VALUE, null);
        findMaximum(root);
        return maxResult.subtree;
    }
    
    private ResultType findMaximum(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, null);
        }
        
        ResultType left = findMaximum(root.left);
        ResultType right = findMaximum(root.right);
        ResultType result = new ResultType(
            left.size + right.size + 1,
            left.sum + right.sum + root.val,
            root
            );
        if (maxResult.subtree == null 
            || result.sum * maxResult.size > result.size * maxResult.sum) {
            maxResult = result;
        }
        return result;
    }
}


//--------------------------------------------------------------------------------
class ResultType {
    int size, sum;
    ResultType(int size, int sum) {
        this.size = size;
        this.sum = sum;
    }
}
public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
     
    // 1. D & C Traversal 
    private TreeNode subtree = null;
    private ResultType maxResult = null;
    
    public TreeNode findSubtree2(TreeNode root) {
        maxResult = new ResultType(1, Integer.MIN_VALUE);
        postTraversal(root);
        
        return subtree;
    }
    // Divide & Conquer
    private ResultType postTraversal(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }
        ResultType left = postTraversal(root.left);
        ResultType right = postTraversal(root.right);
        ResultType curtResult = new ResultType(
            left.size + right.size + 1, 
            left.sum + right.sum + root.val
            );
        
        if (subtree == null ||
          curtResult.sum * maxResult.size > curtResult.size * maxResult.sum) {
            maxResult = curtResult;
            subtree = root;
        }
        return curtResult;
    }
}