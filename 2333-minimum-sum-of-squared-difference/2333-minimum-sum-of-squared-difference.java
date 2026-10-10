class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2; 
        
        int[] bucket = new int[100001];
        long totalDiffSum = 0;
        int maxDiff = 0;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            if (diff > 0) {
                bucket[diff]++;
                maxDiff = Math.max(maxDiff, diff);
                totalDiffSum += diff;
            }
        }
        
        if (k >= totalDiffSum) {
            return 0;
        }
        
        for (int i = maxDiff; i > 0 && k > 0; i--) {
            if (bucket[i] > 0) {
                long reduceCount = Math.min((long) bucket[i], k);
                
                bucket[i] -= reduceCount;       
                bucket[i - 1] += reduceCount;   
                k -= reduceCount;               
            }
        }
        
        long result = 0;
        for (long i = maxDiff; i > 0; i--) {
            if (bucket[(int)i] > 0) {
                result += (i * i) * bucket[(int)i]; 
            }
        }
        
        return result;
    }
}