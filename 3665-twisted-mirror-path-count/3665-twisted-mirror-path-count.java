class Solution {
    public int uniquePaths(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int MOD = 1_000_000_007;
                int[] eR = new int[m + 1];
        int[] eD = new int[m + 1];
        
        for (int r = n - 1; r >= 0; r--) {
            for (int c = m - 1; c >= 0; c--) {
                if (r == n - 1 && c == m - 1) {
                    eR[c] = 1;
                    eD[c] = 1;
                    continue;
                }
                
                if (grid[r][c] == 0) {
                    int ways = eR[c + 1] + eD[c];
                                        if (ways >= MOD) {
                        ways -= MOD;
                    }
                    
                    eR[c] = ways;
                    eD[c] = ways;
                } else {
                    int new_eR = eD[c];
                    
                    int new_eD = eR[c + 1];
                    
                    eR[c] = new_eR;
                    eD[c] = new_eD;
                }
            }
        }
        return eR[0];
    }
}