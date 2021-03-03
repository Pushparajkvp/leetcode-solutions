/**

Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
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

/*
    [3] isLeft = true
    [20, 9] isLeft = false 
    [9, 7, 15] isLeft = false
    [10, 11, 7, 15] isLeft = true
    [7] isLeft = true
    []
*/

//Using level list and queue
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        
        if(root == null)
            return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        list.add(root.val);
        result.add(list);
        boolean leftToRight = false;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            list = new LinkedList<>();
            
            
            for(int it=0; it<size; it++) {
                TreeNode node = queue.poll();
               
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
                
                if(leftToRight) {
                    if(node.left != null) list.add(node.left.val);
                    if(node.right != null) list.add(node.right.val);
                } else {
                    if(node.left != null) list.addFirst(node.left.val);
                    if(node.right != null) list.addFirst(node.right.val);
                }
            }
            
            if(!list.isEmpty()) result.add(list);
            leftToRight = !leftToRight;
        }
        
        return result;
    }
}