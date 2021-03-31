/*
Given a sorted integer array nums and three integers a, b and c, apply a quadratic function of the form f(x) = ax2 + bx + c to each element nums[i] in the array, and return the array in a sorted order.

 

Example 1:

Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
Output: [3,9,15,33]
Example 2:

Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
Output: [-23,-5,1,7]
 

Constraints:

1 <= nums.length <= 200
-100 <= nums[i], a, b, c <= 100
nums is sorted in ascending order.
 

Follow up: Could you solve it in O(n) time?
*/

//O(n) solution
class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        //Direction of Parabolas: The sign on the coefficient  a determines the direction of the parabola.
        // If the coefficient a>0, the parabola opens upward, 
        //and if the coefficient a<0, the parabola opens downward.
        //  \   /       _
        //   \_/       / \
        //            /   \

        int[] result = new int[nums.length];
        int i = nums.length-1;
        int pStart = 0;
        int pEnd = nums.length-1;
        int x = 0;
        int y = 0;
        if(a < 0) i = 0;
        while(pStart <= pEnd) {
            x = func(a,b,c,nums[pStart]);
            y = func(a,b,c,nums[pEnd]);
            if(a>=0) {
                if(x>y) pStart++;
                else pEnd--;
                result[i--] = Math.max(x,y);
            } else{
                if(x>y) pEnd--;
                else pStart++;
                result[i++] = Math.min(x,y);
            }
        }
        return result;
    }
    
    private int func(int a, int b, int c, int x) {
        return a*x*x + b*x + c;
    }
}

// O(nlogn) solution
class Solution {
    
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for(int it=0; it<nums.length; it++) {
            minHeap.offer(compute(a, b, c, nums[it]));
        }
        
        for(int it=0; it<nums.length; it++) {
            nums[it] = minHeap.poll();
        }
        
        return nums;
    }
    
    private int compute(int a, int b, int c, int x) {
        return (int)((a * Math.pow(x, 2)) + (b * x) + c);
    }
}