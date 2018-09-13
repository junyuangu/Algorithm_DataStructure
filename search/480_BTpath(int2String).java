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
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // write your code here
        List<String> result = new ArrayList();
        if (root == null) {
            return result;
        }
        
        dfs(root, result, String.valueOf(root.val));
        return result;
    }
    
    private void dfs(TreeNode root, List<String> list, String subpath) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list.add(subpath);
        }
        if (root.left != null) {
            dfs(root.left, list, subpath + "->" + String.valueOf(root.left.val));
        }
        if (root.right != null) {
            dfs(root.right, list, subpath + "->" + String.valueOf(root.right.val));
        }
        
    }
}