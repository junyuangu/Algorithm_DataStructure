public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
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
    
    private boolean checkCopiers(int[] pages, int copiers, int k) {
        int count = 0;
        int left = 0;
        for (int curt : pages) {
            if (curt > copiers) {
                return false;
            }
            if (curt > left) {
                count++;
                left = copiers;
            }
            left -= curt;
        }
        
        return count <= k;
    }
        
}