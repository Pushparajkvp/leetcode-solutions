/*
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]
 

Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
*/
class Solution {
    private void backtrack(int[] nums, int start, ArrayList<List<Integer>> result) {
        
        if(start == nums.length - 1) {
            ArrayList<Integer> list = new ArrayList<>();
            for(int n : nums){
                list.add(n);
            }
            result.add(list);
            return;
        }
        
        for(int it=start; it<nums.length; it++) {
            
            swap(nums, start, it);
            backtrack(nums, start+1, result);
            swap(nums, start, it);
        }
    }
    
    private void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    
    public List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, result);
        return result;
    }
}