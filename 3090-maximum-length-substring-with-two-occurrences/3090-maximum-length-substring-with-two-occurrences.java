class Solution {
    public int maximumLengthSubstring(String s) {
        int[] freq = new int[26];
        int maxLen = 0;
        int left = 0;
        
        char[] chars = s.toCharArray();
        
        for (int right = 0; right < chars.length; right++) {
            int rightCharIdx = chars[right] - 'a';
            
            freq[rightCharIdx]++;
            
            while (freq[rightCharIdx] > 2) {
                int leftCharIdx = chars[left] - 'a';
                freq[leftCharIdx]--;
                left++;
            }
            
            int currentLen = right - left + 1;
            if (currentLen > maxLen) {
                maxLen = currentLen;
            }
        }
        
        return maxLen;
    }
}