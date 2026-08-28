class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        int oddCount = 0;
        char midChar = 0;
        int[] halfCount = new int[26];
        
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) (i + 'a');
            }
            halfCount[i] = count[i] / 2; 
        }
        
        if (oddCount > 1) {
            return "";
        }
        
        int m = n / 2;
        int bestI = -1;
        char bestChar = 0;
        int[] bestCount = new int[26];
        boolean fullMatchPossible = true;
        
        for (int i = 0; i < m; i++) {
            char tChar = target.charAt(i);
            
            for (int j = tChar - 'a' + 1; j < 26; j++) {
                if (halfCount[j] > 0) {
                    bestI = i;
                    bestChar = (char) (j + 'a');
                    System.arraycopy(halfCount, 0, bestCount, 0, 26);
                    break;
                }
            }
            
            if (halfCount[tChar - 'a'] > 0) {
                halfCount[tChar - 'a']--;
            } else {
                fullMatchPossible = false;
                break; 
            }
        }
        
        if (fullMatchPossible) {
            String exactHalf = target.substring(0, m);
            String exactPal = buildPalindrome(exactHalf, midChar, n);
            if (exactPal.compareTo(target) > 0) {
                return exactPal;
            }
        }
        
        if (bestI != -1) {
            StringBuilder halfStr = new StringBuilder(m);
            halfStr.append(target.substring(0, bestI));
            halfStr.append(bestChar);
            bestCount[bestChar - 'a']--; // Consume the divergence character
            
            for (int i = 0; i < 26; i++) {
                while (bestCount[i] > 0) {
                    halfStr.append((char) (i + 'a'));
                    bestCount[i]--;
                }
            }
            
            return buildPalindrome(halfStr.toString(), midChar, n);
        }
        
        return "";
    }
    
    private String buildPalindrome(String half, char midChar, int n) {
        StringBuilder sb = new StringBuilder(n);
        sb.append(half);
        if (n % 2 != 0) {
            sb.append(midChar);
        }
        for (int i = half.length() - 1; i >= 0; i--) {
            sb.append(half.charAt(i));
        }
        return sb.toString();
    }
}