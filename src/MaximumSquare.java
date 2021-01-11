/* 
Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

 

Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4
Example 2:


Input: matrix = [["0","1"],["1","0"]]
Output: 1
Example 3:

Input: matrix = [["0"]]
Output: 0
*/
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0) return 0;
        
        int dp[] = new int[matrix[0].length+1];
        
        int max = Integer.MIN_VALUE, prev = 0;
        for(int row=0; row < matrix.length; row++) {
            for(int col=0; col < matrix[row].length; col++) {
                int temp = dp[col+1];
                if(matrix[row][col] == '1') {
                    dp[col+1] = Math.min(dp[col+1], Math.min(dp[col], prev)) + 1;
                    max = Math.max(max, dp[col+1]);
                } else {
                    dp[col+1] = 0;
                }
                prev = temp;
            }
        }
        return max*max;
    }
}