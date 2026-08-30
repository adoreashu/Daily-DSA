class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        
        if (n <= 2) {
            return n;
        }
        
        int minIdx = 0;
        int maxIdx = 0;
                for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        
        int first = Math.min(minIdx, maxIdx);
        int last = Math.max(minIdx, maxIdx);
        
        int deleteFront = last + 1;
        
        int deleteBack = n - first;
        
        int deleteBothSides = (first + 1) + (n - last);
        
        return Math.min(Math.min(deleteFront, deleteBack), deleteBothSides);
    }
}