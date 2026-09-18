import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
                int[] first = new int[26];
        int[] last = new int[26];
        
        for (int i = 0; i < 26; i++) {
            first[i] = n;
            last[i] = -1;
        }
        
        for (int i = 0; i < n; i++) {
            int c = arr[i] - 'a';
            if (first[c] == n) {
                first[c] = i;
            }
            last[c] = i;
        }
                int[] resStarts = new int[26];
        int[] resEnds = new int[26];
        int count = 0;
        int right = -1;
        
        for (int i = 0; i < n; i++) {
            if (i == first[arr[i] - 'a']) {
                int newRight = getRightBound(i, arr, first, last);
                
                if (newRight != -1) {
                    if (i > right) {
                        resStarts[count] = i;
                        resEnds[count] = newRight;
                        count++;
                    } else {
                        resStarts[count - 1] = i;
                        resEnds[count - 1] = newRight;
                    }
                    right = newRight;
                }
            }
        }
                List<String> res = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            res.add(new String(arr, resStarts[i], resEnds[i] - resStarts[i] + 1));
        }
        
        return res;
    }
    
    private int getRightBound(int i, char[] arr, int[] first, int[] last) {
        int right = last[arr[i] - 'a'];
        
        for (int j = i; j <= right; j++) {
            int c = arr[j] - 'a';
            
            if (first[c] < i) {
                return -1;
            }
                        if (last[c] > right) {
                right = last[c];
            }
        }
        
        return right;
    }
}