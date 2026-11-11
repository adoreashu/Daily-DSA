class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;
                long costOption1 = (n1 * c1) + (n2 * c2);
        
        long k2 = Math.min(n1, n2);
        long costOption2 = (k2 * cb) + ((n1 - k2) * c1) + ((n2 - k2) * c2);
        
        long k3 = Math.max(n1, n2);
        long costOption3 = k3 * cb;
                return Math.min(costOption1, Math.min(costOption2, costOption3));
    }
}