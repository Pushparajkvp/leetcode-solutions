class Solution {
    ArrayList<Integer>[] adjList, rev;
    Stack<Integer> stack;
    boolean[] visited;
    
    public int findCircleNum(int[][] isConnected) {
        
        int n = isConnected.length;
        
        visited = new boolean[n];
        stack = new Stack<>();
        adjList = new ArrayList[n];
        rev = new ArrayList[n];
        
        for(int it=0; it<n; it++) {
            adjList[it] = new ArrayList<>();
            rev[it] = new ArrayList<>();
        }
        
        for(int row=0; row<isConnected.length; row++) {
            for(int col=0; col<isConnected[row].length; col++) {
                if(isConnected[row][col] == 1)
                    adjList[row].add(col);
            }
        }
        
        for(int it=0; it<n; it++) {
            if(!visited[it])
                dfs(it);
        }
        
        Arrays.fill(visited, false);
        
        for(int it=0; it<adjList.length; it++) {
            for(int neighbor : adjList[it]) {
                rev[neighbor].add(it);
            }
        }
        
        int count = 0;
        
        for(int it=0; it<n; it++) {
            if(!visited[it]) {
                count++;
                dfs(it);
            }
        }
        
        return count;
    }
    
    private void dfs(int curr) {
        
        visited[curr] = true;
        
        for(int neighbor : adjList[curr]) {
            if(!visited[neighbor]) 
                dfs(neighbor);
        }
        
        stack.push(curr);
    }
}