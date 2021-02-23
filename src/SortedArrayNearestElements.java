/*
Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b
 

Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]
 

Constraints:

1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 104

*/
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int len = arr.length;
        List<Integer> result = new ArrayList<>();
        
        if(arr[0] > x) {
            for(int it=0; it<k && it<len; it++)
                result.add(arr[it]);
            return result;
        } else if(arr[len - 1] < x) {
            for(int it= Math.max(0, len - k); it<len; it++)
                result.add(arr[it]);
            return result;
        } else {
            int left = 0, right = len - 1;
            
            int mid = left + (right - left) / 2;
            while(left <= right) {
                mid = left + (right - left) / 2;
                
                if(arr[mid] > x) {
                    right = mid - 1;
                } else if(arr[mid] < x) {
                    left = mid + 1;
                } else {
                    break;
                }
            }
            
            int position;
            
            if(arr[mid] == x)  {
                position = mid;
            } else {
                position = left - 1;
            }
            left = Math.max(0, position - k - 1);
            right = Math.min(len - 1, position + k + 1);
            
            while(right - left + 1 > k) {
                if((x - arr[left]) <= (arr[right] - x)) {
                    right--;
                } else {
                    left++;
                }
            }
            
            for(int it=left; it<=right; it++)
                result.add(arr[it]);
            return result;
        }
    }
}