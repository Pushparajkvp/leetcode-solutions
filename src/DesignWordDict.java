/* 
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 

Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
 

Constraints:

1 <= word.length <= 500
word in addWord consists lower-case English letters.
word in search consist of  '.' or lower-case English letters.
At most 50000 calls will be made to addWord and search.
*/

class Node {
    HashMap<Character, Node> map = new HashMap<>();
    boolean isEnd = false;
}

class WordDictionary {
    
    Node root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }
    
    public void addWord(String word) {
        Node trav = root;
        
        for(int it=0; it < word.length(); it++) {
            char chr = word.charAt(it);
            if(trav.map.containsKey(chr)){
                trav = trav.map.get(chr);
            } else {
                Node newNode = new Node();
                trav.map.put(chr, newNode);
                trav = newNode;
            }
            
            if(it == word.length() - 1)
                trav.isEnd = true;
        }
    }
    
    public boolean search(String word) {
        return recur(word, 0, root);
    }
    
    private boolean recur(String word, int index, Node node) {
        for(int it=index; it<word.length(); it++) {
            char chr = word.charAt(it);
            if(chr == '.') {
                for(Map.Entry<Character, Node> entry : node.map.entrySet()) {
                    if(recur(word, it + 1, entry.getValue())) {
                        return true;
                    }
                }
                
                return false;
            } else {
                if(!node.map.containsKey(chr))
                    return false;
                node = node.map.get(chr);
            }
        }
        
        return node.isEnd;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */