/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 

Constraints:

1 <= n <= 8

*/
class Solution {
    
    List<String> result;
    
    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        if(n == 0)
            return result;
        
        recur("(", n, 1, n-1);
        
        return result;
    }
    
    private void recur(String curr, int n, int balance, int opensLeft) {
        if(n == 0) {
            result.add(curr);
            return;
        }
    
        if(balance > 0)
            recur(curr + ")", n - 1, balance-1, opensLeft);

        if(opensLeft > 0)
            recur(curr+"(", n, balance + 1, opensLeft - 1);
    }
}