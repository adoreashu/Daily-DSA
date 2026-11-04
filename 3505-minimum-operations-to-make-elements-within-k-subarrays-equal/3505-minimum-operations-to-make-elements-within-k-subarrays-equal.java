import java.util.Arrays;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
                long[] cost = new long[n - x + 1];
        
        int[] countBIT = new int[2000005];
        long[] sumBIT = new long[2000005];
        long totalSum = 0;
        
        for (int i = 0; i < x - 1; i++) {
            addBIT(countBIT, sumBIT, nums[i], 1);
            totalSum += nums[i];
        }
        
        for (int i = x - 1; i < n; i++) {
            addBIT(countBIT, sumBIT, nums[i], 1);
            totalSum += nums[i];
            
            int startIdx = i - x + 1;
            
            int medIdx = findKth(countBIT, (x + 1) / 2);
            long medVal = medIdx - 1000001; 
            
            int C = getCount(countBIT, medIdx);
            long S = getSum(sumBIT, medIdx);
            long lowerCount = (x + 1) / 2;
            long excess = C - lowerCount;
            
            long exactSumLower = S - excess * medVal;
            long exactSumUpper = totalSum - exactSumLower;
            
            cost[startIdx] = exactSumUpper - exactSumLower + (x % 2 != 0 ? medVal : 0);
            
            addBIT(countBIT, sumBIT, nums[startIdx], -1);
            totalSum -= nums[startIdx];
        }
        
        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];
        
        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, Long.MAX_VALUE / 2); 
            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1]; 
                if (i >= x) {
                    long take = prev[i - x] + cost[i - x];
                    if (take < curr[i]) {
                        curr[i] = take;
                    }
                }
            }
            long[] temp = prev;
            prev = curr;
            curr = temp;
        }
        
        return prev[n];
    }
    
    private void addBIT(int[] countBIT, long[] sumBIT, int val, int cnt) {
        int idx = val + 1000001;
        for (; idx < 2000005; idx += (idx & -idx)) {
            countBIT[idx] += cnt;
            sumBIT[idx] += (long) cnt * val;
        }
    }
    
    private int getCount(int[] countBIT, int idx) {
        int count = 0;
        for (; idx > 0; idx -= (idx & -idx)) {
            count += countBIT[idx];
        }
        return count;
    }
    
    private long getSum(long[] sumBIT, int idx) {
        long sum = 0;
        for (; idx > 0; idx -= (idx & -idx)) {
            sum += sumBIT[idx];
        }
        return sum;
    }
    
    private int findKth(int[] countBIT, int k) {
        int idx = 0;
        for (int i = 20; i >= 0; i--) {
            int nextIdx = idx + (1 << i);
            if (nextIdx < 2000005 && countBIT[nextIdx] < k) {
                idx = nextIdx;
                k -= countBIT[idx];
            }
        }
        return idx + 1;
    }
}