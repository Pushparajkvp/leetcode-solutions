/**

Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

 

Example 1:


Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]
Example 2:


Input: root = [2,1,1]
Output: [[1]]
Example 3:


Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]
 

Constraints:

The number of the nodes in the tree will be in the range [1, 10^4]
-200 <= Node.val <= 200

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
    
    HashMap<String, Boolean> map = new HashMap<>();
    ArrayList<TreeNode> result = new ArrayList<>();
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return result;
    }
    
    private String dfs(TreeNode node) {
        
        if(node == null)
            return null;
        
        StringBuilder sb = new StringBuilder();
        if(node.left != null) {
            sb.append(dfs(node.left));
            sb.append(":");
        }
        
        sb.append(node.val);

        if(node.right != null) {
            sb.append(",");
            sb.append(dfs(node.right));
        }
        
        String str = sb.toString();
        if(map.containsKey(str)) {
            if(!map.get(str)) {
                map.put(str, true);
                result.add(node);
            }
        } else {
            map.put(str, false);
        }
        return str;
    }
}