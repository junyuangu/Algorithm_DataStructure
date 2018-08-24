/*
 * 891. Sum of Subsequence Widths
Description:
Given an array of integers A, consider all non-empty subsequences of A.
For any sequence S, let the width of S be the difference between the maximum and minimum element of S.
Return the sum of the widths of all subsequences of A. 
As the answer may be very large, return the answer modulo 10^9 + 7.

Example 1:

Input: [2,1,3]
Output: 6
Explanation:
Subsequences are [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3].
The corresponding widths are 0, 0, 0, 1, 1, 2, 2.
The sum of these widths is 6.
 

Note:

1 <= A.length <= 20000
1 <= A[i] <= 20000

 */

// Mathematic
//Time Complexity: O(NlogN), where NN is the length of A.
//Space Complexity: O(N), the space used by pow2
class Solution {
    public int sumSubseqWidths(int[] A) {
        int MOD = 1_000_000_007;
        int N = A.length;
        Arrays.sort(A);

        long[] pow2 = new long[N];
        pow2[0] = 1;
        for (int i = 1; i < N; ++i)
            pow2[i] = pow2[i-1] * 2 % MOD;

        long ans = 0;
        for (int i = 0; i < N; ++i) {
            ans += (pow2[i] - 1) * A[i] % MOD;
            ans -= (pow2[N-1-i] - 1) * A[i] % MOD;
            ans = (ans + MOD) % MOD;
        }

        return (int) ans;
    }
}



//3. 
class Solution {
    public int sumSubseqWidths(int[] A) {
        Arrays.sort(A);
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            int qi = A.length - i - 1;
            ans *= 2;
            ans %= 1000000007;
            ans += A[qi];
            ans %= 1000000007;
            ans -= A[i];
            ans += 1000000007;
            ans %= 1000000007;
        }
        return ans;
    }
}


//2. 
class Solution {
    public int sumSubseqWidths(int[] a) {
        int mod = 1000000007;
        Arrays.sort(a);
        int n = a.length;
        // a[n - 2] * 1 + a[n - 1] * 2
        long s = 0;
        long num = 0;
        long ans = 0;
        for (int i = n - 1; i >= 1; i--) {
        	s = s * 2 + a[i];
        	num = num * 2 + 1;
        	
        	s %= mod;
        	num %= mod;
        	ans += s - num * a[i - 1];
        	ans %= mod;
        }
        if(ans < 0) {
        	ans += mod;
        }
        return (int)ans;
    }
}	

//1. DFS - error， TLE 
class Solution {
    private int ans = 0;
    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        dfsHelper(nums, 0, new ArrayList<Integer>());
        return ans;
    }
    
    private void dfsHelper(int[] nums,
                           int startIndex,
                           List<Integer> subsets) {
        if (subsets.size() > 1) {
            // for(int i = 0; i < subsets.size(); ++i) {
            //     System.out.print(subsets.get(i) + ", ");
            // }
            //System.out.println();
            int tmp = (subsets.get(subsets.size() - 1) - subsets.get(0)) % 1000000007;
            ans = (ans + tmp) % 1000000007;
        }
        
        for (int i = startIndex; i < nums.length; i++) {
            subsets.add(nums[i]);
            dfsHelper(nums, i + 1, subsets);
            subsets.remove(subsets.size() - 1);
        }
    }
}