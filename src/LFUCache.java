/*
Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.

 

Example 1:

Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[3,4], cnt(4)=2, cnt(3)=3
 

Constraints:

0 <= capacity, key, value <= 104
At most 105 calls will be made to get and put.
 

Follow up: Could you do both operations in O(1) time complexity?

*/
class LFUCache {
    
    private class Node{
        public int key, value, count;
        public Node next, prev;
        
        public Node(int key, int value, int count) {
            this.key = key;
            this.value = value;
            this.count = count;
        }
    }
    
    
    HashMap<Integer, Node> map;
    TreeMap<Integer, Node> cache;
    int capacity;
    //int minCount;
    
    public LFUCache(int capacity) {
        this.map = new HashMap<>();
        this.cache = new TreeMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.count++;
            remove(node, node.count - 1);
            addFront(node, node.count);
            return node.value;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
       
        
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            node.count++;
            remove(node, node.count - 1);
            addFront(node, node.count);
        } else {
            if(map.size() == capacity) {
                Map.Entry<Integer, Node> entry = cache.firstEntry();
                if(entry != null) {
                    Node toRemove = entry.getValue();
                    while(toRemove.next != null)
                        toRemove = toRemove.next;
                    remove(toRemove, entry.getKey());
                    map.remove(toRemove.key);
                } else {
                    return;
                }
            }
            Node node = new Node(key, value, 1);
            addFront(node, node.count);
            map.put(key, node);
        }
    }
    
    
    private void remove(Node node, int count) {
        if(node == null)
            return;
        
        if(node == cache.get(count)) {
            removeFront(count);
            return;
        }
        
        if(node.prev != null)
            node.prev.next = node.next;
        if(node.next != null)
            node.next.prev = node.prev;
        
        node.next = null;
        node.prev = null;
    }

    
    private void addFront(Node node, int count) {
        if(node == null)
            return;
        
        Node head = cache.get(count);
        
        if(head != null) {
            node.next = head;
            head.prev = node;
        } else {
            node.prev = null;
            node.next = null;
        }
        cache.put(count, node);
    }
    
    private void removeFront(int count) {
        Node head = cache.get(count);
        
        if(head == null)
            return;
        
        Node next = head.next;
        if(next != null) {
            next.prev = null;
            cache.put(count, next);
        } else {
            cache.remove(count);
        }
            
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */