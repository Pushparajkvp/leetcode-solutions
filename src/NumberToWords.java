/*
Convert a non-negative integer num to its English words representation.

 

Example 1:

Input: num = 123
Output: "One Hundred Twenty Three"
Example 2:

Input: num = 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: num = 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: num = 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 

Constraints:

0 <= num <= 231 - 1
*/
class Solution {
    public String numberToWords(int num) {
        if(num == 0) return "Zero";
        int billion = (int)num/(int)Math.pow(10,9);
        int million = (int)(num%Math.pow(10,9))/(int)Math.pow(10,6);
        int thousand = (int)(num%Math.pow(10,6))/(int)Math.pow(10,3);
        int rest = num%1000;
        StringBuilder sb = new StringBuilder();
        
        if(billion > 0) {
            sb.append(three(billion)).append(" Billion ");
        }
        if(million > 0) {
            sb.append(three(million)).append(" Million ");
        }
        
        if(thousand > 0) {
            sb.append(three(thousand)).append(" Thousand ");
        }
        
        if(rest > 0) {
            sb.append(three(rest));
        }
        
        return sb.toString().trim();
    }
    private String one(int num) {
        switch(num) {
            case 0: return "Zero";
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
        }
        return "";
    }
    
    private String twoLessThan20(int num) {
        switch(num) {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
        }
        return "";
    }
    private String twos(int num) {
        switch(num) {
            case 2: return "Twenty";
            case 3: return "Thirty";
            case 4: return "Forty";
            case 5: return "Fifty";
            case 6: return "Sixty";
            case 7: return "Seventy";
            case 8: return "Eighty";
            case 9: return "Ninety";
        }
        return "";
    }
    
    private String two(int num) {
        if(num<10)
            return one(num);
        if(num<20)
            return twoLessThan20(num);
        
        int rest = num%10;
        num/=10;
        
        if(rest>0)
            return twos(num) + " " + one(rest);
        return twos(num);
    }
    
    private String three(int num) {
        if(num<100)
            return two(num);
        
        int rest = num%100;
        num/=100;
        
        if(rest>0)
            return one(num) + " Hundred " + two(rest);
        return one(num) + " Hundred";
    }
    
    
}