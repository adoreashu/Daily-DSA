class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
                comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }
        return (int) ((dfs(nums) - 1 + MOD) % MOD);
    }

    private long dfs(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return 1;
        }
        
        int root = nums[0];
        
        int[] left = new int[n];
        int[] right = new int[n];
        int l = 0, r = 0;
        
        for (int i = 1; i < n; i++) {
            if (nums[i] < root) {
                left[l++] = nums[i];
            } else {
                right[r++] = nums[i];
            }
        }
        int[] exactLeft = new int[l];
        System.arraycopy(left, 0, exactLeft, 0, l);
        
        int[] exactRight = new int[r];
        System.arraycopy(right, 0, exactRight, 0, r);
        
        long leftWays = dfs(exactLeft);
        long rightWays = dfs(exactRight);
        long totalWays = (leftWays * rightWays) % MOD;
        totalWays = (totalWays * comb[l + r][l]) % MOD;
        
        return totalWays;
    }
}