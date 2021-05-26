
/**

 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class Solution {

    int left, right;
    public int solve(Tree root) {
        left = right = 0;
        helper(root, 0);
        return (right - left + 1);
    }

    public void helper(Tree t, int val) {
        left = Math.min(left, val);
        right = Math.max(right, val);
        if (t.left != null)
            helper(t.left, val - 1);
        if (t.right != null)
            helper(t.right, val + 1);
    }
}