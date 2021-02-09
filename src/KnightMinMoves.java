/*
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.



Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

 

Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 

Constraints:

|x| + |y| <= 300
*/
class Solution {
    
    public final int[][] directions = {{2,1}, {-2,1}, {2,-1}, {-2,-1}, {1,2}, {-1,2}, {1,-2}, {-1,-2}};
    
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        
        Queue<int[]> squares = new LinkedList<>();
        squares.add(new int[]{0, 0});
        
        Set<Pair> visited = new HashSet<>();
        visited.add(new Pair(0, 0));
        
        int count =0;
        
        while(!squares.isEmpty()) {
            int len = squares.size();
            
            for(int i=0; i<len; i++) {
                int[] curr = squares.poll();

                if(curr[0] == x && curr[1] == y) return count;

                for(int it=0; it<directions.length; it++) {
                    int[] move = new int[]{curr[0] + directions[it][0], curr[1] + directions[it][1]};

                    if(!visited.contains(new Pair(move[0], move[1])) && move[0] >= -1 && move[1] >= -1) {
                        visited.add(new Pair(move[0], move[1]));
                        squares.add(move);
                    }

                }   
            }
            
            count++;
        }
        
        return -1;
    }

}