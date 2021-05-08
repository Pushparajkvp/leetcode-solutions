/*
A string is considered beautiful if it satisfies the following conditions:

Each of the 5 English vowels ('a', 'e', 'i', 'o', 'u') must appear at least once in it.
The letters must be sorted in alphabetical order (i.e. all 'a's before 'e's, all 'e's before 'i's, etc.).
For example, strings "aeiou" and "aaaaaaeiiiioou" are considered beautiful, but "uaeio", "aeoiu", and "aaaeeeooo" are not beautiful.

Given a string word consisting of English vowels, return the length of the longest beautiful substring of word. If no such substring exists, return 0.

A substring is a contiguous sequence of characters in a string.

 

Example 1:

Input: word = "aeiaaioaaaaeiiiiouuuooaauuaeiu"
Output: 13
Explanation: The longest beautiful substring in word is "aaaaeiiiiouuu" of length 13.
Example 2:

Input: word = "aeeeiiiioooauuuaeiou"
Output: 5
Explanation: The longest beautiful substring in word is "aeiou" of length 5.
Example 3:

Input: word = "a"
Output: 0
Explanation: There is no beautiful substring, so return 0.
 

Constraints:

1 <= word.length <= 5 * 105
word consists of characters 'a', 'e', 'i', 'o', and 'u'.
*/
class Solution {
    public int longestBeautifulSubstring(String word) {
        boolean[] windowFreq = new boolean[26];
        int size = 0, windowStart = 0;
        
        int max = 0;
        
        for(int windowEnd=0; windowEnd < word.length(); windowEnd++) {
            char chr = word.charAt(windowEnd);
           
            if(!windowFreq[chr - 'a'])
                size++;
            windowFreq[chr - 'a'] = true;
            
            
            if(windowEnd != windowStart && word.charAt(windowEnd - 1) > chr) {
                windowStart = windowEnd;
                Arrays.fill(windowFreq, false);
                size = 1;
                windowFreq[chr - 'a'] = true;
            }
            
            if(size == 5) {
                max = Math.max(max, windowEnd - windowStart + 1);
            }
            
        }
    
        return max;
    }             
                    
}