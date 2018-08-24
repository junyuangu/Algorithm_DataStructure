/*
 * 75. Find Peak Element
Description
There is an integer array which has the following features:

The numbers in adjacent positions are different.
A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
We define a position P is a peak if:

A[P] > A[P-1] && A[P] > A[P+1]
Find a peak element in this array. Return the index of the peak.

INFO:
It's guaranteed the array has at least one peak.
The array may contain multiple peeks, find any of them.
The array has at least 3 numbers in it.

Example
Given [1, 2, 1, 3, 4, 5, 7, 6]

Return index 1 (which is number 2) or 6 (which is number 7)

Challenge
Time complexity O(logN)


 */

public class Solution {
    /*
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    // 二分之Half Half, 选择有答案的一半，缩小范围。
    public int findPeak(int[] A) {
        // write your code here
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            // if (mid == 0 || mid == A.length - 1) {
            //     break;
            // }
            // if (A[mid] > A[mid + 1] && A[mid] > A[mid - 1]) {
            //     return mid;
            // } else 
            if (A[mid] < A[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[start] > A[end]) {
            return start;
        }
        return end;
    }
}