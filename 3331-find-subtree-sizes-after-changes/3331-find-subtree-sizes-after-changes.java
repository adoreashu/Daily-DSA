import java.util.Arrays;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        int[] ans = new int[n];

        int[] firstChild = new int[n];
        int[] nextSibling = new int[n];
        for (int i = 1; i < n; i++) {
            int p = parent[i];
            nextSibling[i] = firstChild[p];
            firstChild[p] = i;
        }
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        int[] stackNode = new int[n];
        int[] stackPrev = new int[n];

        int top = 0;
        stackNode[0] = 0;
        stackPrev[0] = -1;
        lastSeen[s.charAt(0) - 'a'] = 0;
        ans[0] = 1;

        while (top >= 0) {
            int u = stackNode[top];
            int child = firstChild[u];

            if (child != 0) {
                firstChild[u] = nextSibling[child];

                int c = s.charAt(child) - 'a';
                stackNode[++top] = child;
                stackPrev[top] = lastSeen[c];  
                lastSeen[c] = child;
                ans[child] = 1;
            } else {
                int prev = stackPrev[top--];
                lastSeen[s.charAt(u) - 'a'] = prev; 

                if (u != 0) {
                    int targetParent = (prev != -1) ? prev : parent[u];
                    ans[targetParent] += ans[u];
                }
            }
        }

        return ans;
    }
}