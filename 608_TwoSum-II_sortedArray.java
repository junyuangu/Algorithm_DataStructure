/*
 * 608. Two Sum II - Input array is sorted
Description
Given an array of integers that is already sorted in ascending order, 
find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

Info
You may assume that each input would have exactly one solution.

Example
Given nums = [2, 7, 11, 15], target = 9
return [1, 2]

 */
public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: target = nums[index1] + nums[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    // 1. O(nlogn) 
    public int[] twoSum(int[] nums, int target) {
        // write your code here
        //int[] result = new int[2]; new int[2] 是[0, 0];
        if (nums == null || nums.length == 0) {
            return new int[] {}; // 是array长度为0;
        }
        
        for (int i = 0; i < nums.length; ++i) {
            int curt = nums[i];
            if (i == nums.length - 1) {
                break;
            }
            int index = binarySearch(nums, i + 1, nums.length - 1, target - curt);
            if (index == -1) {
                continue;
            }
            return new int[] {i + 1, index + 1};
        }
        return new int[] {};
    }
    
    private int binarySearch(int[] nums, int start, int end, int target) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}