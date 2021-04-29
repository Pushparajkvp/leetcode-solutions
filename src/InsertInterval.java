/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

 

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
Example 3:

Input: intervals = [], newInterval = [5,7]
Output: [[5,7]]
Example 4:

Input: intervals = [[1,5]], newInterval = [2,3]
Output: [[1,5]]
Example 5:

Input: intervals = [[1,5]], newInterval = [2,7]
Output: [[1,7]]
 

Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= intervals[i][0] <= intervals[i][1] <= 105
intervals is sorted by intervals[i][0] in ascending order.
newInterval.length == 2
0 <= newInterval[0] <= newInterval[1] <= 105
*/
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
        LinkedList<int[]> result = new LinkedList<>();
        int iterator = 0;
        while(iterator < intervals.length && intervals[iterator][1] < newInterval[0])
            result.add(intervals[iterator++]);
        
        if(iterator == intervals.length) {
            result.add(newInterval);
            return result.toArray(new int[result.size()][]);
        }
        
        if(intervals[iterator][0] < newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[iterator][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[iterator][1]);
            result.add(newInterval);

            iterator++;
        } else {
            result.add(newInterval);
        }
        
        
        while(iterator < intervals.length && result.getLast()[1] >= intervals[iterator][0]) {
            result.getLast()[1] = Math.max(result.getLast()[1], intervals[iterator][1]);
            iterator++;
        }
        
        while(iterator < intervals.length)
            result.add(intervals[iterator++]);
        
        return result.toArray(new int[result.size()][]);
    }
}