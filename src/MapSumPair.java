/*
Implement the MapSum class:

MapSum() Initializes the MapSum object.
void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 

Example 1:

Input
["MapSum", "insert", "sum", "insert", "sum"]
[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
Output
[null, null, 3, null, 5]

Explanation
MapSum mapSum = new MapSum();
mapSum.insert("apple", 3);  
mapSum.sum("ap");           // return 3 (apple = 3)
mapSum.insert("app", 2);    
mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 

Constraints:

1 <= key.length, prefix.length <= 50
key and prefix consist of only lowercase English letters.
1 <= val <= 1000
At most 50 calls will be made to insert and sum.
*/


class Node {
    HashMap<Character, Node> map = new HashMap<>();
    int val = 0;
}

class MapSum {
    
    Node root;
    HashMap<String, Integer> map;
    
    public MapSum() {
        root = new Node();
        map = new HashMap<>();
    }
    
    public void insert(String key, int val) {
        
        int diff = val - map.getOrDefault(key, 0);
        Node trav = root;
        map.put(key, val);
        for(int it=0; it<key.length(); it++) {
            char chr = key.charAt(it);
            if(trav.map.containsKey(chr)) {
                trav = trav.map.get(chr);
                trav.val += diff;
            } else {
                Node newNode = new Node();
                newNode.val += diff;
                trav.map.put(chr, newNode);
                trav = newNode;
            }
        }
        
    }
    
    public int sum(String prefix) {
        Node trav = root;
        for(int it=0; it<prefix.length(); it++) {
            char chr = prefix.charAt(it);
            if(trav.map.containsKey(chr)) {
                trav = trav.map.get(chr);
            } else {
                return 0;
            }
        }
        return trav.val;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */