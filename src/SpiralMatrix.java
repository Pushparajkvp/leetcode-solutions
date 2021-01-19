/* 
Given an m x n matrix, return all elements of the matrix in spiral order.

 

Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:


Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100

*/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return null;
        
        List<Integer> result = new ArrayList<Integer>();
        
        
        int top=0, right = matrix[0].length - 1, bottom = matrix.length - 1, left = 0;
        
        int total = matrix.length * matrix[0].length;
        
        while(result.size() < total) {
            
            //Top Row
            for(int it=left; it<=right && result.size() < total; it++)
                result.add(matrix[top][it]);
            top++;
            
            //Right Column
            for(int it=top; it<=bottom && result.size() < total; it++)
                result.add(matrix[it][right]);
            right--;
            
            //Bottom Row
            for(int it=right; it>=left && result.size() < total; it--)
                result.add(matrix[bottom][it]);
            bottom--;
            
            //Left Column
            for(int it=bottom; it>=top && result.size() < total; it--)
                result.add(matrix[it][left]);
            left++;
            
        }
        
        return result;
    }
}