/*
Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

Each person may dislike some other people, and they should not go into the same group. 

Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

Return true if and only if it is possible to split everyone into two groups in this way.

 

Example 1:

Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]
Example 2:

Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Example 3:

Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false
 

Constraints:

1 <= N <= 2000
0 <= dislikes.length <= 10000
dislikes[i].length == 2
1 <= dislikes[i][j] <= N
dislikes[i][0] < dislikes[i][1]
There does not exist i != j for which dislikes[i] == dislikes[j].

*/
class Solution {
    
    HashMap<Integer, Integer> colors = new HashMap<>();
    ArrayList<Integer>[] adjList;
    
    public boolean possibleBipartition(int N, int[][] dislikes) {
        
        adjList = new ArrayList[N+1];
        
        for(int it=0; it<=N; it++)
            adjList[it] = new ArrayList<>();
        
        for(int it=0; it<dislikes.length; it++) {
            adjList[dislikes[it][0]].add(dislikes[it][1]);
            adjList[dislikes[it][1]].add(dislikes[it][0]);
        }
        
        for(int it=1; it<=N; it++) {
            if(!colors.containsKey(it) && !dfs(it, 0))
                return false;
        }
        
        return true;
    }
    
    public boolean dfs(int curr, int color) {
        if(colors.containsKey(curr)) {
            return colors.get(curr) == color;
        }
        
        colors.put(curr, color);
        
        for(int neighbor : adjList[curr]) {
            if(!dfs(neighbor, color ^ 1)) {
                return false;
            }
        }
        
        return true;
    }
}