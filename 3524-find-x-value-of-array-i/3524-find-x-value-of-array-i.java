class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] currentDp = new long[k];
        
        for (int num : nums) {
            long[] nextDp = new long[k];
            
            int modVal = num % k;
            
            nextDp[modVal] += 1;
            
            for (int r = 0; r < k; r++) {
                if (currentDp[r] > 0) {
                    int newRemainder = (r * modVal) % k;
                    nextDp[newRemainder] += currentDp[r];
                }
            }
            
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
                currentDp[r] = nextDp[r];
            }
        }
        
        return result;
    }
}