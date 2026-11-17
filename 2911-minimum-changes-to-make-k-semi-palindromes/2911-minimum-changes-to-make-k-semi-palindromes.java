class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        
        int[][] divs = new int[n + 1][];
        int[] temp = new int[n + 1];
        for (int len = 2; len <= n; len++) {
            int count = 0;
            for (int d = 1; d <= len / 2; d++) {
                if (len % d == 0) {
                    temp[count++] = d;
                }
            }
            divs[len] = new int[count];
            System.arraycopy(temp, 0, divs[len], 0, count);
        }
        int[] cost = new int[n * n]; 
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minChanges = Integer.MAX_VALUE;
                
                for (int d : divs[len]) {
                    int currentChanges = 0;
                                        for (int g = 0; g < d; g++) {
                        int left = i + g;
                        int maxSteps = (len - 1 - g) / d;
                        int right = i + g + (maxSteps * d);
                        
                        while (left < right) {
                            if (arr[left] != arr[right]) {
                                currentChanges++;
                            }
                            left += d;
                            right -= d;
                        }
                    }
                    if (currentChanges < minChanges) {
                        minChanges = currentChanges;
                    }
                }
                cost[i * n + j] = minChanges;
            }
        }
        
        int[] prev = new int[n];
        int[] curr = new int[n];
        
        for (int i = 1; i < n; i++) {
            prev[i] = cost[i]; 
        }
        
        for (int p = 2; p <= k; p++) {
            for (int i = 2 * p - 1; i < n; i++) {
                int min = Integer.MAX_VALUE;
                
                for (int j = 2 * p - 2; j <= i - 1; j++) {
                    int val = prev[j - 1] + cost[j * n + i];
                    if (val < min) {
                        min = val;
                    }
                }
                curr[i] = min;
            }
                    int[] tempArr = prev;
            prev = curr;
            curr = tempArr;
        }
        
        return prev[n - 1];
    }
}