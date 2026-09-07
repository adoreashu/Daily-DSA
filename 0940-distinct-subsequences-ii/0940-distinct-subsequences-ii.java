class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        int[] end = new int[26];
        int total = 0;
                char[] chars = s.toCharArray();
        
        for (char ch : chars) {
            int c = ch - 'a';
            
            int newEndingWithC = (total + 1) % MOD;
            int added = (newEndingWithC - end[c] + MOD) % MOD;
            
            end[c] = newEndingWithC;
            total = (total + added) % MOD;
        }
        
        return total;
    }
}