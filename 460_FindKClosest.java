/*
 * 460. Find K Closest Elements
Description
Given a target number, a non-negative integer k and an integer array A sorted in ascending order, 
find the k closest numbers to target in A, sorted in ascending order by the difference between the number and target. 
Otherwise, sorted in ascending order by number if the difference is same.

Notice: 
The value k is a non-negative integer and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 10^4
Absolute value of elements in the array and x will not exceed 10^4

Example
Given A = [1, 2, 3], target = 2 and k = 3, return [2, 1, 3].
Given A = [1, 4, 6, 8], target = 3 and k = 3, return [4, 1, 6].

Challenge
O(logn + k) time complexity.

 */


public class Solution {
    /**
     * @param A: an integer array
     * @param target: An integer
     * @param k: An integer
     * @return: an integer array
     */

    // 18.8.13: 先二分法找到最接近的位置，再双指针法找k - 1个数。
    // Time Complexity: O(logn + k);
    // 缺点： 1. 函数式编程做的不好，主要程序放入了子程序，第一步却没有单独作method。
    //       2. 当k > A.length时， 程序是有问题的。Runtime Error.
    public int[] kClosestNumbers(int[] A, int target, int k) {
        int[] result = new int[k];
        // corner case
        if (A == null || A.length == 0 || k == 0) {
            return result;
        }
        
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < target) {
                start = mid;
            } else if (A[mid] == target) {
                return findKClosest(A, result, target, k, mid);
            } else {
                end = mid;
            }
        }
        if (A[end] == target) {
            return findKClosest(A, result, target, k, end);
        } 
        return findKClosest(A, result, target, k, start);
    }
    
    private int[] findKClosest(int[] A, int[] result, int target, int k, int mid) {
        int startIndex = 0;
        int left = mid, right = mid + 1;
        while(left >= 0 && right < A.length && startIndex < k) {
            if (Math.abs(A[left] - target) < Math.abs(A[right] - target)) {
                result[startIndex] = A[left];
                startIndex++;
                left--;
            } else if (Math.abs(A[left] - target) > Math.abs(A[right] - target)) {
                result[startIndex] = A[right];
                startIndex++;
                right++;
            } else { // diff is the same
                if (A[left] < A[right]) {
                    result[startIndex++] = A[left];
                    left--;
                } else {
                    result[startIndex++] = A[right];
                    right++;
                }
                
            }
        }
        while (startIndex < k && left == -1) {
            result[startIndex++] = A[right++];
        }
        while (startIndex < k && right == A.length) {
            result[startIndex++] = A[left--];
        }
        return result;
    }
}