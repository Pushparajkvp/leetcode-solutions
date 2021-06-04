/*
Given a binary tree root, return the number of perfect nodes. A perfect node has two properties:

Has both children
The sum of one subtree is even and the sum of the other subtree is odd
Constraints

0 ≤ n ≤ 100,000 where n is the number of nodes in root
*/
class Solution {

    int count = 0;

    public int solve(Tree root) {
        dfs(root);    
        return count;
    }

    private int dfs(Tree node) {
        if(node == null)
            return 0;
        
        int leftSum = dfs(node.left);
        int rightSum = dfs(node.right);

        if(node.left != null && node.right != null && (leftSum + rightSum)%2 == 1 )
            count++;
        return leftSum + rightSum + node.val;
    }
}

/*
Counting Maximal Value Roots in Binary Tree
Easy

Given a binary tree root, count and return the number of nodes where its value is greater than or equal to the values of all of its descendants.

Constraints

n ≤ 100,000 where n is the number of nodes in root
*/
class Solution {

    int count = 0;
    public int solve(Tree root) {
        dfs(root);
        return count;
    }

    private int dfs(Tree node) {
        if(node == null)
            return Integer.MIN_VALUE;
        
        int leftMax = dfs(node.left);
        int rightMax = dfs(node.right);

        if(node.val >= leftMax && node.val >= rightMax)
            count++;
        
        return Math.max(Math.max(leftMax, rightMax), node.val);
    }
}