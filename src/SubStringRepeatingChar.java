/*
Given a string s, find the length of the longest substring without repeating characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
Example 4:

Input: s = ""
Output: 0
 

Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int[] dict = new int[128];
        
        for(int start=0,end=0; end < s.length(); end++) {
            start = Math.max(dict[s.charAt(end)], start);
            max = Math.max(max, end - start + 1);
            dict[s.charAt(end)] = end + 1;
        }
        
        return max;
    }
}

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0, currentLen = 0, start = 0;
        
        HashMap<Character, Integer> map = new HashMap<>();
        for(int it=0; it<s.length(); it++) {
            Character chr = s.charAt(it);
            if(map.containsKey(chr)) {
                if(currentLen > maxLen)
                    maxLen = currentLen;
                int index = map.get(chr);
                for(int i=start; i<=index; i++) {
                    map.remove(s.charAt(i));
                    currentLen--;
                }
                start = index+1;
            }
            map.put(chr, it);
            currentLen++;
        }
        if(currentLen > maxLen)
            maxLen = currentLen;
        return maxLen;
    }
}