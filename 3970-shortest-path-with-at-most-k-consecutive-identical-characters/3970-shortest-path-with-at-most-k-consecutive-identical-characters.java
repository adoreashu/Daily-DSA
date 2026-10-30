import java.util.Arrays;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int m = edges.length;
        int[] to = new int[m];
        int[] weight = new int[m];
        int[] next = new int[m];
        
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            to[i] = edges[i][1];
            weight[i] = edges[i][2];
            next[i] = head[u];
            head[u] = i;
        }
                int[][] minDist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(minDist[i], Integer.MAX_VALUE);
        }
        
        LongMinHeap heap = new LongMinHeap(m * 2); 
        
        minDist[0][1] = 0;
        heap.add(pack(0, 0, 1)); 
        
        byte[] chars = labels.getBytes(); 
        
        while (!heap.isEmpty()) {
            long val = heap.poll();
            int currW = (int) (val >>> 22);
            int u = (int) ((val >>> 6) & 0xFFFF);
            int count = (int) (val & 0x3F);
            
            if (currW > minDist[u][count]) continue;
            
            if (u == n - 1) return currW; 
            
            byte c_u = chars[u];
            
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                int w = weight[e];
                byte c_v = chars[v];
                
                int nextCount = (c_u == c_v) ? count + 1 : 1;
                
                if (nextCount <= k) {
                    int nextW = currW + w;
                    boolean dominated = false;
                    for (int c = 1; c <= nextCount; c++) {
                        if (minDist[v][c] <= nextW) {
                            dominated = true;
                            break;
                        }
                    }
                    
                    if (!dominated) {
                        minDist[v][nextCount] = nextW;
                        heap.add(pack(nextW, v, nextCount));
                    }
                }
            }
        }
        
        return -1;
    }
    private long pack(int w, int u, int c) {
        return ((long) w << 22) | ((long) u << 6) | c;
    }
    private static class LongMinHeap {
        long[] data;
        int size;
        
        public LongMinHeap(int capacity) {
            data = new long[Math.max(capacity, 16)];
            size = 0;
        }
        
        public void add(long val) {
            if (size == data.length) {
                data = Arrays.copyOf(data, data.length << 1);
            }
            int i = size++;
            while (i > 0) {
                int p = (i - 1) >>> 1;
                if (data[p] <= val) break;
                data[i] = data[p];
                i = p;
            }
            data[i] = val;
        }
        
        public long poll() {
            long ret = data[0];
            long val = data[--size];
            if (size == 0) return ret;
            int i = 0;
            while ((i << 1) + 1 < size) {
                int left = (i << 1) + 1;
                int right = left + 1;
                int min = (right < size && data[right] < data[left]) ? right : left;
                if (val <= data[min]) break;
                data[i] = data[min];
                i = min;
            }
            data[i] = val;
            return ret;
        }
        
        public boolean isEmpty() { 
            return size == 0; 
        }
    }
}