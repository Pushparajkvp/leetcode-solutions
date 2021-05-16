/*

Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring text[i]...text[j] is in the list of words.

 

Example 1:

Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
Output: [[3,7],[9,13],[10,17]]
Example 2:

Input: text = "ababa", words = ["aba","ab"]
Output: [[0,1],[0,2],[2,3],[2,4]]
Explanation: 
Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].
 

Note:

All strings contains only lowercase English letters.
It's guaranteed that all strings in words are different.
1 <= text.length <= 100
1 <= words.length <= 20
1 <= words[i].length <= 50
Return the pairs [i,j] in sorted order (i.e. sort them by their first coordinate in case of ties sort them by their second coordinate).

*/
class Solution {
    public int[][] indexPairs(String text, String[] words) {
        /*initializing tire and put all word from words into Trie.*/
        Trie trie=new Trie();
        for(String s:words){
            Trie cur=trie;
            for(char c:s.toCharArray()){
                if(cur.children[c-'a']==null){
                    cur.children[c-'a']=new Trie();
                }
                cur=cur.children[c-'a'];
            }
            cur.end=true;       /*mark there is a word*/
        }
        
        /*if text is "ababa", check "ababa","baba","aba","ba","a" individually.*/
        int len=text.length();
        List<int[]> list=new ArrayList<>();
        for(int i=0;i<len;i++){
            Trie cur=trie;
            char cc=text.charAt(i);
            int j=i;   /*j is our moving index*/
            
            while(cur.children[cc-'a']!=null){ 
                cur=cur.children[cc-'a'];
                if(cur.end){   /*there is a word ending here, put into our list*/
                    list.add(new int[]{i,j});
                }
                j++;
                if(j==len){  /*reach the end of the text, we stop*/
                    break;
                }
                else{
                    cc=text.charAt(j);  
                }
            }
        }
        /*put all the pairs from list into array*/
        int size=list.size();
        int[][] res=new int[size][2];
        int i=0;
        for(int[] r:list){
            res[i]=r;
            i++;
        }
        return res;
    }
}
class Trie{
    Trie[] children;
    boolean end;   /*indicate whether there is a word*/
    public Trie(){
        end=false;
        children=new Trie[26];
    }
}