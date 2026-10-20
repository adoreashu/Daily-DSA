class Solution {
public int sortableIntegers(int[] nums) {        int n = nums.length;
        int sumK = 0;
                for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (isValid(nums, n, i)) {
                    sumK += i;
                }
                
                if (i * i != n) {
                    int j = n / i;
                    if (isValid(nums, n, j)) {
                        sumK += j;
                    }
                }
            }
        }
        
        return sumK;
    }
    
    private boolean isValid(int[] nums, int n, int k) {
        int prevMax = -1; 
                for (int i = 0; i < n; i += k) {
            int start = i;
            int end = i + k - 1;
            if (nums[start] < prevMax) return false;
            
            int blockMax = nums[start];
            int drops = 0;
            
            for (int j = start; j < end; j++) {
                int nextVal = nums[j + 1];
                if (nextVal < prevMax) return false;
                if (nums[j] > nextVal) {
                    drops++;
                    if (drops > 1) return false;
                }
                
                if (nextVal > blockMax) {
                    blockMax = nextVal;
                }
            }
            
            // If there is exactly 1 drop, the sequence "wrapped around".
            // For the wrap to be valid, the last element must connect back to <= the first element.
            if (drops == 1 && nums[end] > nums[start]) return false;
            
            // Update the maximum value for the next block's comparison
            prevMax = blockMax;
        }
        
        return true;
    }
}