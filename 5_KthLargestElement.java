/*
 * 5. Kth Largest Element
Description
Find K-th largest element in an array.

Example
In array [9,3,2,4,8], the 3rd largest element is 4.

In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc.

Challenge
O(n) time, O(1) extra memory.

 */

class Solution {
    /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */

    // Recommend
    // 可以不对k进行改动，因为nums这个array总是局部排序。最终nums[k - 1]是正确答案即可。
    public int kthLargestElement(int k, int[] nums) {
        // corner case 
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return -1;
        }
        
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }
    
    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[k];
        }
        
        int pivot = nums[(start + end) / 2];
        int left = start, right = end;
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            while (left <= right && nums[right] < pivot) {
                right--;
            }
            
            if (left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        if (k <= right) {
            return quickSelect(nums, start, right, k);
            
        }
        if (k >= left) {
            return quickSelect(nums, left, end, k);
        }
        return nums[k]; // in case: [start ... right k left ... end]
    }

    //O(n)， 因为每次只需要取一边. n + n/2 + n/4 + ... + 1 = 2n - 1;
    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return -1;
        }
        
        return quickSelect(nums, 0, nums.length - 1, k);
    }
    
    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[start + k - 1];
        }
        
        int pivot = nums[(start + end) / 2];
        int left = start, right = end;
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            while (left <= right && nums[right] < pivot) {
                right--;
            }
            
            if (left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        if (k <= right) {
            quickSelect(nums, start, right, k);
            return nums[start + k - 1]; // 递归的思路
        }
        quickSelect(nums, right + 1, end, k - right - 1); // 没利用返回值。
        return nums[start + k - 1];
    }

    // 3. reference
    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return -1;
        }
        
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }
    
    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        } 
        
        int pivot = nums[(start + end) / 2];
        int left = start, right = end;
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            while (left <= right && nums[right] < pivot) {
                right--;
            }
            
            if (left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        if (start + k <= right) {
            return quickSelect(nums, start, right, k);
        }
        if (start + k >= left) {
            return quickSelect(nums, left, end, k - (left - start));
        }
        
        return nums[right + 1];
    }

}
    

    