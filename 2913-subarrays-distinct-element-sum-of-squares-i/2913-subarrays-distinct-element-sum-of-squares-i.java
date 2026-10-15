import java.util.List;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int totalSum = 0;
        int[] seen = new int[101];
                for (int i = 0; i < n; i++) {
            int distinctCount = 0;
                        for (int j = i; j < n; j++) {
                int val = nums.get(j);
                if (seen[val] != i + 1) {
                    seen[val] = i + 1; 
                    distinctCount++;
                }
                
                totalSum += distinctCount * distinctCount;
            }
        }
        
        return totalSum;
    }
}