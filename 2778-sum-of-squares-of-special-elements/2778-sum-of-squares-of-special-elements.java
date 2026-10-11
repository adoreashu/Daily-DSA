class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
                for (int i = 1; i * i <= n; i++) {
                        if (n % i == 0) {
                sum += nums[i - 1] * nums[i - 1];
                if (i * i != n) {
                    int pairedDivisor = n / i;
                    sum += nums[pairedDivisor - 1] * nums[pairedDivisor - 1];
                }
            }
        }
        
        return sum;
    }
}