class Solution {
    public String removeOuterParentheses(String s) {
        char[] arr = s.toCharArray();
        int opened = 0; 
        int writeIndex = 0; 
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                if (opened > 0) {
                    arr[writeIndex++] = arr[i];
                }
                opened++; 
            } else { 
                opened--; 
                if (opened > 0) {
                    arr[writeIndex++] = arr[i];
                }
            }
        }        return new String(arr, 0, writeIndex);
    }
}