class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[] rowCounts = new int[m];
        int[] colCounts = new int[n];
        int totalServers = 0;
        
        for (int r = 0; r < m; r++) {
            int[] row = grid[r]; 
            int rowSum = 0;
            for (int c = 0; c < n; c++) {
                if (row[c] == 1) {
                    rowSum++;
                    colCounts[c]++;
                    totalServers++;
                }
            }
            rowCounts[r] = rowSum;
        }
        
        if (totalServers <= 1) return 0;
        
        int isolatedServers = 0;
        
        for (int r = 0; r < m; r++) {
            if (rowCounts[r] == 1) {
                int[] row = grid[r];
                for (int c = 0; c < n; c++) {
                    if (row[c] == 1) {
                        if (colCounts[c] == 1) {
                            isolatedServers++;
                        }
                        break;
                    }
                }
            }
        }
        
        return totalServers - isolatedServers;
    }
}