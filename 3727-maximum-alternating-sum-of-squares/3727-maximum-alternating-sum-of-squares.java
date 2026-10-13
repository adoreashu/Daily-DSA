class Solution {
    public long maxAlternatingSum(int[] nums) {
        int[] counts = new int[40001]; 
                for (int num : nums) {
            counts[Math.abs(num)]++;
        }
        
        long maxScore = 0;
        int posCount = (nums.length + 1) / 2; 
                for (int i = 40000; i >= 0; i--) {
            if (counts[i] > 0) {
                long square = (long) i * i;
                
                if (posCount > 0) {
                    int takeForPositive = Math.min(posCount, counts[i]);
                    maxScore += square * takeForPositive;
                    posCount -= takeForPositive;
                                        int takeForNegative = counts[i] - takeForPositive;
                    if (takeForNegative > 0) {
                        maxScore -= square * takeForNegative;
                    }
                } else {
                    maxScore -= square * counts[i];
                }
            }
        }
        
        return maxScore;
    }}