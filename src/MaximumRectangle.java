/*
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 

Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
Example 2:

Input: matrix = []
Output: 0
Example 3:

Input: matrix = [["0"]]
Output: 0
Example 4:

Input: matrix = [["1"]]
Output: 1
Example 5:

Input: matrix = [["0","0"]]
Output: 0
 

Constraints:

rows == matrix.length
cols == matrix.length
0 <= row, cols <= 200
matrix[i][j] is '0' or '1'.
*/

//DP solution
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0)
            return 0;
        
        int rows =  matrix.length;
        int cols = matrix[0].length;
        
        int heights[] = new int[cols];
        int lefts[] = new int[cols];
        int rights[] = new int[cols];
        
        Arrays.fill(rights, cols-1);
        
        int maxArea = 0;
        
        for(int row=0; row<rows; row++) {
            int currLeft = 0, currRight = cols - 1;
        
            //update left
            for(int col=0; col<cols; col++) {
                if(matrix[row][col] == '1')
                    lefts[col] = Math.max(lefts[col] , currLeft);
                else {
                    lefts[col] = 0;
                    currLeft = col + 1;
                }
            }
            
            //update right
            for(int col=cols-1; col>=0; col--) {
                if(matrix[row][col] == '1') {
                    rights[col] = Math.min(rights[col], currRight);
                } else {
                    rights[col] = cols - 1;
                    currRight = col - 1;
                }
            }
            
            for(int col=0; col<cols; col++) {
                
                //calculate height
                if(matrix[row][col] == '1')
                    heights[col] = heights[col] + 1;
                else {
                    heights[col] = 0;
                    continue;
                }
                
                maxArea = Math.max(maxArea, (rights[col] - lefts[col] + 1) * heights[col]);
            }
            
        }
        
        return maxArea;
    }
}

//Histogram solution
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int heights[] = new int[cols];
        
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        
        int maxArea = Integer.MIN_VALUE;
        for(int row=0; row<rows; row++) {
            int maxRowArea = Integer.MIN_VALUE;
            for(int col=0; col<cols; col++) {
                if(matrix[row][col] != '1') {
                    heights[col] = 0;
                } else {
                    heights[col] = heights[col] + 1;   
                }
                
                while(stack.peek() != -1 && heights[stack.peek()] >= heights[col]) {
                    int height = heights[stack.pop()];
                    int width = col - stack.peek() - 1;
                    maxRowArea = Math.max(maxRowArea, height*width);
                }
                
                stack.push(col);
            }
            
            while(stack.peek() != -1) {
                int height = heights[stack.pop()];
                int width = cols - stack.peek() - 1;
                maxRowArea = Math.max(maxRowArea, height*width);
            }
            maxArea = Math.max(maxRowArea, maxArea);
        }
        
        return maxArea;
    }
}