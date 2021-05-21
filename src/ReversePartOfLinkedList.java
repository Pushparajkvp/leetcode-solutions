/*
Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.

 

Example 1:


Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
Example 2:

Input: head = [5], left = 1, right = 1
Output: [5]
 

Constraints:

The number of nodes in the list is n.
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n
 

Follow up: Could you do it in one pass?
*/
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode trav = head, prevStart = null;
        int counter = 0;
        
        while(trav != null) {
            counter++;
            if(counter == left) {
                ListNode newEnd = trav, prev = prevStart;
                
                while(trav != null && counter <= right) {
                    ListNode next = trav.next;
                    trav.next = prev;
                    prev = trav;
                    trav = next;
                    counter++;
                }
                if(prevStart != null)
                    prevStart.next = prev;
                newEnd.next = trav;
                if(left == 1)
                    return prev;
                break;
            }
            prevStart = trav;
            trav = trav.next;
        }
        return head;
    }
}