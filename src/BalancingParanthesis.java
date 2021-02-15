/*
Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].
*/
class Solution {
    public boolean checkValidString(String s) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> starStack = new Stack<>();
        
        char[] arr = s.toCharArray();
        for(int it=0; it<arr.length; it++) {
            if(arr[it] == '(') {
                stack.push(it);
            } else if(arr[it] == ')') {
                if(!stack.isEmpty())
                    stack.pop();
                else if(!starStack.isEmpty())
                    starStack.pop();
                else 
                    return false;
            } else if(arr[it] == '*') {
                starStack.push(it);
            }
        }
        
        while(!stack.isEmpty()) {
            if(starStack.isEmpty())
                return false;
            
            int index = stack.pop();
            if(index < starStack.peek()) {
                starStack.pop();
            } else {
                return false;
            }
        }
        
        return true;
    }
}