/*
Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.

 

Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
 

Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.
*/

//DP with memo
class Solution {
    
    private int max(List<String> arr, int currChars, int index, HashMap<String, Integer> memo) {
        if(index == arr.size())
            return 0;
        
        String key = index + "," + currChars;
        if(memo.containsKey(key))
            return memo.get(key);
        
        boolean isValid = true;
        
        String curr = arr.get(index);
        int currUsedChar = 0;
        for(int it=0; it<curr.length(); it++) {
            int normalChar = 1 << (curr.charAt(it) - 'a');
            if((currUsedChar & normalChar) == 0) {
                currUsedChar |= normalChar;
            } else {
                isValid = false;
                break;
            }
        }
        
        int result = max(arr, currChars, index+1, memo);
        
        if(isValid && ((currChars & currUsedChar) == 0)) {
            result = Math.max(result, 
                              curr.length() + max(arr, (currChars | currUsedChar), index+1, memo));
        }
        
        memo.put(key, result);
        return result;
    }
    
    public int maxLength(List<String> arr) {
        return max(arr, 0, 0, new HashMap<>());
    }
}