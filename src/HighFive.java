/*
Given a list of the scores of different students, items, where items[i] = [IDi, scorei] represents one score from a student with IDi, calculate each student's top five average.

Return the answer as an array of pairs result, where result[j] = [IDj, topFiveAveragej] represents the student with IDj and their top five average. Sort result by IDj in increasing order.

A student's top five average is calculated by taking the sum of their top five scores and dividing it by 5 using integer division.

 

Example 1:

Input: items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
Output: [[1,87],[2,88]]
Explanation: 
The student with ID = 1 got scores 91, 92, 60, 65, 87, and 100. Their top five average is (100 + 92 + 91 + 87 + 65) / 5 = 87.
The student with ID = 2 got scores 93, 97, 77, 100, and 76. Their top five average is (100 + 97 + 93 + 77 + 76) / 5 = 88.6, but with integer division their average converts to 88.
Example 2:

Input: items = [[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100]]
Output: [[1,100],[7,100]]
 

Constraints:

1 <= items.length <= 1000
items[i].length == 2
1 <= IDi <= 1000
0 <= scorei <= 100
For each IDi, there will be at least five scores.

*/
class Solution {
    
    HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
    
    public int[][] highFive(int[][] items) {
        
        for(int it=0; it<items.length; it++) {
            if(map.containsKey(items[it][0])) {
                PriorityQueue queue = map.get(items[it][0]);
                map.get(items[it][0]).offer(items[it][1]);
                if(queue.size() > 5)
                    queue.poll();
            } else {
                PriorityQueue queue = new PriorityQueue<>();
                queue.offer(items[it][1]);
                map.put(items[it][0], queue);
            }
        }
        
        int[][] result = new int[map.keySet().size()][2];
        int it = -1;
        for(Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            int sum = 0;
            PriorityQueue<Integer> queue = map.get(entry.getKey());
            while(!queue.isEmpty()) {
                sum += queue.poll();
            }
            ++it;
            result[it][0] = entry.getKey();
            result[it][1] = sum / 5;
        }
        
        return result;
    }
}