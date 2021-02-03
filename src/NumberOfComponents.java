/* 
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

 

Example 1:


Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Example 2:


Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
 

Constraints:

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
*/

//Union Find Solution
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int count = n;
        int parent[] = new int[n];
        
        for(int it=0; it<n; it++)
            parent[it] = -1;
        
        for(int row=0; row<n; row++) {
            for(int col=0; col<n; col++) {
                if(row == col || isConnected[row][col] != 1)
                    continue;
                int rowParent = findParent(parent, row);
                int colParent = findParent(parent, col);
                if(rowParent != colParent) {
                    parent[rowParent] = colParent;
                    count--;
                }
            }
        }
        
        return count;
    }
    
    private int findParent(int parent[], int index) {
        int temp = index;
        while(parent[index] != -1) {
            index = parent[index];
        }
        while(parent[temp] != -1) {
            int next = parent[temp];
            parent[temp] = index;
            temp = next;
        }
        return index;
    }
}