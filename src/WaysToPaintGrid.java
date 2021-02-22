/*
You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colours: Red, Yellow or Green while making sure that no two adjacent cells have the same colour (i.e no two cells that share vertical or horizontal sides have the same colour).

You are given n the number of rows of the grid.

Return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 10^9 + 7.

 

Example 1:

Input: n = 1
Output: 12
Explanation: There are 12 possible way to paint the grid as shown:

Example 2:

Input: n = 2
Output: 54
Example 3:

Input: n = 3
Output: 246
Example 4:

Input: n = 7
Output: 106494
Example 5:

Input: n = 5000
Output: 30228214
 

Constraints:

n == grid.length
grid[i].length == 3
1 <= n <= 5000
*/

//Dividing into groups of 2 colors and 3 colors
class Solution {
    
    int MOD = 1000000007;
    
    public int numOfWays(int n) {
        long c3 = 6;
        long c2 = 6;
        for(int i=2; i<=n; i++) {
            long temp = c3;
            c3 = (2 * c2 + 2 * c3) % MOD;
            c2 = (2 * temp + 3 * c2) % MOD;
        }
        
        return (int)(c3 + c2) % MOD;
    }
}