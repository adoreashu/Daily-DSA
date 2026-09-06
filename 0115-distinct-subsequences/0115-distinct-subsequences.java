class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        
        if (m > n) {
            return 0;
        }
        
        int[] dp = new int[m + 1];
        dp[0] = 1; 
        
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
                if (sChars[i - 1] == tChars[j - 1]) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        
        return dp[m];
    }
}