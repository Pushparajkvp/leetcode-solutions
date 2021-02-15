/*
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
*/
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] dic = new int[26];
        for(int it=0; it<order.length(); it++)
            dic[order.charAt(it)-'a'] = it;
        
        for(int it=1; it<words.length; it++) {
            for(int j=0;; j++) {
                if(j>=words[it-1].length() || j>=words[it].length()) {
                    if(words[it-1].length() > words[it].length())
                        return false;
                    break;
                }
                char leftChar = words[it-1].charAt(j);
                char rightChar = words[it].charAt(j);
                if(dic[leftChar-'a'] > dic[rightChar-'a'])
                    return false;
                else if(dic[leftChar - 'a'] < dic[rightChar - 'a'])
                    break;
                else
                    continue;
            }
        }
        return true;
    }
    
}