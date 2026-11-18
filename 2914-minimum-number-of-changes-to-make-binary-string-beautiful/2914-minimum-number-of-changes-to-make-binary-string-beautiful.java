class Solution {
    public int minChanges(String s) {
        char[] arr = s.toCharArray();
        int changes = 0;
        int n = arr.length;
                for (int i = 0; i < n; i += 2) {
            if (arr[i] != arr[i + 1]) {
                changes++;
            }
        }
        
        return changes;
    }
}