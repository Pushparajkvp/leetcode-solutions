/*
You are given an m x n grid rooms initialized with these three possible values.

-1 A wall or an obstacle.
0 A gate.
INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

 

Example 1:


Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
Example 2:

Input: rooms = [[-1]]
Output: [[-1]]
Example 3:

Input: rooms = [[2147483647]]
Output: [[2147483647]]
Example 4:

Input: rooms = [[0]]
Output: [[0]]
 

Constraints:

m == rooms.length
n == rooms[i].length
1 <= m, n <= 250
rooms[i][j] is -1, 0, or 231 - 1.
*/
class Solution {
    
    int cols, rows;
    int[][] directions = new int[][]{ {-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    
    public void wallsAndGates(int[][] rooms) {
        rows = rooms.length;
        if(rows == 0)
            return;
        
        cols = rooms[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        for(int row=0; row<rows; row++) {
            for(int col=0; col<cols; col++) {
                if(rooms[row][col] == 0) {
                    queue.offer(new int[]{ row, col});
                }
            }
        }
        
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currRow = curr[0], currCol = curr[1];
            
            for(int direction=0; direction < directions.length; direction++) {
                int newRow = currRow + directions[direction][0];
                int newCol = currCol + directions[direction][1];
                
                if(newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || rooms[newRow][newCol] != Integer.MAX_VALUE)
                    continue;
                
                rooms[newRow][newCol] = rooms[currRow][currCol] + 1;
                queue.add(new int[] { newRow, newCol});
                
            }
        }
    }
    
}