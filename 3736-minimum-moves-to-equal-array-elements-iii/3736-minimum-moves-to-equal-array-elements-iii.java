class Solution {
    public int minMoves(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
                for (int num : nums) {
            sum += num;
            if (num > max) {
                max = num;
            }
        }
                return (max * nums.length) - sum;
    }
}