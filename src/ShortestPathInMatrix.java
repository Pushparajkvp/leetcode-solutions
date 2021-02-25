/*
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

 

Example 1:


Input: grid = [[0,1],[1,0]]
Output: 2
Example 2:


Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
*/
class Solution {
    
    private final int[][] directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        int rows = grid.length;
        int cols = grid[0].length;
        int len = 0;
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        
        if(grid[0][0] == 1)
            return -1;
        if(grid[rows-1][cols-1] == 1)
            return -1;
        
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        
        while(!queue.isEmpty()) {
            
            len++;
            int nodes = queue.size();
            
            for(int it=0; it<nodes; it++) {
                int[] curr = queue.poll();
                
                if(curr[0] == rows - 1 && curr[1] == cols -1)
                    return len;
                
                for(int direction=0; direction<directions.length; direction++) {
                    int row = curr[0] + directions[direction][0];
                    int col = curr[1] + directions[direction][1];
                    if(row >= 0 && col >= 0 && row < rows && col < cols && grid[row][col] != 1 && !visited[row][col]) {
                        visited[row][col] = true;
                        queue.offer(new int[]{row, col});
                }
            }   
            }
        }
        
        return -1;
    }
}