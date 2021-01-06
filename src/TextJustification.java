/* 
Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
 

Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
 

Constraints:

1 <= words.length <= 300
1 <= words[i].length <= 20
words[i] consists of only English letters and symbols.
1 <= maxWidth <= 100
words[i].length <= maxWidth
*/
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int currLength = 0;
        
        Queue<String> queue = new LinkedList<>();
        ArrayList<String> result = new ArrayList<>();
        
        for(int it=0; it<words.length; it++) {
            if(currLength + words[it].length() + queue.size()> maxWidth) {
                StringBuilder sb = new StringBuilder();
                int space = maxWidth-currLength;
                
                if(queue.size() == 1 || queue.size() == 2) {
                    sb.append(queue.poll());
                    while(space>0)  {
                        space--;
                        sb.append(" ");
                    }
                    if(!queue.isEmpty())
                        sb.append(queue.poll());
                } else {
                    int parts = queue.size() - 1;
                    int extra = space%parts;
                    space/=parts;

                    while(!queue.isEmpty()) {
                        sb.append(queue.poll());
                        for(int j=0; j<space && queue.size()>0; j++)
                            sb.append(" ");
                        if(extra > 0) {
                            extra--;
                            sb.append(" ");
                        }
                    }
                }
                
                result.add(sb.toString());
                currLength = 0;
            }
            currLength += words[it].length();
            queue.offer(words[it]);
        }
    
        StringBuilder sb = new StringBuilder();
        int space = maxWidth-currLength-(queue.size()-1);
        while(!queue.isEmpty()) {
            sb.append(queue.poll());
            if(queue.size()>0)
                sb.append(" ");
        }
        while(space>0) {
            space--;
            sb.append(" ");
        }
       
        result.add(sb.toString());
     
        return result;
    }
}