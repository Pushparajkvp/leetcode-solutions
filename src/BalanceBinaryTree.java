/**
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
 
//Using inorder traversal
class Solution {
    
    ArrayList<TreeNode> inorder = new ArrayList<>();
    
    public TreeNode balanceBST(TreeNode root) {
        dfs(root);
        
        return createTree(0, inorder.size() - 1);
    }
    
    private TreeNode createTree(int left, int right) {
        
        if(left > right)
            return null;
        
        int mid = left + ((right - left) >> 1);
        
        
        TreeNode newNode = new TreeNode(inorder.get(mid).val);
        
        newNode.left = createTree(left, mid - 1);
        newNode.right = createTree(mid + 1, right);
        
        return newNode;
    }
    
    private void dfs(TreeNode node) {
        if(node == null) return;
        
        dfs(node.left);
        inorder.add(node);
        dfs(node.right);
    }
}

//Wrong solution
class Solution {
    
    HashMap<TreeNode, Integer> heights = new HashMap<>();
    TreeNode root;
    public TreeNode balanceBST(TreeNode root) {
        this.root = root;
        update(root);
        return balance(root);
    }
    
    
    private TreeNode balance(TreeNode node) {
        if(node == null)
            return null;
        
        node.left = balance(node.left);
        node.right = balance(node.right);
        
        int bf = getBF(node);
        
        if(bf > 1) {
            int bfLeft = getBF(node.left);
            if(bfLeft < 0){
                node.left = leftRotation(node.left);
                System.out.print("left ");
            }
            System.out.print("right rotation " + node.val + "\n");
            node = rightRotation(node);
        } else if(bf < -1) {
            int bfRight = getBF(node.right);
            if(bfRight > 0) {
                node.right = rightRotation(node.right);
                System.out.print("right ");
            }
            System.out.print("left rotation " + node.val +"\n");
            node = leftRotation(node);
        }
        
        update(root);
        return node;
    }
    
    private int getBF(TreeNode node) {
        int leftHeight = node.left == null ? 0 : heights.get(node.left);
        int rightHeight = node.right == null ? 0 : heights.get(node.right);
        return leftHeight - rightHeight;
    }
    private int update(TreeNode node) {
        if(node == null) return 0;
        
        int left = update(node.left);
        int right = update(node.right);
        
        int curr = Math.max(left, right) + 1;
        heights.put(node, curr);
        
        return curr;
    }
    private TreeNode rightRotation(TreeNode node) {
        TreeNode nextRoot = node.left;
        node.left = nextRoot.right;
        nextRoot.right = node;
        return nextRoot;
    }
    
    private TreeNode leftRotation(TreeNode node) {
        TreeNode nextRoot = node.right;
        node.right = nextRoot.left;
        nextRoot.left = node;
        return nextRoot;
    }
}