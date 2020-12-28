/*
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 

Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104

*/
class Solution {
    public int[][] merge(int[][] intervals) {
        
        if(intervals.length == 0) return new int[0][0];
        
        LinkedList<int[]> result = new LinkedList<>();
        
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        
        for(int it=0; it<intervals.length; it++) {
            int[] current = intervals[it];
            
            if(result.isEmpty() || result.getLast()[1] < current[0]) {
                result.add(current);
            } else {
                result.getLast()[1] = Math.max(result.getLast()[1], current[1]);
            }
        }
        
        /*int resultarr[][] = new int[result.size()][2];
        int index = -1;
        for(Iterator<Integer[]> it = result.iterator(); it.hasNext();){
            Integer[] next = it.next();
            resultarr[++index][0] = next[0];
            resultarr[index][1] = next[1];
        }*/
        return result.toArray(new int[result.size()][]);
    }
}