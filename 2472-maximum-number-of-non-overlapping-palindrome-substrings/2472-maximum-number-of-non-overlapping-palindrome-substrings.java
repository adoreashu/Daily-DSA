class Solution {
    public int maxPalindromes(String s, int k) {
        char[] c = s.toCharArray();
        int n = c.length;
        
        int count = 0;
        int last_end = -1; 
        
        for (int i = k - 1; i < n; i++) {
            
            int start_k = i - k + 1;
            if (start_k > last_end && isPalindrome(c, start_k, i)) {
                count++;
                last_end = i;
                continue; 
            }
            
            int start_k1 = i - k;
            if (start_k1 >= 0 && start_k1 > last_end && isPalindrome(c, start_k1, i)) {
                count++;
                last_end = i;
            }
        }
        
        return count;
    }
    
    private boolean isPalindrome(char[] c, int left, int right) {
        while (left < right) {
            if (c[left] != c[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}