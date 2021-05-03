/*
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 

Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
 

Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 104 calls in total will be made to insert, search, and startsWith.
*/
class Trie {
    
    class Node{
        HashMap<Character, Node> map = new HashMap<>();
        boolean isEnd = false;
    }
    
    private Node root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();    
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node node = root;
        for(char chr : word.toCharArray()) {
            if(node.map.containsKey(chr))
                node = node.map.get(chr);
            else {
                Node newNode = new Node();
                node.map.put(chr, newNode);
                node = newNode;
            }
        }
        node.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node node = root;
        for(char chr : word.toCharArray()) {
            if(node.map.containsKey(chr))
                node = node.map.get(chr);
            else {
                return false;
            }
        }
        
        return node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node node = root;
        for(char chr : prefix.toCharArray()) {
            if(node.map.containsKey(chr))
                node = node.map.get(chr);
            else {
                return false;
            }
        }
        
        if(node.isEnd)
            return true;
        
        if(node.map.size() > 0)
            return true;
        
        return false;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */