/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */

public class Solution {
    /*
     * @param root: The root of the tree
     * @param A: node in the tree
     * @param B: node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        // write your code here
        if (root == null || root == A || root == B) {
            return root;
        }
        
        ParentTreeNode leftPtn = lowestCommonAncestorII(root.left, A, B);
        ParentTreeNode rightPtn = lowestCommonAncestorII(root.right, A, B);
        
        if (leftPtn != null && rightPtn != null) {
            return root;
        }
        
        if (leftPtn != null) {
            return leftPtn;
        }
        
        if (rightPtn != null) {
            return rightPtn;
        }
        
        return null;
    }
    
    
}