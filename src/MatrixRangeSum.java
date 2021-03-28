/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Implement the NumMatrix class:

NumMatrix(int[][] matrix) initializes the object with the integer matrix matrix.
void update(int row, int col, int val) updates the value of matrix[row][col] to be val.
int sumRegion(int row1, int col1, int row2, int col2) returns the sum of the elements of the matrix array inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 

Example 1:


Input
["NumMatrix", "sumRegion", "update", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]
Output
[null, 8, null, 10]

Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8
numMatrix.update(3, 2, 2);
numMatrix.sumRegion(2, 1, 4, 3); // return 10
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
-105 <= matrix[i][j] <= 105
0 <= row < m
0 <= col < n
-105 <= val <= 105
0 <= row1 <= row2 < m
0 <= col1 <= col2 < n
At most 104 calls will be made to sumRegion and update.
*/

//BIT implementation
class NumMatrix {

    int[][] bit;
    int rows, cols;
    
    public NumMatrix(int[][] matrix) {
        this.rows = matrix.length + 1;
        this.cols = matrix[0].length + 1;
        
        this.bit = new int[rows][cols];
        for(int row=0; row<rows-1; row++) {
            for(int col=0; col<cols-1; col++) {
                this.bit[row+1][col+1] = matrix[row][col];
            }
        }
        for(int row=1; row<rows; row++) {
            for(int col=1; col<cols; col++) {
                int parent = col + lsb(col);
                if(parent < cols)
                    this.bit[row][parent] += this.bit[row][col];
            }
        }
        
        /*for(int row=1; row<rows; row++) {
            System.out.println(Arrays.toString(bit[row]));
        }*/
    }
    
    public void update(int row, int col, int val) {
        row++;
        col++;
        
        val = val - sum(row, col, col);
        add(row, col, val);
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1++;
        row2++;
        col1++;
        col2++;
        
        int sum = 0; 
        
        while(row1 <= row2) {
            sum += sum(row1, col1, col2);
            //System.out.println(sum);
            row1++;
        }
        
        return sum;
    }
    
    private int lsb(int num) {return num & -num;}
    
    
    
    private int sum(int row, int startCol, int endCol) {
        return prefixSum(row, endCol) - prefixSum(row, startCol - 1);
    }
    
    private void add(int row, int col, int val) {
        
        while(col < cols) {
            bit[row][col] += val;
            col += lsb(col);
        }
    }

    
    private int prefixSum(int row, int col) {
        int sum = 0;
        while(col > 0) {
            sum += bit[row][col];
            col -= lsb(col);
        }
        
        return sum;
    }
    
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */