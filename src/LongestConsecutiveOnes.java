/*
Given an m x n binary matrix mat, return the length of the longest line of consecutive one in the matrix.

The line could be horizontal, vertical, diagonal, or anti-diagonal.

 

Example 1:


Input: mat = [[0,1,1,0],[0,1,1,0],[0,0,0,1]]
Output: 3
Example 2:


Input: mat = [[1,1,1,1],[0,1,1,0],[0,0,0,1]]
Output: 4
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
*/
class Solution {
    
    int[][][] dp;
    int rows, cols;
    int[][] directions = new int[][] {{-1, -1}, {-1, 0}, {0, -1}, {-1, 1}};
    
    public int longestLine(int[][] M) {
        
        if(M.length == 0)
            return 0;
        
        rows = M.length;
        cols = M[0].length;
        dp = new int[rows][cols][4];
        
        int max = 0;
        
        for(int row=0; row<rows; row++) {
            for(int col=0; col<cols; col++) {
                if(M[row][col] != 1)
                    continue;
                
                for(int it=0; it<4; it++) {
                    int[] direction = directions[it];
                    int prevRow = row + direction[0];
                    int prevCol = col + direction[1];
                    
                    int prevVal = 0;
                    if(prevRow >= 0 && prevCol >= 0 && prevRow < rows && prevCol < cols)
                        prevVal = dp[prevRow][prevCol][it];
                    
                    dp[row][col][it] = prevVal + 1;
                    max = Math.max(dp[row][col][it], max);
                }        
            }
        }
        
        /*for(int row=0; row<rows; row++) {
            for(int col=0; col<cols; col++) {
                System.out.println(Arrays.toString(dp[row][col]));       
            }
        }*/
        return max;
    }
}