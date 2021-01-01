/*
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
*/
class Solution {
    public int[] productExceptSelf(int[] nums) {
        if(nums.length == 0) return new int[0];
        
        int[] res = new int[nums.length];
        
        res[0] = 1;
        
        for(int it=1; it<nums.length; it++) {
            res[it] = res[it-1] * nums[it-1];
        }
        
        int prev = 1;
        for(int it=nums.length - 2; it>=0; it--) {
            prev = prev * nums[it+1];
            res[it] = prev * res[it];
        }
        
        return res;
    }
}