/* 
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

 

Example 1:


Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:


 

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
*/
class Solution {
    
    HashSet<Integer> rows[];
    HashSet<Integer> cols[];
    HashSet<Integer> squares[];
    
    public void solveSudoku(char[][] board) {
        rows = new HashSet[9];
        cols = new HashSet[9];
        squares = new HashSet[9];
        for(int row=0; row<board.length; row++) {
            rows[row] = new HashSet<>();
            for(int col=0; col<board[row].length; col++) {
                if(cols[col] == null)
                    cols[col] = new HashSet<>();
                
                int square = 3*((int)row/3) + col/3;
                
                if(squares[square] == null)
                    squares[square] = new HashSet<>();
                
                if(board[row][col] != '.') {
                    rows[row].add(board[row][col] - '0');
                    cols[col].add(board[row][col] - '0');
                    squares[square].add(board[row][col] - '0');
                }
            }
        }
        solve(board, 0, 0);
    }
    
    private boolean solve(char[][] board, int row, int col) {
        
        if(row == 8 && col == 9)
            return true;
        
        if(col > 8) {
            row += 1;
            col = 0;
        }
        
        if(board[row][col] != '.')
            return solve(board, row, col + 1);

        int square = 3*((int)row/3) + col/3;
        for(int it=1; it<10; it++) {
            if(rows[row].contains(it) || cols[col].contains(it) || squares[square].contains(it))
                continue;
            board[row][col] = (char) (it + '0');
            //System.out.println(board[row][col]);
            rows[row].add(it);
            cols[col].add(it);
            squares[square].add(it);
            
            if(!solve(board, row, col + 1)) {
                board[row][col] = '.';
                rows[row].remove(it);
                cols[col].remove(it);
                squares[square].remove(it);      
            } else {
                return true;
            }
        }
        return false;
    }
}