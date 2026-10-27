import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
                char[] buffer = new char[n];
        Arrays.fill(buffer, '.');
        grid[0] = new String(buffer);
        if (m > 1) {
            Arrays.fill(buffer, 0, n - 1, '#');
            String lowerRow = new String(buffer);
                        Arrays.fill(grid, 1, m, lowerRow);
        }
        
        return grid;
    }
}