/*
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1:

Input: "2-1-1"
Output: [0, 2]
Explanation: 
((2-1)-1) = 0 
(2-(1-1)) = 2
Example 2:

Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation: 
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
*/
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        
        if(input.length() == 1) {
            List<Integer> res = new ArrayList<>();
            res.add(Integer.parseInt(input));
            return res;
        }
        
        List<Integer> result = new ArrayList<>();
        for(int it=0; it<input.length(); it++) {
            char ch = input.charAt(it);
            
            if(ch == '+' || ch == '-' || ch == '*') {
                
                List<Integer> left = diffWaysToCompute(input.substring(0, it));
                List<Integer> right = diffWaysToCompute(input.substring(it+1));
                
                for(int leftNum : left) {
                    for(int rightNum : right) {
                        if(ch == '+') {
                            result.add(leftNum + rightNum);
                        } else if(ch == '-') {
                            result.add(leftNum - rightNum);
                        } else {
                            result.add(leftNum * rightNum);
                        }
                     }
                }
            }
        }
        
        if(result.size() == 0) result.add(Integer.parseInt(input));
        return result;
    }
}