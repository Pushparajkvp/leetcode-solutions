/*
Design a data structure that is initialized with a list of different words. Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.

Implement the MagicDictionary class:

MagicDictionary() Initializes the object.
void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure, otherwise returns false.
 

Example 1:

Input
["MagicDictionary", "buildDict", "search", "search", "search", "search"]
[[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
Output
[null, null, false, true, false, false]

Explanation
MagicDictionary magicDictionary = new MagicDictionary();
magicDictionary.buildDict(["hello", "leetcode"]);
magicDictionary.search("hello"); // return False
magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
magicDictionary.search("hell"); // return False
magicDictionary.search("leetcoded"); // return False
 

Constraints:

1 <= dictionary.length <= 100
1 <= dictionary[i].length <= 100
dictionary[i] consists of only lower-case English letters.
All the strings in dictionary are distinct.
1 <= searchWord.length <= 100
searchWord consists of only lower-case English letters.
buildDict will be called only once before search.
At most 100 calls will be made to search.
*/
class Node {
    Node[] dict = new Node[26];
    boolean isEnd = false;
}
class MagicDictionary {
    
    Node root;
    
    /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new Node();    
    }
    
    public void buildDict(String[] dictionary) {
        for(int it=0; it<dictionary.length; it++) {
            String word = dictionary[it];
            Node trav = root;
            
            for(int letterIt=0; letterIt<word.length(); letterIt++) {
                char letter = word.charAt(letterIt);
                
                if(trav.dict[letter - 'a'] != null) {
                    trav = trav.dict[letter - 'a'];
                } else {
                    Node newNode = new Node();
                    trav.dict[letter - 'a'] = newNode;
                    trav = newNode;
                }
            }
            
            trav.isEnd = true;
        }
    }
    
    public boolean search(String searchWord) {
        return search(searchWord, 0, false, root);
    }
    
    
    public boolean search(String word, int start, boolean replaced, Node trav) {
        
        for(int letterIt=start; letterIt<word.length(); letterIt++) {
            char letter = word.charAt(letterIt);

            if(trav.dict[letter - 'a'] != null) {
                trav = trav.dict[letter - 'a'];
            } else {
                if(replaced)
                    return false;
                replaced = true;
                for(int it=0; it<26; it++) {
                    if(trav.dict[it] != null)
                        if(search(word, letterIt+1, replaced, trav.dict[it])) return true;
                }
                
                return false;
            }
        }
        return trav.isEnd && replaced;
    }
}
