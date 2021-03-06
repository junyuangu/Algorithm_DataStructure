/*
 * 31. Partition Array
 Description
Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:

All elements < k are moved to the left
All elements >= k are moved to the right
Return the partitioning index, i.e the first index i nums[i] >= k.

Info:
You should do really partition in array nums instead of just counting the numbers of integers smaller than k.
If all elements in nums are smaller than k, then return nums.length

Example
If nums = [3,2,2,1] and k=2, a valid answer is 1.

Challenge
Can you partition the array in-place and in O(n)?

*/

public class Solution {
    /**
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            while (start <= end && nums[start] < k) {
                start++;
            }
            while (start <= end && nums[end] >= k) {
                end--;
            }
            if(start <= end) {
                swap(nums, start, end);
                start++;
                end--;
            }
        }
        
        if (end >= 0 && nums[end] >= k) {
            return end;
        }
        return start;
    }
    
    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}