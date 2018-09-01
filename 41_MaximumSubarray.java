/*
 * 41. Maximum Subarray
Description
Given an array of integers, find a contiguous subarray which has the largest sum.

Example
Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6.

Challenge
Can you do it in time complexity O(n)?

 */

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // write your code here
        if (nums.length == 1) {
            return nums[0];
        }
        
        int n = nums.length;
        int[][] f = new int[n][2];
        f[0][0] = nums[0];
        
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                if (i == j) {
                    f[i][j % 2] = nums[i];
                } else {
                    f[i][j % 2] = f[i][(j - 1) % 2] + nums[j];
                }
                
                if (f[i][j % 2] > maxSum) {
                    maxSum = f[i][j % 2];
                }
            }
        }
        return maxSum;
    }
}