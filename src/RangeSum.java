/*
Given an array nums and two types of queries where you should update the value of an index in the array, and retrieve the sum of a range in the array.

Implement the NumArray class:

NumArray(int[] nums) initializes the object with the integer array nums.
void update(int index, int val) updates the value of nums[index] to be val.
int sumRange(int left, int right) returns the sum of the subarray nums[left, right] (i.e., nums[left] + nums[left + 1], ..., nums[right]).
 

Example 1:

Input
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
Output
[null, 9, null, 8]

Explanation
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 9 = sum([1,3,5])
numArray.update(1, 2);   // nums = [1,2,5]
numArray.sumRange(0, 2); // return 8 = sum([1,2,5])
 

Constraints:

1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
0 <= index < nums.length
-100 <= val <= 100
0 <= left <= right < nums.length
At most 3 * 104 calls will be made to update and sumRange.
*/

//Fenwick tree
class NumArray {
    
    int bit[];
    
    private int lsb(int num) {
        return num & -num;
    }
    
    private int prefixSum(int index) {
        index++;
        
        int sum = 0;
        while(index > 0) {
            sum += bit[index];
            index -= lsb(index);
        }
        
        return sum;
    }
    
    private void add(int index, int val) {
        index++;
        while(index < bit.length) {
            bit[index] += val;
            index += lsb(index);
        }
    }
    
    public NumArray(int[] nums) {
        this.bit = new int[nums.length + 1];
        this.bit[0] = 0;
        
        for(int it=0; it < nums.length; it++) {
            this.bit[it+1] = nums[it];
        }
        
        for(int it=0; it<nums.length; it++) {
            int parent = (it+1) + lsb(it + 1);
            if(parent <= nums.length)
                this.bit[parent] += this.bit[it+1];
        }
        
    }
    
    
    public void update(int index, int val) {
        val = val - sumRange(index, index);
        add(index, val);
    }
    
    public int sumRange(int left, int right) {
        if(left == 0) return prefixSum(right);
        return prefixSum(right) - prefixSum(left - 1);
    }
}
