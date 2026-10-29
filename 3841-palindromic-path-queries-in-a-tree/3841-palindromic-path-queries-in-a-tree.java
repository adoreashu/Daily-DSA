import java.util.ArrayList;
import java.util.List;

class Solution {
    private int[] tin, tout;
    private int timer = 0;
    private int[][] up;
    private int LOG = 16; 
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        up = new int[n][LOG + 1];

        dfs(0, 0, adj);

        Fenwick bit = new Fenwick(n);
        char[] currentChars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            int mask = 1 << (currentChars[i] - 'a');
            bit.updateRange(tin[i], tout[i], mask);
        }
        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            int space1 = q.indexOf(' ');
            int space2 = q.indexOf(' ', space1 + 1);

            if (q.charAt(0) == 'u') { 
                int u = 0;
                for (int i = space1 + 1; i < space2; i++) {
                    u = u * 10 + (q.charAt(i) - '0');
                }
                char newC = q.charAt(space2 + 1);
                char oldC = currentChars[u];

                if (newC != oldC) {
                    int diff = (1 << (oldC - 'a')) ^ (1 << (newC - 'a'));
                    bit.updateRange(tin[u], tout[u], diff);
                    currentChars[u] = newC;
                }
            } else { 
                int u = 0;
                for (int i = space1 + 1; i < space2; i++) {
                    u = u * 10 + (q.charAt(i) - '0');
                }
                int v = 0;
                for (int i = space2 + 1; i < q.length(); i++) {
                    v = v * 10 + (q.charAt(i) - '0');
                }

                int lcaNode = getLca(u, v);
                int xorU = bit.query(tin[u]);
                int xorV = bit.query(tin[v]);
                int lcaCharMask = 1 << (currentChars[lcaNode] - 'a');

                int pathMask = xorU ^ xorV ^ lcaCharMask;
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    private void dfs(int v, int p, List<Integer>[] adj) {
        tin[v] = ++timer;
        up[v][0] = p;
        for (int i = 1; i <= LOG; i++) {
            up[v][i] = up[up[v][i - 1]][i - 1];
        }
        for (int u : adj[v]) {
            if (u != p) {
                dfs(u, v, adj);
            }
        }
        tout[v] = timer;
    }

    private boolean isAncestor(int u, int v) {
        return tin[u] <= tin[v] && tout[u] >= tout[v];
    }

    private int getLca(int u, int v) {
        if (isAncestor(u, v)) return u;
        if (isAncestor(v, u)) return v;
        for (int i = LOG; i >= 0; i--) {
            if (!isAncestor(up[u][i], v)) {
                u = up[u][i];
            }
        }
        return up[u][0];
    }

    private static class Fenwick {
        int[] tree;
        int size;

        public Fenwick(int size) {
            this.size = size;
            this.tree = new int[size + 2];
        }

        public void update(int idx, int val) {
            for (; idx <= size + 1; idx += idx & -idx) {
                tree[idx] ^= val;
            }
        }

        public void updateRange(int l, int r, int val) {
            update(l, val);
            update(r + 1, val);
        }

        public int query(int idx) {
            int sum = 0;
            for (; idx > 0; idx -= idx & -idx) {
                sum ^= tree[idx];
            }
            return sum;
        }
    }
}