/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

 

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 

Constraints:

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lower-case English letters.
*/
class Solution {
    public String longestCommonPrefix(String[] strs) {
        
        if(strs.length == 0)
            return "";
        
        StringBuilder sb = new StringBuilder();
        
        for(int firstWordIt=0; firstWordIt<strs[0].length(); firstWordIt++) {
            char chr = strs[0].charAt(firstWordIt);
            
            for(int wordIt=1; wordIt<strs.length; wordIt++) {
                if(firstWordIt >= strs[wordIt].length() || strs[wordIt].charAt(firstWordIt) != chr)
                    return sb.toString();
            }
            
            sb.append(chr);
        }
        
        return sb.toString();
    }
}