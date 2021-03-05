/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Accepted
131,657
Submissions
227,803
*/
class Solution {
    
   int[] parent;
    
    public int countComponents(int n, int[][] edges) {
        int result = n;
        parent = new int[n];
        
        Arrays.fill(parent, -1);
        for(int it=0; it<edges.length; it++)
            if(union(parent, edges[it][0], edges[it][1]))
                result--;
        
        return result;
    }
    
    private boolean union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);
        
        if(rootA != rootB)
            parent[rootA] = rootB;
        return rootA != rootB;
    }
    
    private int find(int[] parent, int a) {
        
        int root = a;
        while(parent[root] != -1) root = parent[root];
        
        int trav = a;
        while(trav != root) {
            int next = parent[trav];
            parent[trav] = root;
            trav = next;
        }
        
        return root;
    }
}