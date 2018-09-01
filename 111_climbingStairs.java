/*
 * 111. Climbing Stairs
Description
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example
Given an example n=3 , 1+1+1=2+1=1+2=3

return 3
 */

public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    // O(n) time, O(1) space
    public int climbStairs(int n) {
        int[] f = new int[3]; // f[i] denotes the ways of going to i-th stair.
        f[0] = 0;
        f[1] = 1;
        f[2] = 2;
        
        for (int i = 3; i <= n; ++i) {
            f[i % 3] = f[(i - 1) % 3] + f[(i - 2) % 3];
        }
        return f[n % 3];
    }
}