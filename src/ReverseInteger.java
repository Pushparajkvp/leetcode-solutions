/*
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

 

Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21
Example 4:

Input: x = 0
Output: 0
 

Constraints:

-231 <= x <= 231 - 1
*/
class Solution {
    public int reverse(int x) {
        int result = 0;
        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);
        if(x < 0)
            x = Integer.MAX_VALUE;
        
        while(x != 0) {
            int digit = x % 10;
            if(result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && digit > Integer.MAX_VALUE%10))
                return 0;
            
            result = result * 10 + digit;
            //System.out.println(result);
            x /= 10;
        }
        
        return result*sign;
    }
}