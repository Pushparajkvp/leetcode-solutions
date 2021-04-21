/*
Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

 

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 

Constraints:

1 <= s.length, p.length <= 3 * 104
s and p consist of lowercase English letters.

*/
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> result = new ArrayList<>();
        
        int len = p.length();
        int[] windowDict = new int[26];
        int[] dict = new int[26];
        
        for(int it=0; it<p.length(); it++)
            dict[(int)(p.charAt(it) - 'a')]++;
        
        for(int it=0; it<s.length(); it++) {
            
            windowDict[(int)(s.charAt(it) - 'a')]++;
            if(it >= len) {
                windowDict[(int)(s.charAt(it - len) - 'a')]--;
            }
            
            if(Arrays.equals(windowDict, dict))
                result.add(it - len + 1);
        }
        
        return result;
    }
}