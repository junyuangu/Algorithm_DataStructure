/*
 * 889. Construct Binary Tree from Preorder and Postorder Traversal
User Accepted: 756
User Tried: 990
Total Accepted: 771
Total Submissions: 1731
Difficulty: Medium
Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 

Note:

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.


 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return dfs(pre, post);
    }
    
    TreeNode dfs(int[] pre, int[] post){
    	if(pre.length == 0)
    		return null;
    	
    	assert pre[0] == post[post.length-1];
    	TreeNode me = new TreeNode(pre[0]);
    	if(pre.length >= 2) {
    		for(int i = 0;i < post.length;i++) {
    			if(post[i] == pre[1]) {
    				TreeNode L = dfs(Arrays.copyOfRange(pre, 1, i+2), 
    						Arrays.copyOfRange(post, 0, i+1)
    						);
    				TreeNode R = dfs(Arrays.copyOfRange(pre, i+2, pre.length), 
    						Arrays.copyOfRange(post, i+1, post.length-1)
    						);
    				me.left = L;
    				me.right = R;
    				break;
    			}
    		}
    	}
    	return me;
    }
}	



