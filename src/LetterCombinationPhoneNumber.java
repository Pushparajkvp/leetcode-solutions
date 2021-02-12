/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



 

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]
 

Constraints:

0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
*/
class Solution {
    private HashMap<Integer, String> map = new HashMap<>();
    private  List<String> result = new ArrayList<>();
    
    public List<String> letterCombinations(String digits) {
       
        if(digits.length() == 0)
            return result;
        
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        
        backtrack("", digits);
        
        return result;
    }
    
    private void backtrack(String curr, String digits) {
        
        if(digits.length() == 0) {
            result.add(curr);
        } else {
            String letters = map.get(digits.charAt(0) - '0');
            String remainingDigits = digits.substring(1);
            for(int it=0; it<letters.length(); it++) {
                char letter = letters.charAt(it);
                backtrack(curr+letter, remainingDigits);
            }
        }
    }

}