/*
Given a string s, you can convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

 

Example 1:

Input: s = "aacecaaa"
Output: "aaacecaaa"
Example 2:

Input: s = "abcd"
Output: "dcbabcd"
 

Constraints:

0 <= s.length <= 5 * 104
s consists of lowercase English letters only.
*/
//KMP
class Solution {
    public String shortestPalindrome(String s) {
        
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        sb = new StringBuilder(s + "#" + sb.toString());
        
        int[] kmp = new int[sb.length()];
        
        int it = 1, len = 0;
        
        while(it < sb.length()) {
            
            if(sb.charAt(it) == sb.charAt(len)) {
                len++;
                kmp[it] = len;
                it++;
            } else {
                if(len != 0) {
                    len = kmp[len-1];
                } else {
                    kmp[it] = len;
                    it++;
                }
            }
        }
        
        if(kmp[kmp.length - 1] == s.length())
            return s;
        else {
            String subStr = s.substring(kmp[kmp.length - 1], s.length());
            sb = new StringBuilder(subStr);
            sb.reverse();
            sb.append(s);
            return sb.toString();
        }
    }
}

//Brute Force
class Solution {
    public String shortestPalindrome(String s) {
        
        int len = s.length();
        if(len == 0) 
            return "";
        
        int max = 0;
        char firstLetter = s.charAt(0);
        for(int it=1; it<len; it++) {
            char letter = s.charAt(it);
            
            if(letter == firstLetter) {
                int left = 0, right = it;
                boolean isPalindrome = true;
                while(left <= right) {
                    if(s.charAt(left) != s.charAt(right)) {
                        isPalindrome = false;
                        break;
                    }
                    left++; right--;
                }
                
                if(isPalindrome) {
                    max = Math.max(max, it);
                }
            }
        }
        if(max == len - 1)
            return s;
        
        String rest = s.substring(max+1, len);
        StringBuilder sb = new StringBuilder();
        sb.append(rest);
        sb.reverse();
        sb.append(s);
        return sb.toString();
    }
}