class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int[] m1 = new int[n];
        int[] m2 = new int[n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) m1[i] |= (1 << j);
                if (img2[i][j] == 1) m2[i] |= (1 << j);
            }
        }
        
        int maxOverlap = 0;
        
        for (int yShift = -n + 1; yShift < n; yShift++) {
            
            for (int xShift = -n + 1; xShift < n; xShift++) {
                int currentOverlap = 0;
                
                for (int i = 0; i < n; i++) {
                    int m1Row = i - yShift;
                    
                    if (m1Row >= 0 && m1Row < n) {
                        int r1 = m1[m1Row];
                        int r2 = m2[i];
                        
                        if (xShift > 0) {
                            r1 = r1 << xShift;
                        } else if (xShift < 0) {
                            r1 = r1 >>> (-xShift); 
                        }
                        
                        currentOverlap += Integer.bitCount(r1 & r2);
                    }
                }
                
                if (currentOverlap > maxOverlap) {
                    maxOverlap = currentOverlap;
                }
            }
        }
        
        return maxOverlap;
    }
}