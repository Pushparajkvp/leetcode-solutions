/*
A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:

A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
Example 1:

Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:

Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

Output:
1

Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.
*/
class Solution {
    public int minTransfers(int[][] transactions) {
        // Key: person's ID   Value: person's balence after calculation
        // {1 : -5} means person 1 should get $5
        // {2 : 10} means person 2 should pay $10
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] trans : transactions) {
            map.put(trans[0], map.getOrDefault(trans[0], 0) - trans[2]);
            map.put(trans[1], map.getOrDefault(trans[1], 0) + trans[2]);
        }
        
        // Since after we get those amount of balence, person's ID does not affect the final result
        int n = map.size(), i = 0;
        int[] balence = new int[n];
        for (int k : map.keySet()) {
            balence[i++] = map.get(k);
        }
        
        return dfs(0, balence);
    }
    
    private int dfs(int idx, int[] balence) {
        if (idx == balence.length) return 0;                  // all balence are cleared ==> requires 0 operation
        if (balence[idx] == 0) return dfs(idx + 1, balence);  // curr person has balence 0 ==> skip curr person
        
        int res = Integer.MAX_VALUE;
        
        int currDebt = balence[idx];
        
        for (int i = idx+1; i < balence.length; i++) {
            /* Key step 1 :
                if either 1. balence[idx] & balence[i] are both positive or negative 
                          2. balence[idx] | balence[i] has 0 balance 
                then operate between them is meaningless 
            */
            if (balence[i] * currDebt >= 0) continue;
            
            /* Key Step 2 : 
                transfer all balance from balance[idx] to balance[i], i.e., 
                after the transaction, balance[idx] = 0
          
            */
            balence[i] += currDebt;
            res = Math.min(res, 1 + dfs(idx + 1, balence));
            balence[i] -= currDebt;   
            
            
        }
        return res;
    }
}