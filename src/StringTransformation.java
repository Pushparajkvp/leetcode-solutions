/*
1153. String Transforms Into Another String
Hard

555

208

Add to List

Share
Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.

In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.

Return true if and only if you can transform str1 into str2.

 

Example 1:

Input: str1 = "aabcc", str2 = "ccdee"
Output: true
Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
Example 2:

Input: str1 = "leetcode", str2 = "codeleet"
Output: false
Explanation: There is no way to transform str1 to str2.
 

Constraints:

1 <= str1.length == str2.length <= 104
str1 and str2 contain only lowercase English letters.
*/
class Solution {
    
    public boolean canConvert(String str1, String str2) {
        if(str1.equals(str2))
            return true;
        
        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        for(int it=0; it<str1.length(); it++) {
            
            if(map.containsKey(str1.charAt(it))) {
                if(map.get(str1.charAt(it)) != str2.charAt(it))
                    return false;
            } else {
                map.put(str1.charAt(it), str2.charAt(it));
                set.add(str2.charAt(it));
            }
            
        }
        
        return set.size() < 26;
    }
}