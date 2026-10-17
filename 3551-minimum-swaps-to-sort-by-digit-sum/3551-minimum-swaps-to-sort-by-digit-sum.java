import java.util.Arrays;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
                long[] packed = new long[n];
        
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            int sum = 0;
            int temp = val;
                        while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            packed[i] = ((long) sum << 48) | ((long) val << 17) | (long) i;
        }
        Arrays.sort(packed);
        
        boolean[] visited = new boolean[n];
        int swaps = 0;
                for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
                        int originalIndex = (int) (packed[i] & 0x1FFFFL);
                        if (originalIndex == i) {
                visited[i] = true;
                continue;
            }
                        int j = i;
            int cycleSize = 0;
            
            while (!visited[j]) {
                visited[j] = true;
                j = (int) (packed[j] & 0x1FFFFL);
                cycleSize++;
            }
                        if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }
        
        return swaps;
    }
}