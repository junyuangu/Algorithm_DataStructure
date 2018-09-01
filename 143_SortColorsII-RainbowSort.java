/*
 * 143. Sort Colors II
Description
Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.

Info:
You are not suppose to use the library's sort function for this problem.
k <= n

Example
Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].

Challenge
A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory. Can you do it without using extra memory?

*/

public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */

    // O(nlogk) time
    public void sortColors2(int[] colors, int k) {
        // write your code here
        if (colors == null || colors.length == 0) {
            return;
        }
        
        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }
    
    private void rainbowSort(int[] colors, 
                                 int start, 
                                 int end, 
                                 int colorFrom,
                                 int colorTo) {
        if (start >= end || colorFrom == colorTo) {
            return;
        }
        
        int colorMid = (colorFrom + colorTo) / 2;
        int left = start, right = end;
        while (left <= right) {
            // <= colorMid， 因为递归的时候左半边是colorFrom, colorMid， 
            // 如果这里没有等号，就会把左半边=colorMid的数交换到右半边
            while (left <= right && colors[left] <= colorMid) {
                left++;
            }
            while (left <= right && colors[right] > colorMid) {
                right--;
            }
            if (left <= right) {
                int tmp = colors[left];
                colors[left] = colors[right];
                colors[right] = tmp;
                left++;
                right--;
            }
        }
        
        rainbowSort(colors, start, right, colorFrom, colorMid);
        rainbowSort(colors, left, end, colorMid + 1, colorTo);
    }
}