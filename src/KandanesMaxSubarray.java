/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Example 2:

Input: nums = [1]
Output: 1
Example 3:

Input: nums = [0]
Output: 0
Example 4:

Input: nums = [-1]
Output: -1
Example 5:

Input: nums = [-100000]
Output: -100000
 

Constraints:

1 <= nums.length <= 3 * 104
-105 <= nums[i] <= 105
 

Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length, maxSoFar = nums[0]; 
        int maxEndingHere = maxSoFar < 0 ? 0 : maxSoFar;
        int startIndex = 0;
        
        for(int it=1; it<n; it++) {
            int sum = maxEndingHere + nums[it];
            if(sum < 0) {
                startIndex = it + 1;
                maxEndingHere = 0;
            } else {
                maxEndingHere = sum;
            }
            
            if(maxSoFar < sum) {
                maxSoFar = sum;
                System.out.println(startIndex + " " + it);
            }
        }
        return maxSoFar;
    }
}