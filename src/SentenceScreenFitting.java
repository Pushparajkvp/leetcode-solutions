/*
Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Note:

A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word is greater than 0 and won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
Example 1:

Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output: 
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.
Example 2:

Input:
rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

Output: 
2

Explanation:
a-bcd- 
e-a---
bcd-e-

The character '-' signifies an empty space on the screen.
Example 3:

Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

Output: 
1

Explanation:
I-had
apple
pie-I
had--

The character '-' signifies an empty space on the screen.

*/

//Recomputing every time
class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int row = 0;
        int wordIt = 0;
        int result = 0;
        int currLen = 0;
        
        while(row < rows) {
            int wordLen = sentence[wordIt].length();
            int prevLen = currLen == 0 ? currLen : currLen + 1;
            
            if(wordLen + prevLen <= cols) {
                currLen = prevLen + wordLen;
                wordIt++;
                if(wordIt >= sentence.length) {
                    wordIt = 0;
                    result++;
                }
            } else {
                row++;
                currLen = 0;
            }
        }
        
        return result;
    }
}
//Memo for each word in sentence as starting word and next word
class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int numWord = sentence.length;
        int[] nextWord = new int[numWord];
        int[] times = new int[numWord];
        for (int i = 0; i < numWord; i++) {
            int curr = i;
            int currLen = 0;
            int currTime = 0;
            while (currLen + sentence[curr].length() <= cols) { // we can put curr word to the current line.
                currLen += sentence[curr].length() + 1; // update current length with appending space
                curr++;
                if (curr == numWord) { // increase time and reset curr index
                    currTime++;
                    curr = 0;
                }
            }
            // when loop exits, we stop at the first word of next line and times of the previous line.
            nextWord[i] = curr;
            times[i] = currTime;
        }
        
        int totalTimes = 0;
        int start = 0; // it is obviously that we start from the first word in the sentence.
        for (int i = 0; i < rows; i++) {
            totalTimes += times[start];
            start = nextWord[start]; // update start of next row.
        }
        return totalTimes;
    }
}