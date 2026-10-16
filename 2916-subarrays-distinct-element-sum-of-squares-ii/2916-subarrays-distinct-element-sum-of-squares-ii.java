class Solution {
    private static final int MOD = 1_000_000_007;
    private long[] sumSq; 
    private long[] sum;   
    private long[] lazy;  

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumSq = new long[4 * n + 1];
        sum = new long[4 * n + 1];
        lazy = new long[4 * n + 1];
        int[] last = new int[100005];
        java.util.Arrays.fill(last, -1);

        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            int val = nums[i];
            int prevPos = last[val];
            update(1, 0, n - 1, prevPos + 1, i, 1);
            totalSum = (totalSum + sumSq[1]) % MOD;
            last[val] = i;
        }

        return (int) totalSum;
    }

    private void apply(int node, int start, int end, long val) {
        long len = end - start + 1;
        long v = val % MOD;
        sumSq[node] = (sumSq[node] + 2 * v * sum[node] % MOD + len * v % MOD * v % MOD) % MOD;
        sum[node] = (sum[node] + len * v % MOD) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }

    private void pushDown(int node, int start, int end) {
        if (lazy[node] != 0) {
            int mid = start + (end - start) / 2;
            apply(2 * node, start, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, end, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void update(int node, int start, int end, int l, int r, long val) {
        if (l > end || r < start) {
            return; 
        }
        if (l <= start && end <= r) {
            apply(node, start, end, val); 
            return;
        }
        
        pushDown(node, start, end);
        int mid = start + (end - start) / 2;
        update(2 * node, start, mid, l, r, val);
        update(2 * node + 1, mid + 1, end, l, r, val);
                sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
    }
}