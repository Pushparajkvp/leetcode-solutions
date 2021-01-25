/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.

*/
class Solution {
    
    PriorityQueue<Integer> heap = null;
    
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return 0;
        
        heap = new PriorityQueue<>(nums.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer one, Integer two) { 
                return two - one;
            }
        });
        
        for(int it=0; it<nums.length; it++)
            heap.add(nums[it]);
        
        int result = -1;
        for(int it=0; it<k; it++)
            result = heap.poll();
        
        return result;
    }
}