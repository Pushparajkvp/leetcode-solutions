/* 
Given an m x n board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

 

Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false
 

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 200
1 <= word.length <= 103
board and word consists only of lowercase and uppercase English letters.
*/
class Solution {
    
    public boolean exist(char[][] board, String word) {
        
        return backtrack(board, 0, 0, word, 0);
    }
    
    private boolean backtrack(char[][] board,int row, int col, String word, int index) {
        if (index >= word.length())
          return true;

        if (row < 0 || row == board.length || col < 0 || col == board[0].length
            || board[row][col] != word.charAt(index))
          return false;

        board[row][col] = '#';

        int[] rowOffsets = {0, 1, 0, -1};
        int[] colOffsets = {1, 0, -1, 0};
        for (int d = 0; d < 4; ++d) {
          if (backtrack(board, row + rowOffsets[d], col + colOffsets[d], word, index + 1)) {
              board[row][col] = word.charAt(index);
              return true;
          }
            
        }

        board[row][col] = word.charAt(index);
        return false;
    }
}