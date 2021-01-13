/* 
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false

*/

//Memo solution
class Solution {
    HashSet<String> set;
    Boolean[] mem;
    public boolean wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        mem =new Boolean[s.length()];
        
        return check(s, 0);
    }
    
    private boolean check(String s, int start) {
        if(start == s.length())
            return true;

        if(mem[start] != null)
            return mem[start];
        
        for(int end = start+1; end <= s.length(); end++) {
            if(set.contains(s.substring(start, end)) && check(s, end))
                return mem[start] = true;
        }
        
        return mem[start] = false;
    }
}

//DP solution
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        
        for(int i=0; i<s.length(); i++) {
            for(int j=0; j<=i; j++) {
                if(dp[j] && set.contains(s.substring(j, i+1))) {
                    dp[i+1] = true;
                    break;
                }
            }
        }
        
        return dp[s.length()];
    }
}