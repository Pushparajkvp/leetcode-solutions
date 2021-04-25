/*
Given an alphanumeric string s, return the second largest numerical digit that appears in s, or -1 if it does not exist.

An alphanumeric string is a string consisting of lowercase English letters and digits.

 

Example 1:

Input: s = "dfa12321afd"
Output: 2
Explanation: The digits that appear in s are [1, 2, 3]. The second largest digit is 2.
Example 2:

Input: s = "abc1111"
Output: -1
Explanation: The digits that appear in s are [1]. There is no second largest digit. 
 

Constraints:

1 <= s.length <= 500
s consists of only lowercase English letters and/or digits.
*/
class Solution {
    public int secondHighest(String s) {
        int result = -1, max = -1;
        for(int it=0; it<s.length(); it++) {
            char letter = s.charAt(it);
            if(Character.isDigit(letter)) {
                int digit = (int)(letter - '0');
                if(digit > max) {
                    result = max;
                    max = digit;
                } else if(digit > result && digit != max) {
                    result = digit;
                }
            }
        }
        
        return result;
    }
}