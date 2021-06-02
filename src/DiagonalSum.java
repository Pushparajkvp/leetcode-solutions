/*
Given a square matrix mat, return the sum of the matrix diagonals.

Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.

 

Example 1:


Input: mat = [[1,2,3],
              [4,5,6],
              [7,8,9]]
Output: 25
Explanation: Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
Notice that element mat[1][1] = 5 is counted only once.
Example 2:

Input: mat = [[1,1,1,1],
              [1,1,1,1],
              [1,1,1,1],
              [1,1,1,1]]
Output: 8
Example 3:

Input: mat = [[5]]
Output: 5
 

Constraints:

n == mat.length == mat[i].length
1 <= n <= 100
1 <= mat[i][j] <= 100
*/
class Solution {
    public int diagonalSum(int[][] mat) {
        int result = 0;
        
        int rows = mat.length;
        if(rows == 0)
            return -1;
        int cols = mat[0].length;
        
        for(int row=0; row<rows; row++) {
            int left = row, right = cols - row - 1;
            
            if(left == right)
                result += mat[row][left];
            else
                result += (mat[row][left] + mat[row][right]);
        }
        return result;
    }
}