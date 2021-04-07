/*
Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.

Return True if the path crosses itself at any point, that is, if at any time you are on a location you've previously visited. Return False otherwise.

 

Example 1:



Input: path = "NES"
Output: false 
Explanation: Notice that the path doesn't cross any point more than once.
Example 2:



Input: path = "NESWW"
Output: true
Explanation: Notice that the path visits the origin twice.
 

Constraints:

1 <= path.length <= 10^4
path will only consist of characters in {'N', 'S', 'E', 'W}
*/
class Solution {
    public boolean isPathCrossing(String path) {
        HashSet<Pair<Integer, Integer>> set = new HashSet<>();
        
        int currX = 0, currY = 0;
        set.add(new Pair(currX, currY));
        
        for(int it=0; it<path.length(); it++) {
            char chr = path.charAt(it);
            if(chr == 'N')
                currY++;
            else if(chr == 'S')
                currY--;
            else if(chr == 'E')
                currX++;
            else if(chr == 'W')
                currX--;
            
            Pair<Integer, Integer> newPair = new Pair(currX, currY);
            
            if(set.contains(newPair)) {
                return true;
            }
            
            set.add(newPair);
        }
        
        return false;
    }
}