/*
 * 183. Wood Cut
Description
Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

Info
You couldn't cut wood into float length.
If you couldn't get >= k pieces, return 0.

Example
For L=[232, 124, 456], k=7, return 114.

Challenge
O(n log Len), where Len is the longest length of the wood.
 */

public class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    //1. binary search the answer, O(n*log(Len)) time, where Len is the longest length of the wood.
    public int woodCut(int[] L, int k) {
        // corner case
        if (L == null || L.length == 0 || k < 0) {
            return 0;
        }
        
        int maxLen = L[0];
        for (int i = 0; i < L.length; ++i) {
            maxLen = maxLen > L[i] ? maxLen : L[i];
        }
        
        int start = 0, end = maxLen;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (checkCuts(L, mid) < k) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (checkCuts(L, end) >= k) {
            return end;
        }
        return start;
    }
    
    private int checkCuts(int[] L, int len) {
        int sum = 0;
        for (int i = 0; i < L.length; ++i) {
            sum += L[i] / len;
        }
        return sum;
    }
}