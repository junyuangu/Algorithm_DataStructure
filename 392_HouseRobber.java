/*
 * 392. House Robber
Description
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example
Given [3, 8, 4], return 8.

Challenge
O(n) time and O(1) memory.
 */

public class Solution {
    /**
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    // Dynamic Programming using rotated array
    // O(n) time, O(1) space
    public long houseRobber(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0L;
        } 
        
        // store the max stolen until the i-th house.
        long[] f = new long[3];
        // init f array.
        f[0] = 0;
        f[1] = nums[0];
        
        for (int i = 2; i < nums.length + 1; ++i) {
            f[i % 3] = Math.max(f[(i - 1) % 3], 
                                f[(i - 2) % 3] + nums[i - 1]);
        }
        
        return f[nums.length % 3];
    }
}
