class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[31]; 
                for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCounts[i]++;
                }
            }
        }
        
        long maxSum = 0;
        long MOD = 1_000_000_007; 
                for (int j = 0; j < k; j++) {
            long currentNum = 0;
            
            for (int i = 0; i < 31; i++) {
                if (bitCounts[i] > 0) {
                    currentNum |= (1L << i);
                    bitCounts[i]--; 
                }
            }
                        long square = (currentNum * currentNum) % MOD;
            maxSum = (maxSum + square) % MOD;
        }
        
        return (int) maxSum;
    }
}