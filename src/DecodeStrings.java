/* 
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 

Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
Example 4:

Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
 

Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
*/
class Solution {
    
    Stack<Character> stack = new Stack<>();
    
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        
        for(int it=0; it<s.length(); it++) {
            
            char chr = s.charAt(it);
            if(chr != ']') {
                stack.push(chr);
                continue;
            }
            
            //It is closing bracket
            StringBuilder decoded = new StringBuilder();
            while(stack.peek() != '[')
                decoded.append(stack.pop());
            
            stack.pop();
            
            int places = 0;
            int num = 0;
            while(!stack.isEmpty() && Character.isDigit(stack.peek())) {
                num += Math.pow(10, places) * (stack.pop()-'0');
                places++;
            }
            
            while(num-- > 0) {
                for(int i=decoded.length() - 1; i>=0; i--)
                    stack.push(decoded.charAt(i));
            }
        }
        StringBuilder res = new StringBuilder();
        
        while(!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }
}