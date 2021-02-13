/*
Given an input string , reverse the string word by word. 

Example:

Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
Note: 

A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.
Follow up: Could you do it in-place without allocating extra space?
*/
class Solution {
    public void reverseWords(char[] s) {
        reverse(s, 0, s.length-1);
        
        int start = 0;
        for(int it=0; it<s.length; it++) {
            if(s[it] == ' ') {
                reverse(s, start, it-1);
                start = it+1;
            }
        }
        
        reverse(s, start, s.length-1);
    }
    
    private void reverse(char[] s, int start, int end) {
        while(start < end) {
            swap(s, start, end);
            start++;
            end--;
        }
    }
    
    private void swap(char[] s, int from, int with) {
        char temp = s[from];
        s[from] = s[with];
        s[with] = temp;
    }
}