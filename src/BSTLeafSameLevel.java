import java.util.*;

/*
Given a binary tree root, return whether all leaves are at the same level.

Constraints

n ≤ 100,000 where n is the number of nodes in root

 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class Solution {

    int leafLevel = -1;

    public boolean solve(Tree root) {
        return dfs(root, 0);
    }

   

    private boolean dfs(Tree node, int level) {
        if(node == null)
            return true;

        if(node.left == null && node.right == null) {
            if(leafLevel == -1) {
                leafLevel = level;
                return true;
            }
            return leafLevel == level;
        }

        return dfs(node.left, level + 1) && dfs(node.right, level + 1);
    }
}