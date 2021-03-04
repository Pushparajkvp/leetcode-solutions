/*
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.

The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:

lefti is the x coordinate of the left edge of the ith building.
righti is the x coordinate of the right edge of the ith building.
heighti is the height of the ith building.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.

Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]

 

Example 1:


Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
Explanation:
Figure A shows the buildings of the input.
Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
Example 2:

Input: buildings = [[0,2,3],[2,5,3]]
Output: [[0,3],[5,0]]
 

Constraints:

1 <= buildings.length <= 104
0 <= lefti < righti <= 231 - 1
1 <= heighti <= 231 - 1
buildings is sorted by lefti in non-decreasing order.
*/
class Solution {
    
    static class Vertex {
        int x;          // building left/right coordinate
        int y;          // building height
        boolean remove; // whether height should be removed from priorityQueue
        
        Vertex(int x, int y, boolean remove) {
            this.x = x;
            this.y = y;
            this.remove = remove;
        }
    }
    
    // O(n^2) worst
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<Vertex> vertex = new ArrayList<>();
        
        for (int[] building : buildings) {
            // add left & right vertex
            vertex.add(new Vertex(building[0], building[2], false));
            vertex.add(new Vertex(building[1], building[2], true));
        }
        
        // sort by `Vertex.x`
        Collections.sort(vertex, (a, b) -> (a.x - b.x));
        
        // use priority queue as max heap
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> (b - a));
        
        List<List<Integer>> output = new ArrayList<>();
        int lastY = 0;
        for (int i = 0; i < vertex.size(); ++i) {
            Vertex v = vertex.get(i);
            
            if (!v.remove) {
                q.offer(v.y); // add current building's height to heap
            } else {
                q.remove(v.y); // remove height from heap
            }
            
            if (i+1 < vertex.size()
                    && vertex.get(i+1).x == v.x) {
                // next `x` is the same, put off
                continue;
            }
            
            // get height of current coordinate
            int y = q.isEmpty() ? 0 : q.peek();
            
            // height changed, found `key point`
            if (y != lastY) {
                List<Integer> keyPoint = new ArrayList<>();
                keyPoint.add(v.x);
                keyPoint.add(y);
                
                output.add(keyPoint);
                lastY = y;
            }
        }
        return output;
    }
}