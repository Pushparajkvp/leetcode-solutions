/*
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
Follow up:
Could you do get and put in O(1) time complexity?

 

Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
 

Constraints:

1 <= capacity <= 3000
0 <= key <= 3000
0 <= value <= 104
At most 3 * 104 calls will be made to get and put.
*/
class LRUCache {
    
    private Node head, tail;
    private int capacity;
    private HashMap<Integer, Node> map;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            moveToHead(node);
            return node.data;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        Node node;
        if(map.containsKey(key)) {
            node = map.get(key);
            node.data = value;
            moveToHead(node);
        } else {
            if(map.size() == this.capacity) {
                map.remove(tail.prev.key);
                removeTail();    
            }
            node = new Node();
            node.data = value;
            node.key = key;
            
            map.put(key, node);
            addFirst(node);
        }
    }
    
    private void addFirst(Node node) {
        head.next.prev = node;
        node.next = head.next;
        
        node.prev = head;
        head.next = node;
    }
    
    private void moveToHead(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        
        addFirst(node);
    }
    
    private void removeTail() {
        if(tail.prev == head)
            return;
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
    }
    
    private class Node{
        public int data, key;
        public Node next, prev;
    }

}