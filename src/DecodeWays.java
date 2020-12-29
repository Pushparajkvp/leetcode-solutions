/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

The answer is guaranteed to fit in a 32-bit integer.

 

Example 1:

Input: s = "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: s = "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
Example 3:

Input: s = "0"
Output: 0
Explanation: There is no character that is mapped to a number starting with '0'. We cannot ignore a zero when we face it while decoding. So, each '0' should be part of "10" --> 'J' or "20" --> 'T'.
Example 4:

Input: s = "1"
Output: 1
 

Constraints:

1 <= s.length <= 100
s contains only digits and may contain leading zero(s).
*/
class Solution {
    
    public int numDecodings(String s) {
        if(s == null || s.length() == 0)
            return 0;
        
        int dp[] = new int[s.length() + 1];
        dp[0] = 1; //if it is double digit then there is one solution
        dp[1] = s.charAt(0) == '0' ? 0 : 1; 
        
        for(int it=1; it<s.length(); it++) {
            
            int currentDpWays = it+1;
            //If current digit is valid copy over the ways till that digit
            if(s.charAt(it) != '0')
                dp[currentDpWays] += dp[currentDpWays - 1];
            
            //If 2 digits pair is valid add current ways with ways before 2 digits
            
            int number = Integer.valueOf(s.substring(it-1, it+1));
            if(number >= 10 && number <=26) {
                dp[currentDpWays] += dp[currentDpWays - 2];
            }
        }
        
        
        return dp[dp.length - 1];
    }
}