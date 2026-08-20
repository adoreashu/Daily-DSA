class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        
        arr1[0] = nums[0];
        arr2[0] = nums[1];
        
        int p1 = 1;
        int p2 = 1;
        
        for (int i = 2; i < n; i++) {
            if (arr1[p1 - 1] > arr2[p2 - 1]) {
                arr1[p1++] = nums[i];
            } else {
                arr2[p2++] = nums[i];
            }
        }
        
        int[] result = new int[n];
        System.arraycopy(arr1, 0, result, 0, p1);
        System.arraycopy(arr2, 0, result, p1, p2);
        
        return result;
    }
}