/*
Given a string s, return the longest palindromic substring in s.

 

Example 1:

Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
Example 3:

Input: s = "a"
Output: "a"
Example 4:

Input: s = "ac"
Output: "a"
 

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters (lower-case and/or upper-case),
*/
class Solution {
    String result = "";
    
    public String longestPalindrome(String s) {
        for(int it=0; it<s.length(); it++) {
            expand(s, it, it);
            expand(s, it, it+1);
        }
        return result;
    }
    
    private void expand(String s, int i, int j) {
        
        while(i >= 0 && j < s.length() && Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j))) {
            i--;
            j++;
        }
        i++;
        j--;
        if(i < 0 || j >= s.length() || i > j) 
            return;
        String palindrome = s.substring(i, j+1);
        if(palindrome.length() > result.length())
            result = palindrome;
    }
}