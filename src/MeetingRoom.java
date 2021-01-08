/* 
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

 

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1
 

Constraints:

1 <= intervals.length <= 104
0 <= starti < endi <= 106
*/
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length <= 0) return 0;
        
        Arrays.sort(intervals, new Comparator<int[]>() {
           @Override
            public int compare(int[] first, int[] second) {
                return first[0] - second[0];
            }
        });
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(intervals[0][1]);
        for(int it=1; it < intervals.length; it++) {
            
            if(pq.peek()  <= intervals[it][0]) {
                pq.poll();
            }
            
            pq.add(intervals[it][1]);
        }
        return pq.size();
    }
}