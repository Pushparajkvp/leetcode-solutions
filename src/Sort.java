/* 
Given an array of integers nums, sort the array in ascending order.

 

Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
 

Constraints:

1 <= nums.length <= 5 * 104
-5 * 104 <= nums[i] <= 5 * 104

*/

class Solution {
    public int[] sortArray(int[] nums) {
        //selectionSort(nums);
        //insertionSort(nums);
        //mergeSort(nums);    
        //quickSort(nums);
        heapSort(nums);
        return nums;
    }
    
    
    private void heapSort(int[] nums) {
        ArrayList<Integer> heap = heapify(nums);
        int index = -1;
        while(!heap.isEmpty())
            nums[++index] = poll(heap);
    }
    
    private ArrayList<Integer> heapify(int[] nums) {
        ArrayList<Integer> heap = new ArrayList<>();
        for(int it=0; it<nums.length; it++)
            heap.add(nums[it]);
        
        for(int it=Math.max(0, nums.length/2 - 1); it >= 0; it--) {
            sink(heap, it);
        }
        return heap;
    }
    
    private int poll(ArrayList<Integer> heap) {
        if(heap.size() == 0) return -1;
        
        Collections.swap(heap, 0, heap.size() - 1);
        int returnVal = heap.remove(heap.size() - 1);
        sink(heap, 0);
        return returnVal;
    }
    
    private void sink(ArrayList<Integer> heap, int index) {
        if(index < 0 || index >= heap.size())
            return;
        
        while(index < heap.size()/2) {
                        
            int leftIndex = 2*index + 1;
            int rightIndex = 2*index + 2;
            
            int smallest = index;
            
            if(leftIndex < heap.size() && heap.get(leftIndex) < heap.get(smallest)) smallest = leftIndex;
            if(rightIndex < heap.size() && heap.get(rightIndex) < heap.get(smallest)) smallest = rightIndex;
            
            if(smallest == index)
                return;
            
            Collections.swap(heap, smallest, index);
            
            index = smallest;
        }
    }
    
    private void swim(ArrayList<Integer> heap, int index) {
        if(index < 0 || index >= heap.size())
            return;
        
        while(index > 0) {
            int parent = (index - 1) / 2;
            if(heap.get(parent) > heap.get(index))
                Collections.swap(heap, parent, index);
            else
                break;
            index = parent;
        }
        
    }
    
    private void quickSort(int[] nums) {
        quickSortRecur(nums, 0, nums.length - 1);
    }
    
    private void quickSortRecur(int[] nums, int start, int end) {
        if(start >= end) return;
        
        int partitionIndex = partition(nums, start, end);
        
        quickSortRecur(nums, start, partitionIndex - 1);
        quickSortRecur(nums, partitionIndex + 1, end);
        
    }
    
    private int partition(int[] nums, int start, int end) {
        int partitionVal = nums[end];
        
        int left = start, right = end - 1;
        while(true) {
            while(left <= right && nums[left] < partitionVal) left++;
            while(left <= right && nums[right] > partitionVal) right--;
            
            if(left >= right)
                break;
            
            swap(nums, left, right);
        }
        
        swap(nums, left, end);
        
        return left;
    }
    
    private void mergeSort(int[] nums) {
        int[] aux = new int[nums.length];
        mergeRecur(nums, aux, 0, nums.length - 1);
    }
    
    private void mergeRecur(int[] nums, int[] aux, int start, int end) {
        if(end <= start) return;
        
        int mid = start + ((end - start) >> 1);
        
        mergeRecur(nums, aux, start, mid);
        mergeRecur(nums, aux, mid + 1, end);
        
        merge(nums, aux, start, end, mid);
    }
    
    private void merge(int[] nums, int[] aux, int start, int end, int mid) {
        
        for(int it=start; it<=end; it++)
            aux[it] = nums[it];
        
        int left = start, right = mid + 1;
        for(int it=start; it <= end; it++) {
            if(left > mid) nums[it] = aux[right++];
            else if(right > end) nums[it] = aux[left++];
            else if(aux[left] < aux[right]) nums[it] = aux[left++];
            else nums[it] = aux[right++];
        }
    }
    
    private void insertionSort(int[] nums) {
        for(int it=1; it<nums.length; it++) {
            for(int j=it; j > 0; j--) {
                if(nums[j - 1] > nums[j])
                    swap(nums, j - 1, j);
            }
        }    
    }
    
