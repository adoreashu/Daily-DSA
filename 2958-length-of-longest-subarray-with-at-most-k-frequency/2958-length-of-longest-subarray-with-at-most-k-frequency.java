class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 0;
        int ll = 0;
        int capacity = 262144;
        int mask = capacity - 1; 
        
        int[] keys = new int[capacity];
        int[] counts = new int[capacity];
        
        for (int rr = 0; rr < n; rr++) {
            int num = nums[rr];
            
            int pos = hash(num) & mask;
            
            while (keys[pos] != 0 && keys[pos] != num) {
                pos = (pos + 1) & mask;
            }
            
            keys[pos] = num;
            
            if (counts[pos] < k) {
                counts[pos]++;
            } else {
                int num2;
                while ((num2 = nums[ll++]) != num) {
                    int pos2 = hash(num2) & mask;
                    while (keys[pos2] != num2) {
                        pos2 = (pos2 + 1) & mask;
                    }
                    counts[pos2]--;
                }
            }
            
            int len = rr - ll + 1;
            if (len > maxLen) {
                maxLen = len;
            }
        }
        
        return maxLen;
    }
    
    private int hash(int x) {
        x ^= x >>> 16;
        x *= 0x85ebca6b;
        x ^= x >>> 13;
        x *= 0xc2b2ae35;
        x ^= x >>> 16;
        return x;
    }
}