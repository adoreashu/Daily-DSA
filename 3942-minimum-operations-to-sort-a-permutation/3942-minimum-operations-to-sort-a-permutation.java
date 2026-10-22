class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        
        int pos0 = -1;
        boolean isAscending = true;
        boolean isDescending = true;
        
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == 0) pos0 = i;
            
            int curr = nums[i];
            int next = nums[i + 1];
            
            if (isAscending && next != curr + 1 && !(curr == n - 1 && next == 0)) {
                isAscending = false;
            }
            
            if (isDescending && curr != next + 1 && !(next == n - 1 && curr == 0)) {
                isDescending = false;
            }
            
            if (!isAscending && !isDescending) {
                return -1;
            }
        }
        
        if (nums[n - 1] == 0) pos0 = n - 1;
        
        if (isAscending) {
            int curr = nums[n - 1];
            int next = nums[0];
            if (next != curr + 1 && !(curr == n - 1 && next == 0)) {
                isAscending = false;
            }
        }
        
        if (isDescending) {
            int curr = nums[n - 1];
            int next = nums[0];
            if (curr != next + 1 && !(next == n - 1 && curr == 0)) {
                isDescending = false;
            }
        }
        
        if (!isAscending && !isDescending) {
            return -1;
        }
        
        if (isAscending) {
            return Math.min(pos0, 2 + (n - pos0) % n);
        }
        
        return Math.min(n - pos0, (pos0 + 1) % n + 1);
    }
}