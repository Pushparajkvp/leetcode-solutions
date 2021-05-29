/*
Given an array of strings words representing an English Dictionary, return the longest word in words that can be built one character at a time by other words in words.

If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.

 

Example 1:

Input: words = ["w","wo","wor","worl","world"]
Output: "world"
Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:

Input: words = ["a","banana","app","appl","ap","apply","apple"]
Output: "apple"
Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 30
words[i] consists of lowercase English letters.

*/
class Node{
    HashMap<Character, Node> map = new HashMap<>();
    boolean isEnd = false;
}

class Solution {
    
    Node root;
    String result = "";
    
    public String longestWord(String[] words) {
        root = new Node();    
        
        for(int wordIt=0; wordIt<words.length; wordIt++) {
            String word = words[wordIt];
            
            Node trav = root;
            for(int letterIt=0; letterIt<word.length(); letterIt++) {
                char letter = word.charAt(letterIt);
                
                if(trav.map.containsKey(letter)) {
                    trav = trav.map.get(letter);
                } else {
                    Node newNode = new Node();
                    trav.map.put(letter, newNode);
                    trav = newNode;
                }
            }
            trav.isEnd = true;
        }
        
        dfs(root, "");
        
        return result;
    }
    
    private void dfs(Node node, String str) {
        
        int currLen = str.length();
        int resLen = result.length();
        
        if(currLen > resLen || (currLen == resLen && str.compareTo(result) < 0)) {
            result = str;  
        }
            
        for(Map.Entry<Character, Node> entry : node.map.entrySet()) {
            Node nextNode = entry.getValue();
            if(nextNode.isEnd)
                dfs(entry.getValue(), str + entry.getKey());
        }
    }
}