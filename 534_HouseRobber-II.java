/*
 * 534. House Robber II
Description
After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Info:
This is an extension of House Robber.

Example
nums = [3,6,4], return 6
 */

public class Solution {
    /**
     * @param nums: An array of non-negative integers.
     * @return: The maximum amount of money you can rob tonight
     */
    // O(n) time + O(1) space
    public int houseRobber2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        
        return Math.max(robber(nums, 0, nums.length - 2), 
                        robber(nums, 1, nums.length - 1));
    }
    
    private int robber(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        if (start + 1 == end) {
            return Math.max(nums[start], nums[end]);
        }
        
        int[] f = new int[2];
        f[start % 2] = nums[start];
        f[(start + 1) % 2] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i < end + 1; ++i) {
            f[i % 2] = Math.max(f[(i - 1) % 2], 
                                f[(i - 2) % 2] + nums[i]);
        }
        
        return f[end % 2];
    }
}