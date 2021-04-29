/*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

 

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
Example 3:

Input: nums1 = [0,0], nums2 = [0,0]
Output: 0.00000
Example 4:

Input: nums1 = [], nums2 = [1]
Output: 1.00000
Example 5:

Input: nums1 = [2], nums2 = []
Output: 2.00000
 

Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
 

Follow up: The overall run time complexity should be O(log (m+n)).
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        if(nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        
        int low = 0, high = nums1.length, total = nums1.length + nums2.length;
        
        while(low <= high) {
            int part1 = low + ((high - low) >> 1);
            int part2 = ((total + 1) >> 1) - part1;
            
            int left1 = getLeft(nums1, part1);
            int right1 = getRight(nums1, part1);
            
            int left2 = getLeft(nums2, part2);
            int right2 = getRight(nums2, part2);
            
            if(left1 <= right2 && left2 <= right1) {
                if(total % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    return Math.max(left1, left2);
                }
            }
            
            if(left1 > right2) {
                high = part1 - 1;
            } else {
                low = part1 + 1;
            }
        }
        
        return -1;
    }
    
    private int getLeft(int[] arr, int partition) {
        if(partition == 0)
            return Integer.MIN_VALUE;
        return arr[partition - 1];
    }
    
    private int getRight(int[] arr, int partition) {
        if(partition == arr.length)
            return Integer.MAX_VALUE;
        return arr[partition];
    }
}