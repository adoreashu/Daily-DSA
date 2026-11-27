class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int minPrice = Integer.MAX_VALUE;
        int maxFactor = 0;

        for (int[] item : items) {
            if (item[0] > maxFactor) maxFactor = item[0];
            if (item[1] < minPrice) minPrice = item[1];
        }

        if (minPrice > budget) return 0;

        int[] multCount = new int[maxFactor + 1];
        for (int[] item : items) {
            multCount[item[0]]++;
        }
        int half = maxFactor >>> 1;
        for (int f = 1; f <= half; f++) {
            int sum = multCount[f];
            if (sum != 0) {
                for (int m = f << 1; m <= maxFactor; m += f) {
                    sum += multCount[m];
                }
                multCount[f] = sum;
            }
        }

        int[] dp = new int[budget + 1];
        int maxReach = 0;

        for (int[] item : items) {
            int p = item[1];
            if (p > budget) continue;

            int v = multCount[item[0]];
            if (v <= p / minPrice) continue;

            int nextReach = maxReach + p;
            if (nextReach > budget) nextReach = budget;

            int carry = dp[maxReach];
            for (int c = maxReach + 1; c <= nextReach; c++) {
                dp[c] = carry;
            }

            for (int c = nextReach; c >= p; c--) {
                int cand = dp[c - p] + v;
                if (cand > dp[c]) {
                    dp[c] = cand;
                }
            }
            maxReach = nextReach;
        }

        int tailVal = dp[maxReach];
        for (int c = maxReach + 1; c <= budget; c++) {
            dp[c] = tailVal;
        }
        int ans = 0;
        for (int extra = 0, c = budget; c >= 0; extra++, c -= minPrice) {
            int total = dp[c] + extra;
            if (total > ans) ans = total;
        }

        return ans;
    }
}