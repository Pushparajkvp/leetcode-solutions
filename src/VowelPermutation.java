/*
Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
Each vowel 'a' may only be followed by an 'e'.
Each vowel 'e' may only be followed by an 'a' or an 'i'.
Each vowel 'i' may not be followed by another 'i'.
Each vowel 'o' may only be followed by an 'i' or a 'u'.
Each vowel 'u' may only be followed by an 'a'.
Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: n = 1
Output: 5
Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
Example 2:

Input: n = 2
Output: 10
Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
Example 3: 

Input: n = 5
Output: 68
 

Constraints:

1 <= n <= 2 * 10^4
*/

// DP solution
class Solution {
    
    int MOD = 1000000007;
    
    public int countVowelPermutation(int n) {
        int[][] dp = new int[n][5];
        
        Arrays.fill(dp[0], 1);
        
        for(int it=1; it<n; it++) {
            dp[it][0] = dp[it-1][1];
            dp[it][1] = (dp[it-1][0] + dp[it-1][2]) % MOD;
            dp[it][2] = (((dp[it-1][0] + dp[it-1][1]) % MOD + dp[it-1][3]) % MOD + dp[it-1][4]) % MOD;
            dp[it][3] = (dp[it-1][2] + dp[it-1][4]) % MOD;
            dp[it][4] = dp[it-1][0];
        }
        
        int total = 0;
        for(int it=0; it<5; it++)
            total = (total + dp[n-1][it]) % MOD;
        
        return total;
    }
}

//DFS with memo
class Solution {
    
    int MOD = 1000000007;
    Integer[][] memo;
    HashMap<Character, List<Character>> map;
    public int countVowelPermutation(int n) {
        map = new HashMap<>();
        memo = new Integer[n+1][118];
        map.put('a', Arrays.asList('e'));
        map.put('e', Arrays.asList('a','i'));
        map.put('i', Arrays.asList('a','e','o','u'));
        map.put('o', Arrays.asList('i','u'));
        map.put('u', Arrays.asList('a'));
        map.put(' ', Arrays.asList('a','e','i','o','u'));
        return permutations(' ',n);

    }
    private int permutations(char pre, int n) {
        if(n==0) {
            return 1;
        }
        if(memo[n][pre] != null)
            return memo[n][pre];
        
        int count=0;
        for(char c:map.get(pre)) {
            count+=permutations(c,n-1); 
            count%=MOD;
        }
        return memo[n][pre] = count;
    }
}