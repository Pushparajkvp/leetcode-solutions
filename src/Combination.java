/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

You may return the answer in any order.

 

Example 1:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
Example 2:

Input: n = 1, k = 1
Output: [[1]]
 

Constraints:

1 <= n <= 20
1 <= k <= n

*/
class Solution {
    private ArrayList<List<Integer>> result = new ArrayList<>();
    
    private void backtrack(int index, LinkedList<Integer> list, int k, int n) {
        if(list.size() == k){
            result.add(new ArrayList<>(list));
            return;
        }
        
        for(int it=index; it<=n; it++) {
            list.add(it);
            backtrack(it+1, list, k, n);
            list.removeLast();
        }
    }
    
    public List<List<Integer>> combine(int n, int k) {
        backtrack(1, new LinkedList<>(), k, n);
        return result;
    }
}

//Combination without duplicates

class SubsetWithDuplicates {

  public static List<List<Integer>> findSubsets(int[] nums) {
    List<List<Integer>> subsets = new ArrayList<>();
    Arrays.sort(nums);
    combinations(subsets, new LinkedList<Integer>(), nums, 0);
    return subsets;
  }

  private static void combinations(List<List<Integer>> subsets, LinkedList<Integer> curr, int[] nums, int index) {
    
    subsets.add(new ArrayList<>(curr));

    for(int it=index; it<nums.length; it++) {
      if(it > index  && nums[it - 1] == nums[it])
        continue;
      curr.add(nums[it]);
      combinations(subsets, curr, nums, it + 1);
      curr.removeLast();
    }
  }
}