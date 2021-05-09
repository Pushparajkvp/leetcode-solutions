/*
There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.

You may assume that the borders of the maze are all walls (see examples).

 

Example 1:


Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
Example 2:


Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
Output: false
Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.
Example 3:

Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
Output: false
 

Constraints:

m == maze.length
n == maze[i].length
1 <= m, n <= 100
maze[i][j] is 0 or 1.
start.length == 2
destination.length == 2
0 <= startrow, destinationrow <= m
0 <= startcol, destinationcol <= n
Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
The maze contains at least 2 empty spaces.
*/
class Solution {
    
    private int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        return find(maze, destination, new boolean[maze.length][maze[0].length], start);
    }
    
    private boolean find(int[][] maze,int[] destination,boolean[][] visited, int[] curr) {
        
        if(curr[0] == destination[0] && curr[1] == destination[1])
            return true;
        
        visited[curr[0]][curr[1]] = true;
        
        
        for(int it=0; it<directions.length; it++) {
            int[] rollEnd = roll(maze, curr, it);
            
            if(!visited[rollEnd[0]][rollEnd[1]] && find(maze, destination, visited, rollEnd))
                return true;
        }
        
        return false;
    }
    
    private int[] roll(int[][] maze, int[] curr, int direction) {
        
        int[] result = new int[]{curr[0], curr[1]};
        int travRow = curr[0] + directions[direction][0], travCol = curr[1] + directions[direction][1];
        
        while(travRow >= 0 && travRow < maze.length && travCol >= 0 && travCol < maze[0].length
                && maze[travRow][travCol] != 1) {
            result[0] = travRow;
            result[1] = travCol;
            travRow += directions[direction][0];
            travCol += directions[direction][1];
        }
        
        return result;
    }
}