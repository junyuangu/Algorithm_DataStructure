public class Solution {
    /*
     * @param nums: an array with positive and negative numbers
     * @param k: an integer
     * @return: the maximum average
     */
    // 二分法之二分答案 O(n log(Range)) time; Range为输入数据中的最小数 到 最大数。
    public double maxAverage(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0 || k == 0) {
            return 0.0;
        }
        // get the range of the maxAverage.
        double lo = Double.MAX_VALUE, hi = Double.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            lo = Math.min(lo, nums[i]);
            hi = Math.max(hi, nums[i]);
        }
        
        while (hi - lo > 1e-6) {
            double mid = lo + (hi - lo) / 2;
            if (checkMean(nums, mid, k)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        if (checkMean(nums, hi, k)) {
            return hi;
        }
        
        return lo;
    }
    
    private boolean checkMean(int[] nums, double mean, int k) {
        double[] preMin = new double[k + 1];  // 类似记忆化搜索的思路（memoization), 以及滚动数组节省空间。
        double[] prefixSum = new double[nums.length + 1]; 
        
        for (int i = 1; i < prefixSum.length; ++i) {
            prefixSum[i] = prefixSum[i - 1] + (double)nums[i - 1] - mean;
            preMin[i % (k + 1)] = Math.min(preMin[(i - 1) % (k + 1)], prefixSum[i]);
            if(i < k) {
                continue;   // 使主要代码不缩进.
            }
            if (prefixSum[i] - preMin[(i - k) % (k + 1)] >= 0) {
                return true;
            } 
        }
        return false;
    }
}