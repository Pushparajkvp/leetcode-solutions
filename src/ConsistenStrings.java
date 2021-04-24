/*
You are given a string allowed consisting of distinct characters and an array of strings words. A string is consistent if all characters in the string appear in the string allowed.

Return the number of consistent strings in the array words.

 

Example 1:

Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
Output: 2
Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.
Example 2:

Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
Output: 7
Explanation: All strings are consistent.
Example 3:

Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
Output: 4
Explanation: Strings "cc", "acd", "ac", and "d" are consistent.
 

Constraints:

1 <= words.length <= 104
1 <= allowed.length <= 26
1 <= words[i].length <= 10
The characters in allowed are distinct.
words[i] and allowed contain only lowercase English letters.

*/
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        int result = 0;
        boolean[] dict = new boolean[26];
        
        for(int it=0; it<allowed.length(); it++)
            dict[allowed.charAt(it) - 'a'] = true;
        
        for(int wordIt=0; wordIt<words.length; wordIt++) {
            boolean isConsistent = true;
            for(int letterIt=0; letterIt<words[wordIt].length(); letterIt++) {
                char letter = words[wordIt].charAt(letterIt);
                if(!dict[letter - 'a']) {
                    isConsistent = false;
                    break;
                }
            }
            if(isConsistent)
                result++;
        }
        
        return result;
    }
}