    private void selectionSort(int[] nums) {
        
        for(int it=0; it<nums.length; it++) {
            
            int selectedIndex = it;
            
            for(int j= it + 1; j < nums.length; j++) {
                if(nums[j] < nums[selectedIndex]) selectedIndex = j;
            }
            
            swap(nums, selectedIndex, it);
        }
    }
    
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}class Solution {
    public int[] sortArray(int[] nums) {
        //selectionSort(nums);
        //insertionSort(nums);
        //mergeSort(nums);    
        //quickSort(nums);
        heapSort(nums);
        return nums;
    }
    
    
    private void heapSort(int[] nums) {
        ArrayList<Integer> heap = heapify(nums);
        int index = -1;
        while(!heap.isEmpty())
            nums[++index] = poll(heap);
    }
    
    private ArrayList<Integer> heapify(int[] nums) {
        ArrayList<Integer> heap = new ArrayList<>();
        for(int it=0; it<nums.length; it++)
            heap.add(nums[it]);
        
        for(int it=Math.max(0, nums.length/2 - 1); it >= 0; it--) {
            sink(heap, it);
        }
        return heap;
    }
    
    private int poll(ArrayList<Integer> heap) {
        if(heap.size() == 0) return -1;
        
        Collections.swap(heap, 0, heap.size() - 1);
        int returnVal = heap.remove(heap.size() - 1);
        sink(heap, 0);
        return returnVal;
    }
    
    private void sink(ArrayList<Integer> heap, int index) {
        if(index < 0 || index >= heap.size())
            return;
        
        while(index < heap.size()/2) {
                        
            int leftIndex = 2*index + 1;
            int rightIndex = 2*index + 2;
            
            int smallest = index;
            
            if(leftIndex < heap.size() && heap.get(leftIndex) < heap.get(smallest)) smallest = leftIndex;
            if(rightIndex < heap.size() && heap.get(rightIndex) < heap.get(smallest)) smallest = rightIndex;
            
            if(smallest == index)
                return;
            
            Collections.swap(heap, smallest, index);
            
            index = smallest;
        }
    }
    
    private void swim(ArrayList<Integer> heap, int index) {
        if(index < 0 || index >= heap.size())
            return;
        
        while(index > 0) {
            int parent = (index - 1) / 2;
            if(heap.get(parent) > heap.get(index))
                Collections.swap(heap, parent, index);
            else
                break;
            index = parent;
        }
        
    }
    
    private void quickSort(int[] nums) {
        quickSortRecur(nums, 0, nums.length - 1);
    }
    
    private void quickSortRecur(int[] nums, int start, int end) {
        if(start >= end) return;
        
        int partitionIndex = partition(nums, start, end);
        
        quickSortRecur(nums, start, partitionIndex - 1);
        quickSortRecur(nums, partitionIndex + 1, end);
        
    }
    
    private int partition(int[] nums, int start, int end) {
        int partitionVal = nums[end];
        
        int left = start, right = end - 1;
        while(true) {
            while(left <= right && nums[left] < partitionVal) left++;
            while(left <= right && nums[right] > partitionVal) right--;
            
            if(left >= right)
                break;
            
            swap(nums, left, right);
        }
        
        swap(nums, left, end);
        
        return left;
    }
    
    private void mergeSort(int[] nums) {
        int[] aux = new int[nums.length];
        mergeRecur(nums, aux, 0, nums.length - 1);
    }
    
    private void mergeRecur(int[] nums, int[] aux, int start, int end) {
        if(end <= start) return;
        
        int mid = start + ((end - start) >> 1);
        
        mergeRecur(nums, aux, start, mid);
        mergeRecur(nums, aux, mid + 1, end);
        
        merge(nums, aux, start, end, mid);
    }
    
    private void merge(int[] nums, int[] aux, int start, int end, int mid) {
        
        for(int it=start; it<=end; it++)
            aux[it] = nums[it];
        
        int left = start, right = mid + 1;
        for(int it=start; it <= end; it++) {
            if(left > mid) nums[it] = aux[right++];
            else if(right > end) nums[it] = aux[left++];
            else if(aux[left] < aux[right]) nums[it] = aux[left++];
            else nums[it] = aux[right++];
        }
    }
    
    private void insertionSort(int[] nums) {
        for(int it=1; it<nums.length; it++) {
            for(int j=it; j > 0; j--) {
                if(nums[j - 1] > nums[j])
                    swap(nums, j - 1, j);
            }
        }    
    }
    
    private void selectionSort(int[] nums) {
        
        for(int it=0; it<nums.length; it++) {
            
            int selectedIndex = it;
            
            for(int j= it + 1; j < nums.length; j++) {
                if(nums[j] < nums[selectedIndex]) selectedIndex = j;
            }
            
            swap(nums, selectedIndex, it);
        }
    }
    
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}