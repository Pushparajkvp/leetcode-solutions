/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:

[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.
*/
class Solution {
    
    private int[][] directions = new int[][] { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        if(rows == 0 ) return 0;
        int cols = grid[0].length;
        
        boolean[][] visited = new boolean[rows][cols];
        
        int max = 0;
        for(int row=0; row < rows; row++) {
            for(int col=0; col < cols; col++) {
                if(grid[row][col] == 1 && !visited[row][col]) {
                    max = Math.max(dfs(grid, visited, rows, cols, row, col), max); 
                }
            }
        }
        return max;
    }
    
    private int dfs(int[][] grid, boolean[][] visited, int rows, int cols, int row, int col) {
        if(row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == 0)
            return 0;
        int sum = 1;
        
        visited[row][col] = true;
        
        for(int it=0; it<directions.length; it++) {
            int currRow = row + directions[it][0];
            int currCol = col + directions[it][1];
            
            if(currRow < rows && currCol < cols && currRow >= 0 && currCol >= 0 && grid[currRow][currCol] == 1 && !visited[currRow][currCol])
                sum += dfs(grid, visited, rows, cols, currRow, currCol);   
        }
        
        
        return sum;
    }
}