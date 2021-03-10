/*
Given an m x n gird of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Note: There will be some test cases with a board or a word larger than constraints to test if your solution is using pruning.

 

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
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
*/
class Solution {
    
    public boolean exist(char[][] board, String word) {
        for(int row=0; row<board.length; row++) {
            for(int col=0; col<board[row].length; col++) {
                if(backtrack(board, row, col, word, 0))
                    return true;
            }
        }
        return false;
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
        
        /*for(int it=0; it<board.length; it++)
            System.out.println(Arrays.toString(board[it]));*/

        board[row][col] = word.charAt(index);
        return false;
    }
}