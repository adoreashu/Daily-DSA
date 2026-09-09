class Solution {
    public long countCommas(long n) {
        return Math.max(0L, n - 999L) 
             + Math.max(0L, n - 999_999L) 
             + Math.max(0L, n - 999_999_999L) 
             + Math.max(0L, n - 999_999_999_999L) 
             + Math.max(0L, n - 999_999_999_999_999L);
    }
}