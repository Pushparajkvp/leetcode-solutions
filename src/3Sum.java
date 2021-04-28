/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Example 2:

Input: nums = []
Output: []
Example 3:

Input: nums = [0]
Output: []
 

Constraints:

0 <= nums.length <= 3000
-105 <= nums[i] <= 105

*/ 
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        
        List<List<Integer>> result = new ArrayList<>();
        
        for(int it=0; it<nums.length; it++) {
            if(it > 0 && nums[it - 1] == nums[it])
                continue;
            int target = -nums[it];
            int left = it + 1, right = nums.length - 1;
            
            while(left < right) {
                int sum = nums[left] + nums[right];
                
                if(sum == target) {
                    result.add(Arrays.asList(nums[it],nums[left], nums[right]));
                    left++;
                    right--;
                    while(left < right && nums[left] == nums[left-1]) left++;
                    while(left < right && nums[right] == nums[right + 1]) right--;
                } else if(sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
}