/*
Your music player contains N different songs and she wants to listen to L (not necessarily different) songs during your trip.  You create a playlist so that:

Every song is played at least once
A song can only be played again only if K other songs have been played
Return the number of possible playlists.  As the answer can be very large, return it modulo 10^9 + 7.

 

Example 1:

Input: N = 3, L = 3, K = 1
Output: 6
Explanation: There are 6 possible playlists. [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1].
Example 2:

Input: N = 2, L = 3, K = 0
Output: 6
Explanation: There are 6 possible playlists. [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], [1, 2, 2]
Example 3:

Input: N = 2, L = 3, K = 1
Output: 2
Explanation: There are 2 possible playlists. [1, 2, 1], [2, 1, 2]
 

Note:

0 <= K < N <= L <= 100
*/
class Solution {
    int N, L, K;
    long[][] dp;
    public int numMusicPlaylists(int N, int L, int K) {
        this.N = N;
        this.L = L;
        this.K = K;
        dp = new long[100][100];
        for(int i=0; i<100; i++)
            for(int j=0; j<100; j++)
                dp[i][j] = -1;
        return (int)find(0, 0);
    }
    
    private long find(int currLen, int uniqueSongs) {
        if(currLen == L)
            return uniqueSongs == N ? 1 : 0;
        if(dp[currLen][uniqueSongs] != -1)
            return dp[currLen][uniqueSongs];
        
        long result = 0;
        if(uniqueSongs - K > 0)
            result += (find(currLen + 1, uniqueSongs) * (uniqueSongs - K))  % 1000000007;
        
        if(uniqueSongs < N)
            result += (find(currLen + 1, uniqueSongs + 1) * (N - uniqueSongs)) % 1000000007;
        
        dp[currLen][uniqueSongs] = result;
        return result;
    }
}