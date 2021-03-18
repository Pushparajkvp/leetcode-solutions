/*
Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

 

Example 1:



Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).
Example 2:



Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:

Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2
 

Constraints:

1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1
*/
class Solution {
    
    private static final int LAND = 0, WATER = 1;
    
    boolean[][] visited;
    int rows, cols;
    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int closedIsland(int[][] grid) {
        rows = grid.length;
        if(rows == 0)
            return 0;
        cols = grid[0].length;
        
        visited = new boolean[rows][cols];
        
        int count = 0;
        for(int row=0; row < rows; row++) {
            for(int col=0; col < cols; col++) {
                if(grid[row][col] == LAND && !visited[row][col]) {
                    if(!dfs(grid, row, col))
                        count++;
                }
            }
        }
        return count;
    }
    
    private boolean dfs(int[][] grid, int row, int col) {
        
        visited[row][col] = true;
        
        boolean isEdge = row == rows - 1 || col == cols - 1 || row == 0 || col == 0;
        for(int it=0; it<directions.length; it++) {
            int nextRow = row + directions[it][0];
            int nextCol = col + directions[it][1];
            
            if(nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || visited[nextRow][nextCol] || grid[nextRow][nextCol] == WATER)
                continue;
            
            if(dfs(grid, nextRow, nextCol)) isEdge = true;
        }
        
        return isEdge;
    }
}
