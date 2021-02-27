/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

 

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
 

Constraints:

0 <= nums.length <= 104
-109 <= nums[i] <= 109
 

Follow up: Could you implement the O(n) solution?
*/

//O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        
        if(nums.length == 0)
            return 0;
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int it : nums)
            set.add(it);
        
        int max = 0; 
        for(int it=0; it<nums.length; it++) {
            int num = nums[it];
            if(!set.contains(num))
                continue;
            
            int curr = 0, sequence = num+1;
            
            //check forward
            while(set.contains(sequence)) {
                set.remove(sequence);
                sequence++;
                curr++;
            }
            
            sequence = num - 1;
            //check backwards
            while(set.contains(sequence)) {
                set.remove(sequence);
                sequence--;
                curr++;
            }
            
            set.remove(num);
            max = Math.max(curr, max);
        }
        
        return max + 1;
    }
}

//O(nlogn)
class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0)
            return 0;
        
        Arrays.sort(nums);
        
        int max = 0, curr = 0;
        for(int it=1; it<nums.length; it++) {
            if(nums[it - 1] + 1 == nums[it]) {
                curr++;
                max = Math.max(curr, max);
            } else if(nums[it-1] == nums[it]) {
                continue;
            }else {
                curr=0;
            }
        }
        
        return max + 1;
    }
}