/*
 * 254. Drop Eggs
Description
There is a building of n floors. If an egg drops from the k th floor or above, it will break. If it's dropped from any floor below, it will not break.

You're given two eggs, Find k while minimize the number of drops for the worst case. Return the number of drops in the worst case.

Clarification
For n = 10, a naive way to find k is drop egg from 1st floor, 2nd floor ... kth floor. But in this worst case (k = 10), you have to drop 10 times.

Notice that you have two eggs, so you can drop at 4th, 7th & 9th floor, in the worst case (for example, k = 9) you have to drop 4 times.

Example
Given n = 10, return 4.
Given n = 100, return 14.
*/

public class Solution {
    /**
     * @param n: An integer
     * @return: The sum of a and b
     * When the 1st egg is broken, we need to enumerate every floor from the last time + 1 floor which 1st egg not broken.
     * If the trial floor of 1st egg is decreasing gradually by one each time, 
     * then the trial times of 2nd egg will be decreasing accordingly.
     * Therefore the total trial times of 1st egg and 2nd egg will be controled. 
     * What is the proper floor for 1st egg's 1st trial? X + (X - 1) + (X - 2) + ... + 2 + 1 = n, just compute what is X.
     *
     * Let's take n = 10 for example. If X + (X - 1) + ... + 1 = 10, then X = 4;
     * We start from 4th floor for 1st egg. If it's broken, then we try from 1st/2nd/3rd floor bottom-up using 2nd. The worst case is 
     * from 3rd floor eggs will be broken. The total trial number is 4.
     * If 9th floor is the outcome, we tried 3 times using 1st egg(4, 7, 9). Thus we try 8th floor with 2nd egg. All trials is 4.
     * Because the interval is decreasing gradually when 1st egg climbs up to higher floor. The total trials will be under control.
     */
    public int dropEggs(int n) {
        // write your code here
        long dropSum = 0;
        for (int i = 1; ; ++i) {
            dropSum += (long)i;
            if (dropSum < (long)n) {
                continue;
            }
            return i;
        }
    }
}


