/*
 * 395. Coins in a Line II
Description
There are n coins with different value in a line. Two players take turns to take one or two coins from left side until there are no more coins left. The player who take the coins with the most value wins.

Could you please decide the first player will win or lose?

Example
Given values array A = [1,2,2], return true.

Given A = [1,2,4], return false.
 */

public class Solution {
    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }
        
        int n = values.length;
        if (n < 3) {
            return true;
        }
        // f[i] denotes the values which first player get when there are n - i coins left. 
        long[] f = new long[2];
        f[(n - 1) % 2] = values[n - 1];
        f[(n - 2) % 2] = values[n - 1] + values[n - 2];
        long sum = values[n - 1] + values[n - 2];
        for(int i = n - 3; i >= 0; i--) {
            sum += values[i];
            f[i % 2] = Math.max(sum - f[(i + 2) % 2], sum - f[(i + 1) % 2]);
        } 
        
        long maxFirst = f[0];
        if (maxFirst + maxFirst > sum) {
            return true;
        }
        return false;
    }
}