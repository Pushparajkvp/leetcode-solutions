/*
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

 

Example 1:

Input: nums = [1,3,4,2,2]
Output: 2
Example 2:

Input: nums = [3,1,3,4,2]
Output: 3
Example 3:

Input: nums = [1,1]
Output: 1
Example 4:

Input: nums = [1,1,2]
Output: 1
 

Constraints:

2 <= n <= 3 * 104
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for precisely one integer which appears two or more times.
 

Follow up:

How can we prove that at least one duplicate number must exist in nums?
Can you solve the problem without modifying the array nums?
Can you solve the problem using only constant, O(1) extra space?
Can you solve the problem with runtime complexity less than O(n2)?
*/

//Using tortoise and rabbit stratergy
class Solution {
    public int findDuplicate(int[] nums) {
        int rabbit = nums[0], tortoise = nums[0];
        
        do {
            rabbit = nums[nums[rabbit]];
            tortoise = nums[tortoise];
        } while(rabbit != tortoise);
        
        tortoise = nums[0];
        while(tortoise != rabbit) {
            tortoise = nums[tortoise];
            rabbit = nums[rabbit];
        }
        
        return rabbit;
    }
}

//Using array modification
class Solution {
    public int findDuplicate(int[] nums) {
        
        for(int it=0; it<nums.length; it++) {
            int index = Math.abs(nums[it]) - 1;
            if(nums[index] < 0)
                return index + 1;
            else
                nums[index] = -nums[index];
        }
        return -1;
    }
}