class Solution {
    
    int[] aux;
    
    public int[] sortArray(int[] nums) {
        
        //aux = new int[nums.length];
        //mergeSort(nums, 0, nums.length - 1);
        
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    
    private void quickSort(int[] nums, int left, int right) {
        if(left >= right)
            return;
        
        int index = partition(nums, left, right);
        quickSort(nums, left, index - 1);
        quickSort(nums, index + 1, right);
        
    }
    
    private int partition(int[] nums, int low, int high) {
        int pivot = nums[high], left = low - 1, right = high;
        
        while(true) {
            while(++left <= high && nums[left] < pivot);
            while(--right >= low && nums[right] > pivot);
            
            if(left >= right)
                break;
            
            swap(nums, left, right); 
            
        }
        
        swap(nums, left, high);
        return left;
    }
    
    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
    
    private void mergeSort(int[] nums, int left, int right) {
        if(left >= right)
            return;
        
        int mid = left + ((right - left) >> 1);
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }
    
    private void merge(int[] nums, int left, int mid, int right) {
        
        for(int it=left; it<=right; it++)
            aux[it] = nums[it];
        
        int l = left, r = mid + 1;
        for(int it=left; it<=right; it++) {
            if(r > right) nums[it] = aux[l++];
            else if(l > mid) nums[it] = aux[r++];
            else if(aux[l] < aux[r]) nums[it] = aux[l++];
            else nums[it] = aux[r++];
        }
        
        
    }
}