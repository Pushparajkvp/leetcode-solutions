/*
Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Example 2:


Input: root = [3,9,8,4,0,1,7]
Output: [[4],[9],[3,0,1],[8],[7]]
Example 3:


Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]
Example 4:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100

 *
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    HashMap<Integer, List<Integer>> map;
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        map = new HashMap();
        
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        
        Queue<Pair<Integer,TreeNode>> queue = new LinkedList<>();
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
        queue.offer(new Pair(0, root));
        
        while(!queue.isEmpty()) {
            Pair<Integer, TreeNode> pair = queue.poll();
            
            int col = pair.getKey();
            TreeNode node = pair.getValue();
            
            if(map.containsKey(col)) {
                map.get(col).add(node.val);
            } else {
                List<Integer> nums = new ArrayList<>();
                nums.add(node.val);
                map.put(col, nums);
            }
            minCol = Math.min(minCol, col);
            maxCol = Math.max(maxCol, col);
            if(node.left != null) queue.offer(new Pair(col-1, node.left));
            if(node.right != null) queue.offer(new Pair(col+1, node.right));
        }
        
        for(;minCol <= maxCol; minCol++) {
            result.add(map.get(minCol));
        }
        
        return result;
    }
}