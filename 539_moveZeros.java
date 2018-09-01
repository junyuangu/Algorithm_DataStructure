/* 
 * 539. Move Zeroes
Description
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Info
You must do this in-place without making a copy of the array.
Minimize the total number of operations.

Example
Given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

 */

public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void moveZeroes(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return;
        }
        int len = nums.length;
        int zeroP = 0, nzP = 0;
        while (zeroP < len && nzP < len) {
            nzP = zeroP; // update non-zero Pointer, or Time Limit Exceed may occur.
            while (nzP < len && nums[nzP] == 0) {
                nzP++;
            }
            while (zeroP < len && nums[zeroP] != 0) {
                zeroP++;
            }
            if (zeroP < nzP && nzP < len) {
                swap(nums, nzP, zeroP);
            }
        }
    }
    
    private void swap(int[] nums, int index1, int index2) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }
}