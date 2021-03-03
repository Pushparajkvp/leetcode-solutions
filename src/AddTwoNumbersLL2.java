/**

You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

 //Without reversing
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        int len1 = 0, len2 = 0;
        
        ListNode trav = l1;
        while(trav != null) {
            trav = trav.next;
            len1++;
        }
        
        trav = l2;
        while(trav != null) {
            trav = trav.next;
            len2++;
        }
        
        if(len2 > len1) {
            ListNode temp = l2;
            l2 = l1;
            l1 = temp;
            
            int tmp = len1;
            len1 = len2;
            len2 = tmp;
        }
        
        ListNode head = null;
        
        while(len1 > 0 && len2 > 0) {
            int x1 = l1.val;
            int x2 = len1 == len2 ? l2.val : 0;
            
            int sum = x1 + x2;
            
            ListNode newNode = new ListNode(sum);
            newNode.next = head;
            head = newNode;
            
            if(len1 == len2) {
                len2--;
                l2 = l2.next;
            }
            len1--;
            l1 = l1.next;
        }
        
        trav = head;
        int carry = 0;
        ListNode prev = null;
        while(trav != null) {
            if(trav.val >= 10) {
                if(trav.next == null) {
                    trav.next = new ListNode(1);
                } else {
                    trav.next.val += 1;
                }
                trav.val %= 10;
            }
            
            ListNode next = trav.next;
            trav.next = prev;
            prev = trav;
            trav = next;
        }
        
        return prev;
    }
}