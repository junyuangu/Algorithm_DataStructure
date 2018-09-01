/*
 * 397. Longest Continuous Increasing Subsequence
Description
Give an integer array，find the longest increasing continuous subsequence in this array.

An increasing continuous subsequence:

Can be from right to left or from left to right.
Indices of the integers in the subsequence should be continuous.

Example
For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.

For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.

Challenge
O(n) time and O(1) extra space.
 */

public class Solution {
    /**
     * @param A: An array of Integer
     * @return: an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] af = new int[2];
        int[] df = new int[2];
        af[0] = 1;
        df[0] = 1;
        
        int maxLen = 1;
        for (int i = 1; i < A.length; ++i) {
            if (A[i] > A[i - 1]) {
                df[i % 2] = 1;
                af[i % 2] = af[(i - 1) % 2] + 1;
            } else if (A[i] < A[i - 1])  {
                af[i % 2] = 1;
                df[i % 2] = df[(i - 1) % 2] + 1;
            } else {
                af[i % 2] = 1;
                df[i % 2] = 1;
            }
            int max = Math.max(af[i % 2], df[i % 2]);
            if (max > maxLen) {
                maxLen = max;
            }
        }
        
        return maxLen;
    }
}