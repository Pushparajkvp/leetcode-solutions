/* 
Given a string s, return the last substring of s in lexicographical order.

 

Example 1:

Input: s = "abab"
Output: "bab"
Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
Example 2:

Input: s = "leetcode"
Output: "tcode"
 

Constraints:

1 <= s.length <= 4 * 105
s contains only lowercase English letters.
*/
class Solution {
    public String lastSubstring(String s) {
        
        char[] chr = s.toCharArray();
        int n = chr.length;
        
        int max = n - 1;
        for(int it=n - 2; it>=0; it--) {
            if(chr[max] < chr[it]) {
                max = it;
            } else if(chr[max] == chr[it]) {
                int x = it, y = max;
                while(x < n && y < n) {
                    if(chr[x] > chr[y]) {
                        max = it;
                        break;
                    }else if(chr[x] < chr[y]) {
                        break;
                    }
                    if(x >= max) {
                        max = it;
                        break;
                    }
                    x++;y++;
                }
                if(y==n) max = it; 
            }
        }
        
        return new String(chr, max, n - max);
    }
}