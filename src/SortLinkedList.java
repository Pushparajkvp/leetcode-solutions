/* 

Given a singly linked list of integers node, sort the nodes by their values in ascending order.

Constraints

n ≤ 100,000 where n is the number of nodes in node

*/
class Solution {

    public LLNode getMiddle(LLNode node) {
        LLNode slow = node, fast = node.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public LLNode sort(LLNode node) {
        if(node == null || node.next == null)
            return node;
        
        LLNode midNode = getMiddle(node);
        LLNode right = midNode.next;
        midNode.next = null;
        LLNode leftHead = sort(node);
        LLNode rightHead = sort(right);

        return merge(leftHead, rightHead);
    }


    public LLNode solve(LLNode node) {
        return sort(node);
    }
    
    public LLNode merge(LLNode left, LLNode right) {
        LLNode preHead = new LLNode();
        LLNode prev = preHead;
        
        while(left != null && right != null) {    
            if(left.val <= right.val) {
                prev.next = left;
                left = left.next;
            } else {
                prev.next = right;
                right = right.next;
            }
            prev = prev.next;
        }
        
        prev.next = left == null ? right : left;
        return preHead.next;
    }
}