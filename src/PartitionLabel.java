/*
A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

 

Example 1:

Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 

Note:

S will have length in range [1, 500].
S will consist of lowercase English letters ('a' to 'z') only.
*/
class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        
        if(S.length() == 0) return result;
        int[] dict = new int[26];
        
        for(int it=0; it<S.length(); it++) {
            dict[S.charAt(it) - 'a'] = it;
        }
        
        int right = Integer.MIN_VALUE;
        int left = 0, start = 0;
        while(left < S.length()) {
            right = Math.max(right, dict[S.charAt(left) - 'a']);
            
            if(right == left) {
                result.add(right - start + 1);
                start = left = left+1;
                right = Integer.MIN_VALUE;
                continue;
            }
            
            left++;
        }
        
        return result;
    }
}