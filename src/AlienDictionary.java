class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] dic = new int[26];
        for(int it=0; it<order.length(); it++)
            dic[order.charAt(it)-'a'] = it;
        
        for(int it=1; it<words.length; it++) {
            for(int j=0;; j++) {
                if(j>=words[it-1].length() || j>=words[it].length()) {
                    if(words[it-1].length() > words[it].length())
                        return false;
                    break;
                }
                char leftChar = words[it-1].charAt(j);
                char rightChar = words[it].charAt(j);
                if(dic[leftChar-'a'] > dic[rightChar-'a'])
                    return false;
                else if(dic[leftChar - 'a'] < dic[rightChar - 'a'])
                    break;
                else
                    continue;
            }
        }
        return true;
    }
    
}