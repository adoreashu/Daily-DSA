class Solution {
    private int[] treeProduct;
    private int[][] treePrefix;
    private int K;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        this.K = k;
        treeProduct = new int[4 * n];
        treePrefix = new int[4 * n][k];
        
        build(1, 0, n - 1, nums);
        
        int[] ans = new int[queries.length];
        int[] state = new int[2];
        
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
                        update(1, 0, n - 1, idx, val);            state[0] = 1; 
            state[1] = 0; 
            
            if (start < n) {
                query(1, 0, n - 1, start, n - 1, x, state);
            }
            
            ans[i] = state[1];
        }
        
        return ans;
    }
    
    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            int modVal = nums[l] % K;
            treeProduct[node] = modVal;
            treePrefix[node][modVal] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * node, l, mid, nums);
        build(2 * node + 1, mid + 1, r, nums);
        merge(node);
    }
    
    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            int modVal = val % K;
            treeProduct[node] = modVal;
                        for (int m = 0; m < K; m++) {
                treePrefix[node][m] = 0;
            }
            treePrefix[node][modVal] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, r, idx, val);
        }
        merge(node);
    }
    
    private void merge(int node) {
        int left = 2 * node;
        int right = 2 * node + 1;
                treeProduct[node] = (treeProduct[left] * treeProduct[right]) % K;
                for (int m = 0; m < K; m++) {
            treePrefix[node][m] = treePrefix[left][m];
        }
                int leftProd = treeProduct[left];
        for (int m = 0; m < K; m++) {
            if (treePrefix[right][m] > 0) {
                int newMod = (leftProd * m) % K;
                treePrefix[node][newMod] += treePrefix[right][m];
            }
        }
    }
    
    private void query(int node, int l, int r, int ql, int qr, int targetX, int[] state) {
        if (ql <= l && r <= qr) {
            for (int m = 0; m < K; m++) {
                if (treePrefix[node][m] > 0) {
                    if ((state[0] * m) % K == targetX) {
                        state[1] += treePrefix[node][m];
                    }
                }
            }
            state[0] = (state[0] * treeProduct[node]) % K;
            return;
        }
        
        int mid = l + (r - l) / 2;        if (ql <= mid) {
            query(2 * node, l, mid, ql, qr, targetX, state);
        }
        if (qr > mid) {
            query(2 * node + 1, mid + 1, r, ql, qr, targetX, state);
        }
    }
}