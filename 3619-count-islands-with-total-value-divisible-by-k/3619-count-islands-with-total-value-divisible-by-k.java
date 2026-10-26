class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int r = 0; r < m; r++) {
            int[] row = grid[r]; 
            for (int c = 0; c < n; c++) {
                if (row[c] > 0) {
                    if (dfs(grid, r, c, m, n) % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private long dfs(int[][] grid, int r, int c, int m, int n) {
        long sum = grid[r][c];
        grid[r][c] = 0; 

        if (r + 1 < m && grid[r + 1][c] > 0) sum += dfs(grid, r + 1, c, m, n);
        if (r > 0     && grid[r - 1][c] > 0) sum += dfs(grid, r - 1, c, m, n);
        if (c + 1 < n && grid[r][c + 1] > 0) sum += dfs(grid, r, c + 1, m, n);
        if (c > 0     && grid[r][c - 1] > 0) sum += dfs(grid, r, c - 1, m, n);

        return sum;
    }
}