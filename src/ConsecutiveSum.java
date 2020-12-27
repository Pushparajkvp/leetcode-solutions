/*
N -> Sum
X -> Base
K -> number of numbers

N = xk + k(k+1)/2
xk = N - k(k+1)/2
x = N/k - (k+1)/2

x > 0 so assume x is 0 then value of RHS must be greater than 0

0 < N/k - (k+1)/2
-N/k < -(k+1)/2
N/k >= (k+1)/2

2N >= k^2 + k
2N + 1/4 >= (k+1/2)^1/2

(2N + 1/4)^1/2 - 1/2 >= k
*/

class Solution {
    public int consecutiveNumbersSum(int N) {
        int answer = 0;
        int limit = (int)Math.sqrt(2*N + 1/4) - 1/2;
        
        for(int k=1; k<=limit; k++) {
            N-=k;
            
            if(N % k == 0)
                answer++;
        }
        return answer;
    }
}