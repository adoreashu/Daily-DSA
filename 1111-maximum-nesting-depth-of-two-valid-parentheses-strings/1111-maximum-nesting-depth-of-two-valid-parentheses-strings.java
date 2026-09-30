class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        char[] chars = seq.toCharArray();
        int[] ans = new int[chars.length];
        
        for (int i = 0; i < chars.length; i++) {
            ans[i] = (chars[i] == '(' ? 0 : 1) ^ (i & 1);
        }
        
        return ans;
    }
}