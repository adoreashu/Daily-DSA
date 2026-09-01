import java.util.Arrays;

class Solution {
    public int minMoves(String[] classroom, int energyLimit) {
        int rows = classroom.length;
        int cols = classroom[0].length();
        char[][] grid = new char[rows][cols];
        
        int startR = -1, startC = -1;
        int[][] litterId = new int[rows][cols];
        int lCount = 0;
        
        for (int r = 0; r < rows; r++) {
            grid[r] = classroom[r].toCharArray();
            for (int c = 0; c < cols; c++) {
                litterId[r][c] = -1;
                if (grid[r][c] == 'S') {
                    startR = r;
                    startC = c;
                } else if (grid[r][c] == 'L') {
                    litterId[r][c] = lCount++;
                }
            }
        }
        
        if (lCount == 0) return 0;
        int targetMask = (1 << lCount) - 1;
        int[] maxEnergy = new int[rows * cols * 1024];
        Arrays.fill(maxEnergy, -1);
        
        int capacity = 65536; 
        int[] q = new int[capacity];
        int head = 0, tail = 0;
        
        int startState = (startR << 21) | (startC << 16) | (0 << 6) | energyLimit;
        q[tail & (capacity - 1)] = startState;
        tail++;
        
        maxEnergy[(startR * cols + startC) * 1024] = energyLimit;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int moves = 0;
        
        while (head < tail) {
            int size = tail - head;
            
            for (int i = 0; i < size; i++) {
                int state = q[(head++) & (capacity - 1)];
                
                int r = (state >> 21) & 31;
                int c = (state >> 16) & 31;
                int mask = (state >> 6) & 1023;
                int e = state & 63;
                
                int idx = (r * cols + c) * 1024 + mask;
                if (e < maxEnergy[idx]) continue;
                
                if (e == 0) continue; 
                
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] != 'X') {
                        int ne = e - 1;
                        int nmask = mask;
                        
                        if (grid[nr][nc] == 'R') {
                            ne = energyLimit; 
                        } else if (grid[nr][nc] == 'L') {
                            nmask |= (1 << litterId[nr][nc]); 
                        }
                        
                        if (nmask == targetMask) return moves + 1;
                        
                        int nidx = (nr * cols + nc) * 1024 + nmask;
                        
                        if (ne > maxEnergy[nidx]) {
                            maxEnergy[nidx] = ne;
                            
                            if (tail - head == capacity) {
                                int newCap = capacity << 1;
                                int[] nq = new int[newCap];
                                for (int j = 0; j < capacity; j++) {
                                    nq[j] = q[(head + j) & (capacity - 1)];
                                }
                                q = nq;
                                head = 0;
                                tail = capacity;
                                capacity = newCap;
                            }
                            
                            q[tail & (capacity - 1)] = (nr << 21) | (nc << 16) | (nmask << 6) | ne;
                            tail++;
                        }
                    }
                }
            }
            moves++;
        }
        
        return -1; 
    }
}