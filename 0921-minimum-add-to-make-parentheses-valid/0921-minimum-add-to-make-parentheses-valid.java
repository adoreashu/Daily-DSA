class Solution {
    public int minAddToMakeValid(String s) {
        int unmatchedOpen = 0; 
        int unmatchedClose = 0; 
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                unmatchedOpen++;
            } else { 
                if (unmatchedOpen > 0) {
                    unmatchedOpen--;
                } else {
                    unmatchedClose++;
                }
            }
        }
        return unmatchedOpen + unmatchedClose;
    }
}