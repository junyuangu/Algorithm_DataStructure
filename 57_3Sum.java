/*
 * 57. 3Sum
Description
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Info:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.

Example
For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:

(-1, 0, 1)
(-1, -1, 2)

*/

public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // write your code here
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ++i) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int target = 0 - nums[i];
            List<Integer> tmp = searchTarget(nums, i + 1, nums.length - 1, target);
            if (tmp == null) {
                continue;
            }
            for (int j = 0; j < tmp.size(); j += 2) {
                List<Integer> triplet = new ArrayList<>();
                triplet.add(nums[i]);
                triplet.add(tmp.get(j));
                triplet.add(tmp.get(j + 1));
                results.add(triplet);
            }
        }
        return results;
    }
    
    private List<Integer> searchTarget(int[] nums, int start, int end, int target) {
        List<Integer> ans = new ArrayList<>();
        while (start + 1 < end) {
            if (nums[start] + nums[end] < target) {
                start++;
            } else if (nums[start] + nums[end] > target) {
                end--;
            } else {
                ans.add(nums[start]);
                ans.add(nums[end]);
                start++;
                end--;
                while (start < end && nums[start - 1] == nums[start]) {
                    start++;
                }
                while (start < end && nums[end + 1] == nums[end]) {
                    end--;
                }
            }
        }

        if (start < end && nums[start] + nums[end] == target) {
            ans.add(nums[start]);
            ans.add(nums[end]);
        }
        return ans;
    }
}