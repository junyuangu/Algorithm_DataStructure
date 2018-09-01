/*
 * 191. Maximum Product Subarray
Description
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example
For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.

 */

public class Solution {
    /**
     * @param nums: An array of integers
     * @return: An integer
     */
    // DP: O(n^2) time, O(1) space
    public int maxProduct(int[] nums) {
        int[] f = new int[2];
        int maxP = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i; j < nums.length; ++j) {
                f[j % 2] = j > i ? f[(j - 1) % 2] * nums[j] : nums[j];
                maxP = Math.max(maxP, f[j % 2]);
            }
        }
        
        return maxP;
    }
}