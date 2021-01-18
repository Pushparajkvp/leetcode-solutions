/*
Given a string s of '(' , ')' and lowercase English characters. 

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 

Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"
 

Constraints:

1 <= s.length <= 10^5
s[i] is one of  '(' , ')' and lowercase English letters.
*/

//Stack solution
class Solution {
    private Stack<Integer> stack = new Stack<>();
    private HashSet<Integer> set = new HashSet<>();
    
    public String minRemoveToMakeValid(String s) {
        for(int it=0; it<s.length() ; it++) {
            char chr = s.charAt(it);
            if(chr == '(' || chr == ')') {
                if(stack.isEmpty()) {
                    stack.push(it);
                    continue;
                }
                char stackTop = s.charAt(stack.peek());
                if(stackTop == '(' && chr == ')')
                    stack.pop();
                else
                    stack.push(it);
            }
        }
        while(!stack.isEmpty())
            set.add(stack.pop());
        
        StringBuilder sb = new StringBuilder();
        
        for(int it=0; it<s.length(); it++) {
            if(!set.contains(it))
                sb.append(s.charAt(it));
        }
        return sb.toString();
    }
}

//Two Parse method
class Solution {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int opens = 0, balance = 0;
        
        for(int it=0; it<s.length(); it++) {
            char chr = s.charAt(it);
            if(chr == '(') {
                opens++;
                balance++;
            } else if(chr == ')') {
                if(balance <= 0) continue;
                balance--;
            }
            sb.append(chr);
        }
        
        if(balance == 0) return sb.toString();
        
        StringBuilder result = new StringBuilder();
        int retain = opens - balance;
        for(int it=0; it<sb.length(); it++) {
            char chr = sb.charAt(it);
            if(chr == '(') {
                retain--;
                if(retain < 0)
                    continue;
            }
            result.append(chr);
        }

        return result.toString();
    }
}