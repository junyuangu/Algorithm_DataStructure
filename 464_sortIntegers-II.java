/*
 * 464. Sort Integers II
Description
Given an integer array, sort it in ascending order. Use quick sort, merge sort, heap sort or any O(nlogn) algorithm.

Example
Given [3, 2, 1, 4, 5], return [1, 2, 3, 4, 5].
 */


public class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    // 3. Heap Sort, O(nlogn) time
    public void sortIntegers2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        // build max heap
        int len = nums.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(nums, len, i);
        }
        
        // pop maximum from the heap and add max to the end of the heap, 
        // then heap size - 1;
        for (int i = len - 1; i >= 0; i--) {
            int popMax = nums[0];
            nums[0] = nums[i];
            nums[i] = popMax;
            
            heapify(nums, i, 0);
        }
    }
    
    // max heap
    private void heapify(int[] nums, int size, int father) {
        while (father < size) {
            int max = father;
            int leftSon = father * 2 + 1;
            int rightSon = father * 2 + 2;
            if (leftSon < size && nums[leftSon] > nums[max]) {
               max = leftSon;
            }
            if (rightSon < size && nums[rightSon] > nums[max]) {
               max = rightSon;
            }
            if (max != father) {
               int tmp = nums[father];
               nums[father] = nums[max];
               nums[max] = tmp;
               
               father = max;
            } else {
                break;
            } 
        }
    }


    // 2. mergeSort
    public void sortIntegers2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int[] tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, tmp);
    }
    
    private void mergeSort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid, tmp);
        mergeSort(nums, mid + 1, right, tmp);
        
        merge(nums, left, right, tmp);
    }
    
    private void merge(int[] nums, int start, int end, int[] tmp) {
        int mid = (start + end) / 2;
        int left = start, right = mid + 1;
        int index = start;
        while (left <= mid && right <= end ) {
            if (nums[left] < nums[right]) {
                tmp[index++] = nums[left++];
            } else {
                tmp[index++] = nums[right++];
            }
        }
        while (left <= mid) {
            tmp[index++] = nums[left++];
        }
        while (right <= end) {
            tmp[index++] = nums[right++];
        }
        for (int i = start; i <= end; i++) {
            nums[i] = tmp[i];
        }
       
    }

    //1. quickSort - O(nlogn) time, O(1) space.
    public void sortIntegers2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        quickSort(nums, 0, nums.length - 1); 
    }
    
    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        
        int pivot = nums[(start + end) / 2];
        int left = start, right = end;
        // key 1. left <= right, not left < right;
        // cause if left == right, [1, 2] will have unlimited recursion.
        while (left <= right) {
            // 2. ascending sorting，notice: < pivot not <= pivot, 
            // cause: 111110, multi dup numbers(corner case);
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            
            if (left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                //explicitly change indexes.
                left++;
                right--;
            }
        }
        
        // key 3. recursive call, [start ... right | left ... end]
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }
}