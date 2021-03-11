/*
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

 

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]
Example 3:

Input: nums = [1,-1], k = 1
Output: [1,-1]
Example 4:

Input: nums = [9,11], k = 2
Output: [11]
Example 5:

Input: nums = [4,-2], k = 2
Output: [4]
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length

*/
class Solution {
    
    Deque<Integer> queue = new LinkedList<Integer>();
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n == 0 || k == 0) return new int[0];
        if(k == 1) return nums;
        
        int maxIndex = 0;
        for(int it=0; it<k; it++) {
            if(!queue.isEmpty() && queue.getFirst() == it - k)
                queue.removeFirst();
            
            while(!queue.isEmpty() && nums[it] > nums[queue.getLast()])
                queue.removeLast();
            
            queue.addLast(it);
            
            if(nums[it] > nums[maxIndex]) maxIndex = it;
        }
        
        int[] result = new int[n -k + 1];
        result[0] = nums[maxIndex];
        
        for(int it=k; it<n; it++) {
            if(!queue.isEmpty() && queue.getFirst() == it - k)
                queue.removeFirst();
            
            while(!queue.isEmpty() && nums[it] > nums[queue.getLast()])
                queue.removeLast();
            
            queue.addLast(it);
            result[it - k + 1] = nums[queue.getFirst()];
        }
        return result;
    }
}