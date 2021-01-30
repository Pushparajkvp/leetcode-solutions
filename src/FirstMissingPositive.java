/* 
Given an unsorted integer array nums, find the smallest missing positive integer.

 

Example 1:

Input: nums = [1,2,0]
Output: 3
Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Example 3:

Input: nums = [7,8,9,11,12]
Output: 1
 

Constraints:

0 <= nums.length <= 300
-231 <= nums[i] <= 231 - 1
 

Follow up: Could you implement an algorithm that runs in O(n) time and uses constant extra space?
*/
class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums.length == 0)
            return 1;
        boolean foundOne = false;
        for(int it=0; it<nums.length; it++) {
            if(nums[it] == 1) {
                foundOne = true;
            }
            
            if(nums[it] > nums.length || nums[it] <= 0)
                nums[it] = 1;
        }
        
        if(!foundOne) return 1;
        
        for(int it=0; it<nums.length; it++) {
            int val = Math.abs(nums[it]) - 1;
            if(nums[val] > 0)
                nums[val] = -nums[val];
        }
        int it = 0;
        for(; it<nums.length; it++) {
            if(nums[it] > 0)
                break;   
        }
        return it + 1;
    }
}