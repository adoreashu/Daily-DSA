class Solution {
    private int minXor = Integer.MAX_VALUE;
    private int m, n;
    private int[][] grid;
    private long[] vis;

    public int minCost(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;

        this.vis = new long[(m * n) << 4];

        dfs(0, 0, 0);
        return minXor;
    }

    private void dfs(int r, int c, int xor) {
        xor ^= grid[r][c];

        if (r == m - 1 && c == n - 1) {
            if (xor < minXor) {
                minXor = xor;
            }
            return;
        }
        int idx = ((r * n + c) << 4) | (xor >>> 6);
        long mask = 1L << (xor & 63);
        if ((vis[idx] & mask) != 0L) {
            return;
        }
        vis[idx] |= mask;

        if (r + 1 < m) {
            dfs(r + 1, c, xor);
            if (minXor == 0)
                return;
        }

        if (c + 1 < n) {
            dfs(r, c + 1, xor);
        }
    }
}