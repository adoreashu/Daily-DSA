class Solution {
    public int numOfSubarrays(int[] arr) {
        long ans = 0;
        
        int evenCount = 1; 
        int oddCount = 0;
        int parity = 0; 
        
        for (int num : arr) {
            parity ^= (num & 1);
            
            if (parity == 1) {
                ans += evenCount;
                oddCount++;
            } else {
                ans += oddCount;
                evenCount++;
            }
        }
        
        return (int) (ans % 1_000_000_007);
    }
}