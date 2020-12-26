/* 
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Input: height = [4,2,0,3,2,5]
Output: 9

Constraints:
n == height.length
0 <= n <= 3 * 104
0 <= height[i] <= 105
*/


class Solution {
    public int trap(int[] height) {
        if(height.length <= 0) return 0;
        
        int left = 0, right = height.length - 1;
        int leftMax = height[left], rightMax = height[right];
        int area = 0;
        
        while(left <= right) {
            if(height[left] <= height[right]) {
                //Move left to right
                if(height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    int diff = leftMax - height[left];
                    area += diff;
                }
                left++;
            } else {
                //Mpve right to left
                if(height[right] >= rightMax){
                    rightMax = height[right];
                } else {
                    int diff = rightMax - height[right];
                    area += diff;
                }
                right--;
            }
        }
        
        return area;
    }
}