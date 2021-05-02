import java.util.*;

class TaskScheduling {
  public static List<Integer> topSort(int tasks, int[][] prerequisites) {

    HashMap<Integer, Integer> inDeg = new HashMap<>();
    HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();

    for(int it=0; it < tasks; it++) {
      inDeg.put(it, 0);
      adjList.put(it, new ArrayList<>());
    }

    for(int it=0; it < prerequisites.length; it++) {
      inDeg.put(prerequisites[it][1], inDeg.get(prerequisites[it][1]) + 1);
      ArrayList<Integer> children = adjList.get(prerequisites[it][0]);
      children.add(prerequisites[it][1]);
    }

    Queue<Integer> queue = new LinkedList<>();
    for(int it=0; it<tasks; it++) {
      if(inDeg.get(it) == 0)
        queue.offer(it);
    }

    ArrayList<Integer> topoSort = new ArrayList<>();
    while(!queue.isEmpty()) {
      int curr = queue.poll();

      for(Integer children : adjList.get(curr)) {
        int inDegree = inDeg.get(children) - 1;
        inDeg.put(children, inDegree);

        if(inDegree == 0)
          queue.offer(children);
      }
      
      topoSort.add(curr);
    }

    return topoSort.size() == tasks;
  }

}