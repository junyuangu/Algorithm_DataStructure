/*
 * 62. Search in Rotated Sorted Array
Description
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Example
For [4, 5, 1, 2, 3] and target=1, return 2.

For [4, 5, 1, 2, 3] and target=0, return -1.

Challenge
O(logN) time
 */


public class Solution {
    /**
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    // 二分法之Half Half(保留有解的一半)
    public int search(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) {
            return -1;
        }
        
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (A[0] < A[mid]) {
                if (target <= A[mid] && A[0] <= target) {
                    end = mid;
                } else { 
                    start = mid;
                }
            } else {
                if (target >= A[mid] && target < A[0]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (A[start] == target) {
            return start;
        }
        if (A[end] == target) {
            return end;
        }
        return -1;
    }
    
}

