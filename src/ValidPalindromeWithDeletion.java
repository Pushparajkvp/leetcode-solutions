/* 
Given a string s, return true if the s can be palindrome after deleting at most one character from it.

 

Example 1:

Input: s = "aba"
Output: true
Example 2:

Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.
Example 3:

Input: s = "abc"
Output: false
 

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
*/
class Solution {
    
	public boolean validPalindrome(String s) {
        return isPalindrome(s, 0, s.length() - 1, false);
    }
    
    public boolean isPalindrome(final String s, int leftIndex, int rightIndex, final boolean isCharacterDeleted){
        
        while(leftIndex < rightIndex){
            
            if(s.charAt(leftIndex) != s.charAt(rightIndex)){
                if(isCharacterDeleted)
                return isPalindrome(s, leftIndex + 1, rightIndex, true) || isPalindrome(s, leftIndex, rightIndex - 1, true);
            }
            
            leftIndex++;
            rightIndex--;
            
        }
		
        return true;
    }
}