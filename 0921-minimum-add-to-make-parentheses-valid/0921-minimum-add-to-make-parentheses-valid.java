class Solution {
    public int minAddToMakeValid(String s) {
        int unmatched_wala_Open = 0; 
        int unmatched_wala_Close = 0; 
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                unmatched_wala_Open++;
            } else { 
                if (unmatched_wala_Open > 0) {
                    unmatched_wala_Open--;
                } else {
                    unmatched_wala_Close++;
                }
            }
        }
        return unmatched_wala_Open + unmatched_wala_Close;
    }
}