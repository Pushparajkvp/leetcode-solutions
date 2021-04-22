/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

 

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output: 
[
[1,2,2],
[5]
]
 

Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
*/
class Solution {
    
    private List<List<Integer>> result = new ArrayList<>();
    
    private void backtrack(int index, LinkedList<Integer> list, int target, int curr, int[] arr) {
        if(curr == target){
            result.add(new ArrayList<>(list));
            return;
        } else if(curr > target) {
            return;
        }
        
        for(int it=index; it<arr.length; it++) {
            //System.out.println(it + " " + index);
            if(it > index && arr[it] == arr[it-1])
                continue;
            list.add(arr[it]);
            curr+=arr[it];
            backtrack(it+1, list, target, curr, arr);
            list.removeLast();
            curr-=arr[it];
        }
    }
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(0, new LinkedList<>(), target, 0, candidates);
        return result;
    }
}