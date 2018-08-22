/*
 * 586. Sqrt(x) II
Description
Implement double sqrt(double x) and x >= 0.

Compute and return the square root of x.

Example
Given n = 2 return 1.41421356
 */

public class Solution {
    /*
     * @param x: a double
     * @return: the square root of x
     */
    // O(log(Range)) - 二分法之二分答案. 
    public double sqrt(double x) {
        // write your code here
        if (x == 0.0) {
            return 0.0;
        }
        
        double start = 1.0, end = x;
        // Notice: x < 1.0 的情况
        if (x < 1.0) {
            start = x;
            end = 1.0;
        }
        // Notice： 1e-10是跑test case的实验值， 未必一定是这个值。
        while (end - start > 1e-10) {
            double mid = start + (end - start) / 2;
            if (mid * mid < x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (end * end <= x) {
            return end;
        }
        return start;
    }
}