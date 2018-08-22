/*
 * 437. Copy Books
Description
Given n books and the ith book has A[i] pages. You are given k people to copy the n books.

n books list in a row and each person can claim a continous range of the n books. 
For example one copier can copy the books from ith to jth continously, but he can not copy the 1st book, 2nd book and 4th book 
(without 3rd book).

They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. 
What's the best strategy to assign books so that the slowest copier can finish at earliest time?

Example
Given array A = [3,2,4], k = 2.

Return 5( First person spends 5 minutes to copy book 1 and book 2 and second person spends 4 minutes to copy book 3. )

Challenge
Time Complexity: O(nk)

 */

public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    // 1. Binary Search the answer. O(n log Range), where Range is the sum of all pages.
    public int copyBooks(int[] pages, int k) {
        // write your code here
        if (pages == null || pages.length == 0) {
            return 0;
        }
        
        int sum = 0;
        for (int page : pages) {
            sum += page;
        }
        
        int start = 1, end = sum;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (checkCopiers(pages, mid, k)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (checkCopiers(pages, start, k)) {
            return start;
        }
        return end;
    }
    
    private boolean checkCopiers(int[] pages, int time, int k) {
        int count = 0;
        int left = 0;
        for (int curt : pages) {
            if (curt > time) {
                return false;
            }
            if (curt > left) {
                count++;
                left = time;
            }
            left -= curt;
        }
        
        return count <= k;
    }
        
}