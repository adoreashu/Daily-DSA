class Solution {
    public boolean sumGame(String num) {
        int diffSum = 0; 
        int diffQ = 0;   
        int half = num.length() / 2;
                for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            boolean isLeft = (i < half);
            
            if (c == '?') {
                diffQ += isLeft ? 1 : -1;
            } else {
                int val = c - '0';
                diffSum += isLeft ? val : -val;
            }
        }
        return (diffSum * 2) + (diffQ * 9) != 0;
    }
}