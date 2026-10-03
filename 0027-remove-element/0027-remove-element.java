class Solution {
    public int removeElement(int[] nums, int val) {
        int writerHand = 0;
        
        for (int readerHand = 0; readerHand < nums.length; readerHand++) {
            if (nums[readerHand] != val) {
                nums[writerHand] = nums[readerHand];
                writerHand++;
            }
        }
                return writerHand;
    }
}