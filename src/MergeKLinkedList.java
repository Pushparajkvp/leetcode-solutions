/**

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

 

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 

Constraints:

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length won't exceed 10^4.

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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];
        
        int space = 1;
        while(space < lists.length) {
            for(int it=0; it+space<lists.length; it += (space*2)) {
                ListNode ref = merge(lists[it], lists[it+space]);
                lists[it] = ref;
            }
            space *= 2;  
        }
        
        /*for(int it=1; it<lists.length; it++) {
            lists[it] = merge(lists[it], lists[it-1]);
        }*/
        return lists[0];
    }
    
    private ListNode merge(ListNode one, ListNode two) {
        ListNode preHead = new ListNode();
        ListNode prev = preHead;
        while(one != null && two != null) {          
            if(one.val <= two.val) {
                prev.next = one;
                one = one.next;
            } else {
                prev.next = two;
                two = two.next;
            }
            
            prev = prev.next;
        }
        
        prev.next = one == null ? two : one;
        
        return preHead.next;
    }
}