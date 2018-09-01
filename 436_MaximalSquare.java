/*
 * 436. Maximal Square
Description
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

Example
For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.

 */

public class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int maxLen = Integer.MIN_VALUE;
        
        int row = matrix.length, col = matrix[0].length;
        int[][] f = new int[2][col];
        //init f state
        for (int i = 0; i < col; ++i) {
            f[0][i] = matrix[0][i];
            if (maxLen < matrix[0][i]) {
                maxLen = matrix[0][i];
            }
        }
        
        for (int i = 1; i < row; ++i) {
            for (int j = 0; j < col;  ++j) {
                if (j == 0) {
                    f[i % 2][0] = matrix[i][0];
                    if (col == 1 && matrix[i][0] > maxLen) {
                        maxLen = matrix[i][0];
                    }
                    continue;
                }
                if (matrix[i][j] == 0) {
                    f[i % 2][j] = 0;
                    continue;
                }
                int min = Math.min(f[(i - 1) % 2][j - 1], f[(i - 1) % 2][j]);
                f[i % 2][j] = Math.min(f[i % 2][j - 1], min) + 1;
                if (f[i % 2][j] > maxLen) {
                    maxLen = f[i % 2][j];
                }
            }
        }
        
        return maxLen * maxLen;
    }
}