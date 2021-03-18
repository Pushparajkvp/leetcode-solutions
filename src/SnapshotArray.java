/*
Implement a SnapshotArray that supports the following interface:

SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
void set(index, val) sets the element at the given index to be equal to val.
int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 

Example 1:

Input: ["SnapshotArray","set","snap","set","get"]
[[3],[0,5],[],[0,6],[0,0]]
Output: [null,null,0,null,5]
Explanation: 
SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
snapshotArr.set(0,5);  // Set array[0] = 5
snapshotArr.snap();  // Take a snapshot, return snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 

Constraints:

1 <= length <= 50000
At most 50000 calls will be made to set, snap, and get.
0 <= index < length
0 <= snap_id < (the total number of times we call snap())
0 <= val <= 10^9

*/

//Using treemap
class SnapshotArray {

    int snapId = 0;
    
    TreeMap<Integer, Integer>[] arr;
    
    //O(1)
    public SnapshotArray(int length) {
        arr = new TreeMap[length];
    }
    
    //O(logn)
    public void set(int index, int val) {
        if(arr[index] == null) {
            arr[index] = new TreeMap<>();
            arr[index].put(0, 0);
        }
        arr[index].put(snapId, val);
    }
    
    //O(1)
    public int snap() {
        snapId++;
        return snapId - 1;
    }
    
    //O(logn)
    public int get(int index, int snap_id) {
        if(arr[index] == null) {
            return 0;
        }
        snap_id = arr[index].floorKey(snap_id);
        return arr[index].get(snap_id);
    }
}

//using hashmap
class SnapshotArray {
    
    private Map<Integer,Map<Integer,Integer>> snapShotLookUp;
    private int snap_id;

    //O(1)
    public SnapshotArray(int length) {
        snapShotLookUp = new HashMap();
        snap_id = 0;
    }
    
    //O(1)
    public void set(int index, int val) {
        Map<Integer,Integer> currentIndexSnap = snapShotLookUp.getOrDefault(index, new HashMap());
        currentIndexSnap.put(snap_id,val);
        snapShotLookUp.put(index,currentIndexSnap);
        
    }
    
    public int snap() {
        return ++snap_id-1;
    }
    
    //O(n) amortised O(1)
    public int get(int index, int snap_id) {
        if (snapShotLookUp.containsKey(index)) {
            Map<Integer,Integer> currentIndexSnap = snapShotLookUp.get(index);
            while (!currentIndexSnap.containsKey(snap_id) &&  snap_id > 0) {
                snap_id--;
            }
            if (!currentIndexSnap.containsKey(snap_id)) {
                return 0;
            } else {
                return currentIndexSnap.get(snap_id);
            }
        } else {
            return 0;
        }
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */