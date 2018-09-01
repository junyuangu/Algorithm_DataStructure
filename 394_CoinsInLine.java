/*
 * 394. Coins in a Line
Description
There are n coins in a line. Two players take turns to take one or two coins from right side until there are no more coins left. The player who take the last coin wins.

Could you please decide the first play will win or lose?

Example
n = 1, return true.
n = 2, return true.
n = 3, return false.
n = 4, return true.
n = 5, return true.

Challenge
O(n) time and O(1) memory

 */

public class Solution {
    /**
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        // f[i] denotes if there're i coins left, whether the first play will win.
        boolean[] f = new boolean[3];
        
        f[0] = false;
        if (n == 0) {
            return f[0];
        }
        if (n < 3) {
            return true;
        }
        f[1] = true;
        f[2] = true;
        for (int i = 3; i <= n; ++i) {
            f[i % 3] = !f[(i - 1) % 3] || !f[(i - 2) % 3];
        }
        
        return f[n % 3];
    }
}