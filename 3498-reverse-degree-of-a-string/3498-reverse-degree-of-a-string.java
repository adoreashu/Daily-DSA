class Solution {
    public int reverseDegree(String s) {
        char[] chars = s.toCharArray();
        int totalDegree = 0;
        
        for (int i = 0; i < chars.length; i++) {
            int reversedValue = 'z' - chars[i] + 1;
            
            totalDegree += reversedValue * (i + 1);
        }
        
        return totalDegree;
    }
}