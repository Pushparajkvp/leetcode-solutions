/*
Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

 

Example 1:

Input: nums = [1,2,2,3,1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:

Input: nums = [1,2,2,3,1,4,2]
Output: 6
Explanation: 
The degree is 3 because the element 2 is repeated 3 times.
So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
 

Constraints:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
*/
class Solution {
    private class Span {
        public int start, end;
        
        public Span(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        HashMap<Integer, Span> span = new HashMap<>();
        
        int maxCount = 0;
        
        for(int it=0; it<nums.length; it++) {
            
            int count = -1;
            if(countMap.containsKey(nums[it])) {
                count = countMap.get(nums[it]) + 1;
                countMap.put(nums[it], count);
                Span temp = span.get(nums[it]);
                temp.end = it;
                //span.put(nums[it], temp);
            } else {
                count = 1;
                countMap.put(nums[it], count);
                span.put(nums[it], new Span(it, it));
            }
            maxCount = Math.max(count, maxCount);
        }
        
        int result = nums.length;
        for(Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if(entry.getValue() == maxCount) {
                Span curr = span.get(entry.getKey());
                result = Math.min(result, curr.end - curr.start + 1);
            }
        }
        return result;
    }
}