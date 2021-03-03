/**

Given the head of a linked list, remove the nth node from the end of the list and return its head.

Follow up: Could you do this in one pass?

 

Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]
 

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz

 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        
        ListNode trav = head;
        ListNode result = null;
        
        int counter = 0;
        
        while(trav != null) {
            if(counter == n) {
                result = head;
            } else if(counter > n) {
                result = result.next;
            }
            
            counter++;
            trav = trav.next;
        }
        
        if(counter == n) {
            head = head.next;
        } else {
            result.next = result.next.next;
        }
        
        return head;
    }
}