/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
 

Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
Example 3:

Input: s = "A", numRows = 1
Output: "A"
 

Constraints:

1 <= s.length <= 1000
s consists of English letters (lower-case and upper-case), ',' and '.'.
1 <= numRows <= 1000
*/

//O(1) space Single Pass
class Solution {
    public String convert(String s, int numRows) {
        if(numRows <= 1) return s;
        
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        int cycleLen = 2*numRows - 2;
        
        for(int row=0; row<numRows; row++) {
            for(int col=0; col+row<len; col+=cycleLen) {
                sb.append(s.charAt(col+row));
                
                if(row != 0 && row != numRows-1 && (col + cycleLen - row) < len) {
                    sb.append(s.charAt(col + cycleLen - row));
                }
            }
        }
        
        return sb.toString();
    }
}

// O(n) space double pass
class Solution {
    public String convert(String s, int numRows) {
        char[] arr = s.toCharArray();
        StringBuilder result = new StringBuilder();
        
        if(numRows == 1) return s;
        
        List<StringBuilder> list = new ArrayList<>();
        for(int row=0; row<numRows; row++)
            list.add(new StringBuilder());
        
        int currRow = 0;
        boolean goingDown = true;
        
        for(char ch : arr) {
            list.get(currRow).append(ch);
            
            if(currRow == numRows - 1)
                goingDown = false;
            else if(currRow == 0)
                goingDown = true;
            
            if(goingDown)
                currRow++;
            else
                currRow--;
        }
        
        for(StringBuilder sb : list) {
            result.append(sb.toString());
        }
        
        return result.toString();
    }
}