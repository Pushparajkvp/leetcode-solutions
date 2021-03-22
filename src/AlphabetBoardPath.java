/*
On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].

Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.



We may make the following moves:

'U' moves our position up one row, if the position exists on the board;
'D' moves our position down one row, if the position exists on the board;
'L' moves our position left one column, if the position exists on the board;
'R' moves our position right one column, if the position exists on the board;
'!' adds the character board[r][c] at our current position (r, c) to the answer.
(Here, the only positions that exist on the board are positions with letters on them.)

Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.

 

Example 1:

Input: target = "leet"
Output: "DDR!UURRR!!DDD!"
Example 2:

Input: target = "code"
Output: "RR!DDRR!UUL!R!"
 

Constraints:

1 <= target.length <= 100
target consists only of English lowercase letters.
*/
class Solution {
    public String alphabetBoardPath(String target) {
        
        int currRow = 0, currCol = 0;
        
        StringBuilder sb = new StringBuilder();
        
        for(int it=0; it<target.length(); it++) {
            char letter = target.charAt(it);
            
            int tarRow = (letter - 'a') / 5;
            int tarCol = (letter - 'a') % 5;
            
            //Move down
            if(currRow <= tarRow) {
                moveCol(sb, currCol, tarCol);
                moveRow(sb, currRow, tarRow);
            } else { //Move up
                moveRow(sb, currRow, tarRow);
                moveCol(sb, currCol, tarCol);
            }
            currRow = tarRow;
            currCol = tarCol;
            sb.append('!');
        }
        
        return sb.toString();
    }
    
    private void moveRow(StringBuilder sb, int currRow, int tarRow) {
        int rowDiff = tarRow - currRow;
                
        if(rowDiff < 0) {
            for(int i=0; i<Math.abs(rowDiff); i++)
                sb.append('U');
        } else if(rowDiff > 0) {
            for(int i=0; i<Math.abs(rowDiff); i++)
                sb.append('D');
        }
    }
    
    private void moveCol(StringBuilder sb, int currCol, int tarCol) {
        int colDiff = tarCol - currCol;
        
        if(colDiff < 0) {
            for(int i=0; i<Math.abs(colDiff); i++)
                sb.append('L');
        } else if(colDiff > 0) {
            for(int i=0; i<Math.abs(colDiff); i++)
                sb.append('R');
        }
    }
}