class Solution {
    public int minimizeSum(int[] nums) {
        if (nums.length <= 3) return 0;

        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, min3 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min3) {
                if (x < min1) {
                    min3 = min2;
                    min2 = min1;
                    min1 = x;
                } else if (x < min2) {
                    min3 = min2;
                    min2 = x;
                } else {
                    min3 = x;
                }
            }

            if (x > max3) {
                if (x > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = x;
                } else if (x > max2) {
                    max3 = max2;
                    max2 = x;
                } else {
                    max3 = x;
                }
            }
        }

        int dropTwoLargest = max3 - min1;
        int dropTwoSmallest = max1 - min3;
        int dropOneEach = max2 - min2;

        return Math.min(dropOneEach, Math.min(dropTwoLargest, dropTwoSmallest));
    }
}