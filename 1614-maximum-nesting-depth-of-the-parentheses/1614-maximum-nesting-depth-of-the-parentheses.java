class Solution {
    public int maxDepth(String s) {
        char[] chars = s.toCharArray();
        int maxDepth = 0;
        int currentDepth = 0;
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                currentDepth++;
                if (currentDepth > maxDepth) {
                    maxDepth = currentDepth;
                }
            } else if (chars[i] == ')') {
                currentDepth--;
            }
        }
        
        return maxDepth;
    }
}