/*
You are playing a game involving a circular array of non-zero integers nums. Each nums[i] denotes the number of indices forward/backward you must move if you are located at index i:

If nums[i] is positive, move nums[i] steps forward, and
If nums[i] is negative, move nums[i] steps backward.
Since the array is circular, you may assume that moving forward from the last element puts you on the first element, and moving backwards from the first element puts you on the last element.

A cycle in the array consists of a sequence of indices seq of length k where:

Following the movement rules above results in the repeating index sequence seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
Every nums[seq[j]] is either all positive or all negative.
k > 1
Return true if there is a cycle in nums, or false otherwise.

 

Example 1:

Input: nums = [2,-1,1,2,2]
Output: true
Explanation:
There is a cycle from index 0 -> 2 -> 3 -> 0 -> ...
The cycle's length is 3.
Example 2:

Input: nums = [-1,2]
Output: false
Explanation:
The sequence from index 1 -> 1 -> 1 -> ... is not a cycle because the sequence's length is 1.
By definition the sequence's length must be strictly greater than 1 to be a cycle.
Example 3:

Input: nums = [-2,1,-1,-2,-2]
Output: false
Explanation:
The sequence from index 1 -> 2 -> 1 -> ... is not a cycle because nums[1] is positive, but nums[2] is negative.
Every nums[seq[j]] must be either all positive or all negative.
 

Constraints:

1 <= nums.length <= 5000
-1000 <= nums[i] <= 1000
nums[i] != 0
 

Follow up: Could you solve it in O(n) time complexity and O(1) extra space complexity?
*/

//O(N^2 solution)

class Solution {
    public boolean circularArrayLoop(int[] nums) {
        
        for(int it=0; it<nums.length; it++) {
            int fast = it, slow = it;
            boolean isPositive = nums[it] > 0;
            do {
                fast = nextIndex(nums, isPositive, fast);
                if(fast != -1)
                    fast = nextIndex(nums, isPositive, fast);
                
                slow = nextIndex(nums, isPositive, slow);
            } while(slow != -1 && fast != -1 && fast != slow);
            
            if(slow != -1 && slow == fast)
                return true;
        }
        
        return false;
    }
    
    private int nextIndex(int[] nums, boolean isPositive, int currIndex) {
        
        if(nums[currIndex] < 0 && isPositive)
            return -1;
        if(nums[currIndex] > 0 && !isPositive)
            return -1;
        
        int newIndex = (currIndex + nums[currIndex]) % nums.length;
        if(newIndex < 0)
            newIndex += nums.length;
        
        if(newIndex == currIndex)
            return -1;
        //stem.out.println(nums[currIndex] + " " + currIndex + " " + newIndex);
        return newIndex;
    }
}

//O(N) solution
// Fast slow solution
// 1. check whether all same sign in the loop.
// 2. If slow == fast, there is a loop. Note to check whether the loop only has one element
// 3. If no loop, set all element along the way to 0
// Time complexity: O(N)
// Space complexity: O(1)
class Solution {
    public boolean circularArrayLoop(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        final int N = nums.length;
        for (int i = 0; i < N; i++) {
            int num = nums[i];
            if (num == 0) continue;
            int slow = i, fast = next(nums, i);
            while (sameSign(nums[fast], num) && sameSign(nums[next(nums, fast)], num)) {
                if (slow == fast) {
                    // check whether the loop only has one element
                    if (slow == next(nums, slow)) {
                        break;
                    }
                    return true;
                }
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }
            // loop not found, set all element along the way to 0
            slow = i;
            while (sameSign(nums[slow], num)) {
                int next = next(nums, slow);
                nums[slow] = 0;
                slow = next;
            }
        }
        return false;
    }
    
    private int next(int[] nums, int idx) {
        final int N = nums.length;
        idx = (idx + nums[idx]) % N;
        return idx < 0 ? idx + N : idx;
    }
    
    private boolean sameSign(int a, int b) {
        return (a < 0 && b < 0) || (a > 0 && b > 0);
    }
}