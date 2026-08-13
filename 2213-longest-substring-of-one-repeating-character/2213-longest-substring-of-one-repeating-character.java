class Solution {
    int[] max, pref, suff, len;
    char[] prefChar, suffChar;
    
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        
        max = new int[4 * n];
        pref = new int[4 * n];
        suff = new int[4 * n];
        len = new int[4 * n];
        prefChar = new char[4 * n];
        suffChar = new char[4 * n];
        
        char[] arr = s.toCharArray();
        build(1, 0, n - 1, arr);
        
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = max[1];
        }
        
        return ans;
    }
    
    private void build(int node, int left, int right, char[] arr) {
        if (left == right) {
            max[node] = pref[node] = suff[node] = len[node] = 1;
            prefChar[node] = suffChar[node] = arr[left];
            return;
        }
        
        int mid = left + (right - left) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;
        
        build(leftChild, left, mid, arr);
        build(rightChild, mid + 1, right, arr);
        
        merge(node, leftChild, rightChild);
    }
    
    private void update(int node, int left, int right, int idx, char c) {
        if (left == right) {
            prefChar[node] = suffChar[node] = c;
            return;
        }
        
        int mid = left + (right - left) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;
        
        if (idx <= mid) {
            update(leftChild, left, mid, idx, c);
        } else {
            update(rightChild, mid + 1, right, idx, c);
        }
        
        merge(node, leftChild, rightChild);
    }
    
    private void merge(int node, int leftChild, int rightChild) {
        len[node] = len[leftChild] + len[rightChild];
        prefChar[node] = prefChar[leftChild];
        suffChar[node] = suffChar[rightChild];
        
        pref[node] = pref[leftChild];
        if (pref[leftChild] == len[leftChild] && suffChar[leftChild] == prefChar[rightChild]) {
            pref[node] += pref[rightChild];
        }
        
        suff[node] = suff[rightChild];
        if (suff[rightChild] == len[rightChild] && prefChar[rightChild] == suffChar[leftChild]) {
            suff[node] += suff[leftChild];
        }
        
        max[node] = Math.max(max[leftChild], max[rightChild]);
        if (suffChar[leftChild] == prefChar[rightChild]) {
            max[node] = Math.max(max[node], suff[leftChild] + pref[rightChild]);
        }
    }
}