class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        int totalPoints = n + k - 1;
        int pointsToPick = 2 * k;
                if (pointsToPick > totalPoints) {
            return 0;
        }
        
        long result = 1;
        for (int i = 1; i <= pointsToPick; i++) {
            result = (result * (totalPoints - i + 1)) % MOD;
                        result = (result * modInverse(i, MOD)) % MOD;
        }
        
        return (int) result;
    }
    
    private long modInverse(long base, int mod) {
        long res = 1;
        int exp = mod - 2; 
        
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}