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
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    private int result = 0;
    private int levelIndex = 0;
    public int kthSmallest(TreeNode root, int k) {
        traversal(root, k);
        return result;
    }
    
    private void traversal(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        
        traversal(root.left, k);
        levelIndex++;
        if (levelIndex == k) {
            result = root.val;
        }
        traversal(root.right, k);
    }
    
    /*
    //1.inorder Traverse
    private List<Integer> elements = null;
    public int kthSmallest(TreeNode root, int k) {
        // write your code here
        elements = new ArrayList<Integer>();
        inorderTravers(root, elements);
        return elements.get(k - 1);
    }
    
    private void inorderTravers(TreeNode node, List<Integer> results) {
        if (node == null) {
            return;
        }
        
        inorderTravers(node.left, results);
        results.add(node.val);
        inorderTravers(node.right, results);
    } */
    
}