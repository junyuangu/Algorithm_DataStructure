/*
 * 398. Longest Continuous Increasing Subsequence II
 
Description
Give you an integer matrix (with row size n, column size m)，find the longest increasing continuous subsequence in this matrix. (The definition of the longest increasing continuous subsequence here can start at any row or column and go up/down/right/left any direction).

Example
Given a matrix:

[
  [1 ,2 ,3 ,4 ,5],
  [16,17,24,23,6],
  [15,18,25,22,7],
  [14,19,20,21,8],
  [13,12,11,10,9]
]
return 25

Challenge
O(nm) time and memory.

 */

public class Solution {
    /**
     * @param matrix: A 2D-array of integers
     * @return: an integer
     */
    // 1. DP: O(n * m) time, where n denotes row nums of the matrix, while m denotes col nums of the matrix.
    // O(n * m) space.
    private static final int[] deltaX = {1, 0, 0, -1};
    private static final int[] deltaY = {0, 1, -1, 0};
    public int longestContinuousIncreasingSubsequence2(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int n = matrix.length, m = matrix[0].length;
        int[][] memo = new int[n][m];
        
        int longest = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                longest = Math.max(longest, dfs(matrix, memo, i, j));
            }
        }
        return longest;
    }
    
    private int dfs(int[][] matrix,
                    int[][] memo,
                    int x,
                    int y) {
        if (memo[x][y] != 0) {
            return memo[x][y];
        }
        int longest = 1;
        for (int i = 0; i < 4; ++i) {
            int new_x = x + deltaX[i];
            int new_y = y + deltaY[i];
            if (!isValidPos(new_x, new_y, matrix) || matrix[new_x][new_y] <= matrix[x][y]) {
                continue;
            }
            longest = Math.max(longest, dfs(matrix, memo, new_x, new_y) + 1);
        }
        memo[x][y] = longest;
        return longest;
    }
    
    private boolean isValidPos(int x, int y, int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        if (x < 0 || x > n - 1 || y < 0 || y > m - 1) {
            return false;
        }
        return true;
    }
}