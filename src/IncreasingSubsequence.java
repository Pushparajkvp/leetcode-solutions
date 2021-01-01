
//Solving Subset
class Solution {
    public int lengthOfLIS(int[] nums) {
        int dp[] = new int[nums.length];
        int max = 0;
        
        for(int i=1; i<nums.length; i++) {
            for(int j=0; j<i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        for(int it=0; it<dp.length; it++)
            max = Math.max(max, dp[it]);
        return max+1;
    }
}
//Strictly Increasing
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        //Arrays.fill(dp, Integer.MIN_VALUE);
        int len = 0;
        for(int it=0; it<nums.length; it++) {
            //Binary search
            int left=0, right=len-1, mid;
            boolean found = false;
            while(left <= right) {
                mid = left + (right - left)/2;
                if(nums[it] > dp[mid]) {
                    left = mid+1;
                } else if(nums[it] < dp[mid]) {
                    right = mid-1;
                } else {
                    found = true;
                    break;
                }
            }
            if(!found) {
                dp[left] = nums[it];
                if(left == len)
                    len++;
            }
        }
        return len;
    }
}
//Increasing or equal
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for(int it=0; it<nums.length; it++) {

            //Binary search
            int left=0, right=len-1, mid;
            boolean found = false;
            while(left <= right) {
                mid = left + (right - left)/2;
                if(nums[it] > dp[mid]) {
                    left = mid+1;
                } else if(nums[it] < dp[mid]) {
                    right = mid-1;
                } else {
                    found = true;
                    while(dp[mid] == nums[it]) mid++;
                    dp[mid] = nums[it];
                    if(mid == len)
                        len++;
                    break;
                }
            }
            if(!found) {
                dp[left] = nums[it];
                if(left == len)
                    len++;
            }
        }
        return len;
    }
}
//Decreasing
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        //Arrays.fill(dp, Integer.MIN_VALUE);
        int len = 0;
        for(int it=0; it<nums.length; it++) {
            //Binary search
            int left=0, right=len-1, mid;
            boolean found = false;
            while(left <= right) {
                mid = left + (right - left)/2;
                if(nums[it] > dp[mid]) {
                    right = mid-1;
                } else if(nums[it] < dp[mid]) {
                    left = mid+1;
                } else {
                    found = true;
                    break;
                }
            }
            if(!found) {
                dp[left] = nums[it];
                if(left == len)
                    len++;
            }
        }
        return len;
    }
}