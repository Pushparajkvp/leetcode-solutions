/* 
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character
 

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 

Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
*/
class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        
        for(int it=0; it<=word1.length(); it++)
            dp[it][0] = it;
        for(int it=0; it<=word2.length(); it++)
            dp[0][it] = it;
        
        for(int i=0; i<word1.length(); i++) {
            for(int j=0; j<word2.length(); j++) {
                if(word1.charAt(i) == word2.charAt(j)) {
                    dp[i+1][j+1] = 1 + Math.min(Math.min(dp[i][j]-1, dp[i+1][j]), dp[i][j+1]);
                } else {
                    dp[i+1][j+1] = 1 + Math.min(Math.min(dp[i][j], dp[i+1][j]), dp[i][j+1]);
                }
            }
        }
        
        return dp[word1.length()][word2.length()];
    }
}