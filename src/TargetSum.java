/*
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:

Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
 

Constraints:

The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
*/
class Solution {
    
    int[][] memo;
    
    public int findTargetSumWays(int[] nums, int S) {
        memo = new int[nums.length][2001];
        
        for(int row=0; row<memo.length; row++)
            Arrays.fill(memo[row], -1);
        
        return count(nums, S, 0, 0);
    }
    
    private int count(int[] nums, int target, int index, int curr) {
        if(index == nums.length) {
            return curr == target ? 1 : 0;
        }
        
        if(memo[index][curr + 1000] != -1)
            return memo[index][curr + 1000];
        
        return memo[index][curr + 1000] = count(nums, target, index+1, curr + nums[index]) +
                count(nums, target, index+1, curr - nums[index]);
    }
}