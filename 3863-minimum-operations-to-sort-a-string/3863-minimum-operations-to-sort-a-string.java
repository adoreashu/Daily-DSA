class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean isSorted = true;
        
        char minChar = 127; 
        char maxChar = 0;   
        int firstMinIdx = -1;
        int lastMaxIdx = -1;
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            if (i < n - 1 && c > s.charAt(i + 1)) {
                isSorted = false;
            }
                        if (c < minChar) {
                minChar = c;
                firstMinIdx = i;
            }
                        if (c >= maxChar) {
                maxChar = c;
                lastMaxIdx = i;
            }
        }
                if (isSorted) {
            return 0; 
        }
        
        if (n == 2) {
            return -1; 
        }
                if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }
                if (firstMinIdx == n - 1 && lastMaxIdx == 0) {
            return 3;
        }
                return 2;
    }
}