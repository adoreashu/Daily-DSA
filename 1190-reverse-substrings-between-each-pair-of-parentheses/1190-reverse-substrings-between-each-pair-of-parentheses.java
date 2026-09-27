class Solution {
    public String reverseParentheses(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        
        int[] pair = new int[n];
        
        int[] stack = new int[n];
        int top = -1;
        
        for (int i = 0; i < n; i++) {
            if (arr[i] == '(') {
                stack[++top] = i; // Push
            } else if (arr[i] == ')') {
                int j = stack[top--]; // Pop
                pair[i] = j;
                pair[j] = i;
            }
        }
        char[] res = new char[n];
        int resIdx = 0;
        
        int i = 0;
        int direction = 1;
        
        while (i < n) {
            if (arr[i] == '(' || arr[i] == ')') {
                i = pair[i];
                direction = -direction;
            } else {
                res[resIdx++] = arr[i];
            }
            i += direction;
        }
        
        return new String(res, 0, resIdx);
    }
}