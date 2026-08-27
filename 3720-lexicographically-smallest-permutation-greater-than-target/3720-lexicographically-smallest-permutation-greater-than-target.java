class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        int bestI = -1;
        char bestChar = 0;
        int[] bestCount = new int[26];
        
        for (int i = 0; i < n; i++) {
            char tChar = target.charAt(i);
            
            for (int j = tChar - 'a' + 1; j < 26; j++) {
                if (count[j] > 0) {
                    bestI = i;
                    bestChar = (char) (j + 'a');
                    System.arraycopy(count, 0, bestCount, 0, 26);
                    break; 
                }
            }
            
            if (count[tChar - 'a'] > 0) {
                count[tChar - 'a']--;
            } else {
                break; 
            }
        }
        
        if (bestI == -1) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder(n);
        
        sb.append(target.substring(0, bestI));
        
        sb.append(bestChar);
        bestCount[bestChar - 'a']--; 
        
        for (int j = 0; j < 26; j++) {
            while (bestCount[j] > 0) {
                sb.append((char) (j + 'a'));
                bestCount[j]--;
            }
        }
        
        return sb.toString();
    }
}