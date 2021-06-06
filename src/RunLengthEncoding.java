/*
Given a run-length encoded lowercase alphabet string s, implement an iterator which is the decoded version of s:

next() polls the next element in the iterator
hasnext() which returns whether the next element exists
Constraints

n ≤ 100,000 where n is the number of calls to next and hasnext
Example 1
Input
methods = ["constructor", "next", "hasnext", "next", "next", "hasnext"]
arguments = [["2a1b"], [], [], [], [], []]`
Output
[None, "a", True, "a", "b", False]
*/
class RunLengthDecodedIterator {
    String s;
    int indx = 0;
    Deque<String> list;

    private String getNum() {
        StringBuilder sb = new StringBuilder();
        for(;indx<s.length();indx++) {
            if(!Character.isDigit(s.charAt(indx))) return sb.toString();
            sb.append(s.charAt(indx));
        }
        return "";
    }
    public RunLengthDecodedIterator(String s) {
        this.s = s;
        list = new LinkedList<>();
        for(;indx<s.length();indx++) {
            if(Character.isDigit(s.charAt(indx))) {
                list.add(getNum());
            }
            list.add(String.valueOf(s.charAt(indx)));
        }
    }

    public String next() {
        String digit = list.removeFirst();

        int num = Integer.parseInt(digit);
        String res = list.getFirst();
        num -= 1;

        if(num == 0) list.removeFirst();
        else list.addFirst(String.valueOf(num));  

        return res;
    }

    public boolean hasnext() {
        return list.size() != 0;
    }
}