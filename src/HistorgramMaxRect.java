/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

 


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

 


The largest rectangle is shown in the shaded area, which has area = 10 unit.

 

Example:

Input: [2,1,5,6,2,3]
Output: 10
 

Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:


Input: heights = [2,4]
Output: 4
 

Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104
*/
//One Pass Solution

class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        
        int max = Integer.MIN_VALUE;
        for(int it=0; it<heights.length; it++) {
            while(stack.peek() != -1 && heights[stack.peek()] >= heights[it]) {
                int height = heights[stack.pop()];
                int width = it - stack.peek() - 1;
                max = Math.max(max, height*width);   
            }
            stack.push(it);
        }
        
        while(stack.peek() != -1) {
            int height = heights[stack.pop()];
            int width = heights.length - stack.peek() - 1;
            max = Math.max(max, height*width);
        }
        return max;
    }
}

//Two Pass Solution
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        
        int[] leftLimits = new int[heights.length];
        int[] rightLimits = new int[heights.length];
        
        for(int it=0; it<heights.length; it++) {
            
            if(stack.isEmpty()) {
                leftLimits[it] = 0;
            } else {
                while(!stack.isEmpty() && heights[stack.peek()] >= heights[it]) {
                    stack.pop();
                }
                
                if(stack.isEmpty()) {
                    leftLimits[it] = 0;
                } else {
                    leftLimits[it] = stack.peek() + 1;
                }
            }
            stack.push(it);
        }
        
        stack = new Stack<>();
        for(int it=heights.length - 1; it>=0; it--) {
            
            if(stack.isEmpty()) {
                rightLimits[it] = heights.length - 1;
            } else {
                while(!stack.isEmpty() && heights[stack.peek()] >= heights[it]) {
                    stack.pop();
                }
                
                if(stack.isEmpty()) {
                    rightLimits[it] = heights.length - 1;
                } else {
                    rightLimits[it] = stack.peek() - 1;
                }
            }
            stack.push(it);
        }
        
        int max = Integer.MIN_VALUE;
        for(int it=0; it<heights.length; it++) {
            int area = (rightLimits[it] - leftLimits[it] + 1) * heights[it];
            max = Math.max(area, max);
        }
        
        return max;
    }
}