/*
Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
*/

class Solution {
    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int count = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++){
                if(!visited[i][j] && grid[i][j] == '1') {
                    dfs(grid, visited, i, j, grid.length, grid[0].length);
                    count++;
                }
            }
        }
        return count;
    }
    
    public void dfs(char[][] grid,boolean[][] visited,int x, int y, int rows, int columns) {
       visited[x][y] = true;
       if(x+1 < rows && grid[x+1][y] != '0' && !visited[x+1][y])
           dfs(grid, visited, x+1, y, rows, columns);
       if(y+1 < columns && grid[x][y+1] != '0' && !visited[x][y+1])
           dfs(grid, visited, x, y+1, rows, columns);
        if(x-1 >= 0 && grid[x-1][y] != '0' && !visited[x-1][y])
            dfs(grid, visited, x-1, y, rows, columns);
        if(y-1 >= 0 && grid[x][y-1] != '0' && !visited[x][y-1])
            dfs(grid, visited, x, y-1, rows, columns);
    }
}