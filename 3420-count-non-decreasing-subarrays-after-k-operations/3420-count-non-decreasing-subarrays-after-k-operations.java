class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
                int[] st = new int[n];
        int top = -1;
        
        long cost = 0;
        long ans = 0;
        int R = n - 1;
        
        for (int L = n - 1; L >= 0; L--) {
            while (top >= 0 && nums[st[top]] <= nums[L]) {
                int idx = st[top];
                top--;
                
                int range_end = (top >= 0) ? st[top] - 1 : n - 1;
                int overlap_start = idx;
                int overlap_end = Math.min(R, range_end);
                
                if (overlap_start <= overlap_end) {
                    cost += (long) (nums[L] - nums[idx]) * (overlap_end - overlap_start + 1);
                }
            }
                        st[++top] = L;
            
            while (cost > k) {
                int low = 0, high = top;
                int best = -1;
                
                while (low <= high) {
                    int mid = (low + high) >>> 1;
                    if (st[mid] <= R) {
                        best = st[mid];
                        high = mid - 1; 
                    } else {
                        low = mid + 1;
                    }
                }
                                cost -= (nums[best] - nums[R]);
                R--;
            }
                        ans += (R - L + 1);
        }
        
        return ans;
    }
}