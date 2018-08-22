/*
 * 521. Remove Duplicate Numbers in Array
Description
Given an array of integers, remove the duplicate numbers in it.

You should:

Do it in place in the array.
Move the unique numbers to the front of the array.
Return the total number of the unique numbers.

Info: 
You don't need to keep the original order of the integers.

Example
Given nums = [1,3,1,4,4,2], you should:

Move duplicate integers to the tail of nums => nums = [1,3,4,2,?,?].
Return the number of unique integers in nums => 4.
Actually we don't care about what you place in ?, we only care about the part which has no duplicate integers.

Challenge
Do it in O(n) time complexity.
Do it in O(nlogn) time without extra space.
*/


public class Solution {
    /*
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    //4. O(nlog) time, O(1) space
    public int deduplication(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // O(nlogn)
        Arrays.sort(nums);
        int left = 0, right = 0;
        while(right < nums.length) {
            if (nums[left] != nums[right]) {
                nums[++left] = nums[right];
            } 
            right++;
        }
        return left + 1;
    }
    
    //3. O(n) time, O(n) space, Map.
    public int deduplication(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, Boolean> map = new HashMap<>();
        for (int item : nums) {
            map.put(item, true);
        }
        int index = 0;

        // 这种更简洁
        for (Integer item : map.keySet()) {
            nums[index++] = item;
        } 
        return index;
        
        // 这样也可以
        // for(Map.Entry<Integer, Boolean> entry : map.entrySet()) {
        //     nums[index++] = entry.getKey();
        // }
        // return map.keySet().size();
       
    }
    

    //2. O(n) time, O(n) space, set + 2 ptrs.
    public int deduplication(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> unique = new HashSet<>();
        int left = 0, right = 0;
        while (right < nums.length) {
            if (!unique.contains(nums[right])) {
                swap(nums, left, right);
                unique.add(nums[left]);
                left++;
            } 
            right++;
        }
        return unique.size();
    }
    
    private void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }

    // 1. O(n) time, O(n) space, but keep the original order of the integers.
    public int deduplication(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> dupPtr = new LinkedList<>();
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            if (!visited.contains(nums[i])) {
                visited.add(nums[i]);
                if (dupPtr.size() > 0) {
                    int index = dupPtr.poll();
                    nums[index] = nums[i];
                    dupPtr.offer(i);
                }
            } else {
                dupPtr.offer(i);
            }
        }
        
        return visited.size();
    }
}