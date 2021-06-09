/*
Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.

If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.

 

Example 1:

Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Example 2:

Input: s = "abcd", k = 2
Output: "bacd"
 

Constraints:

1 <= s.length <= 104
s consists of only lowercase English letters.
1 <= k <= 104
*/
class Solution {
    public String reverseStr(String s, int k) {
        char[] strArr = s.toCharArray();
        
        for(int start=0; start < strArr.length; start += 2 * k) {
            int end = Math.min(start + k - 1, strArr.length - 1);
            reverse(strArr, start, end);
        }
        
        
        return new String(strArr);
    }
    
    private void reverse(char[] arr, int start, int end) {
        for(int i=start, j=end; i<j; i++, j--) {
            swap(arr, i, j);
        }
    }
    private void swap(char[] arr, int index1, int index2) {
        char temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}