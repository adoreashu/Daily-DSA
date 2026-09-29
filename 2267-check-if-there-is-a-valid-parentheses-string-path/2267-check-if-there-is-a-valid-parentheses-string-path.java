class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
                if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        int maxBalance = (m + n) / 2;
        boolean[][][] visited = new boolean[m][n][maxBalance + 1];
        
        return dfs(grid, 0, 0, 0, visited, m, n);
    }
    
    private boolean dfs(char[][] grid, int r, int c, int balance, boolean[][][] visited, int m, int n) {
        balance += (grid[r][c] == '(') ? 1 : -1;
                if (balance < 0 || balance > (m - 1 - r) + (n - 1 - c)) {
            return false;
        }
        
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }        if (visited[r][c][balance]) {
            return false;
        }
        visited[r][c][balance] = true;
        
        if (r + 1 < m && dfs(grid, r + 1, c, balance, visited, m, n)) return true;
        if (c + 1 < n && dfs(grid, r, c + 1, balance, visited, m, n)) return true;
        
        return false;
    }
}