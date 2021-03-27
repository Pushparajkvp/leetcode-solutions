/*
You are given a tree with n nodes numbered from 0 to n-1 in the form of a parent array where parent[i] is the parent of node i. The root of the tree is node 0.

Implement the function getKthAncestor(int node, int k) to return the k-th ancestor of the given node. If there is no such ancestor, return -1.

The k-th ancestor of a tree node is the k-th node in the path from that node to the root.

 

Example:



Input:
["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
[[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]

Output:
[null,1,0,-1]

Explanation:
TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);

treeAncestor.getKthAncestor(3, 1);  // returns 1 which is the parent of 3
treeAncestor.getKthAncestor(5, 2);  // returns 0 which is the grandparent of 5
treeAncestor.getKthAncestor(6, 3);  // returns -1 because there is no such ancestor
 

Constraints:

1 <= k <= n <= 5*10^4
parent[0] == -1 indicating that 0 is the root node.
0 <= parent[i] < n for all 0 < i < n
0 <= node < n
There will be at most 5*10^4 queries.

*/
//Slow implementation with treemap
class TreeAncestor {

    int[] parent;
    HashMap<Integer, TreeMap<Integer, Integer>> map = new HashMap<>();
    
    public TreeAncestor(int n, int[] parent) {
        this.parent = parent;
    }
    
    public int getKthAncestor(int node, int k) {
        TreeMap<Integer, Integer> treeMap = map.get(node);
        if(treeMap == null)
            treeMap = new TreeMap<>();
        
        if(treeMap.containsKey(k))
            return treeMap.get(k);
        
        Map.Entry<Integer, Integer> entry = treeMap.floorEntry(k);
        
        int trav = entry != null ? entry.getValue() : node;
        int travK = entry != null ? k - entry.getKey() : k;
        while(trav != -1 && travK > 0) {
            trav = parent[trav];
            travK--;
        }
        treeMap.put(k, trav);
        map.put(node, treeMap);
        return trav;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
//Indexed parent arr
class TreeAncestor {
    int n;
    int[] p1;
    int[] p8;
    int[] p64;
    int[] p512;
    int[] p4096;
    
    public TreeAncestor(int n, int[] parent) {
        this.n = n;
        this.p1 = parent;
        p8 = new int[n];
        p64 = new int[n];               
        p512 = new int[n];
        p4096 = new int[n];

        fill(p8,p1,8);
        fill(p64,p8,8);
        fill(p512,p64,8);
        fill(p4096,p512,8);
    }
    
    private void fill(int[] arr,int[] p,int k){
        for(int i=0;i<n;i++){
            int node = i;
            for(int j=0;j<k && node!=-1 ;j++){
                node = p[node];
            }
            arr[i] = node;
        }
    }
    
    
    public int getKthAncestor(int node, int k) {
        while(k>0 && node>=0){
            if(k>=4096){
                node = p4096[node];
                k-=4096;
            }else if(k>=512){
                node = p512[node];
                k-=512;
            }else if(k>=64){
                node = p64[node];
                k-=64;
            }else if(k>=8){
                node = p8[node];
                k -= 8;
            }else{
                node = p1[node];
                k--;
            }
        }
        return node;
    }
}
