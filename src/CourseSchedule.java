/* 
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 

Constraints:

1 <= numCourses <= 105
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
*/

//BFS
class Solution {
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, Integer> inDeg = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();

        for(int it=0; it < numCourses; it++) {
          inDeg.put(it, 0);
          adjList.put(it, new ArrayList<>());
        }

        for(int it=0; it < prerequisites.length; it++) {
          inDeg.put(prerequisites[it][1], inDeg.get(prerequisites[it][1]) + 1);
          ArrayList<Integer> children = adjList.get(prerequisites[it][0]);
          children.add(prerequisites[it][1]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int it=0; it<numCourses; it++) {
          if(inDeg.get(it) == 0)
            queue.offer(it);
        }

        while(!queue.isEmpty()) {
          int curr = queue.poll();

          for(Integer children : adjList.get(curr)) {
            int inDegree = inDeg.get(children) - 1;
            inDeg.put(children, inDegree);

            if(inDegree == 0)
              queue.offer(children);
          }

          numCourses--;
        }

        return numCourses == 0;
    }
}

//DFS
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // it can be sorted by dfs / bfs / topological sort 
        // topological sort can be performed by dfs or khan's algorithm have to read about it.
        // this adjaceny list can also be formed by just using a List and key as the list index.
        HashMap<Integer,List<Integer>> adjacenyList = new HashMap<>();
        boolean[] visited = new boolean[numCourses];
        for(int[] preReq: prerequisites){
            if(adjacenyList.containsKey(preReq[0])){
                List<Integer> list = adjacenyList.get(preReq[0]);
                list.add(preReq[1]);
                adjacenyList.put(preReq[0], list);
            }
            else{
                List<Integer> list = new ArrayList<>();
                list.add(preReq[1]);
                adjacenyList.put(preReq[0], list);
            }   
        }
        
        for(int course=0;course<numCourses;course++){
            if(!dfs(adjacenyList,visited,course)) return false; // even if one course is impossible end the loop.
        }
        return true;
    }
    
   boolean dfs(HashMap<Integer,List<Integer>> adjacenyList,boolean[] visited, int course){
        if(visited[course]) return false; // base case.
        else if(adjacenyList.getOrDefault(course, new ArrayList<>()).isEmpty()) return true; // if this course has no prereq we don't need to go through it.
        else visited[course] = true; // this course is being visited during this dfs search
        
        for(int j: adjacenyList.get(course)){
            if(!dfs(adjacenyList,visited,j)) return false;
        }
        visited[course] = false;
        adjacenyList.put(course,new ArrayList<>());
       return true;
    }
}