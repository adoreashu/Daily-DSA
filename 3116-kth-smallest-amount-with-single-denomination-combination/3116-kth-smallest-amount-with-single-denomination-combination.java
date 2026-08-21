import java.util.*;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);
        List<Integer> filteredCoins = new ArrayList<>();
        for (int coin : coins) {
            boolean isMultiple = false;
            for (int f : filteredCoins) {
                if (coin % f == 0) {
                    isMultiple = true;
                    break;
                }
            }
            if (!isMultiple) {
                filteredCoins.add(coin);
            }
        }
        
        int n = filteredCoins.size();
        
        long minCoin = filteredCoins.get(0);
        long maxAns = minCoin * k; 
        
        List<Long> addList = new ArrayList<>();
        List<Long> subList = new ArrayList<>();
        long[] subsetLcm = new long[1 << n];
        
        for (int mask = 1; mask < (1 << n); mask++) {
            int bit = Integer.numberOfTrailingZeros(mask);
            int prevMask = mask ^ (1 << bit);
            long currentCoin = filteredCoins.get(bit);
            
            long currentLcm;
            if (prevMask == 0) {
                currentLcm = currentCoin;
            } else {
                long prevLcm = subsetLcm[prevMask];
                if (prevLcm > maxAns) {
                    currentLcm = prevLcm;
                } else {
                    currentLcm = lcm(prevLcm, currentCoin);
                }
            }
            
            subsetLcm[mask] = currentLcm;
            
            if (currentLcm <= maxAns) {
                if (Integer.bitCount(mask) % 2 == 1) {
                    addList.add(currentLcm);
                } else {
                    subList.add(currentLcm);
                }
            }
        }
        
        long[] add = addList.stream().mapToLong(l -> l).toArray();
        long[] sub = subList.stream().mapToLong(l -> l).toArray();
        
        long left = 1;
        long right = maxAns;
        long ans = right;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            long count = 0;
            for (long val : add) count += mid / val;
            for (long val : sub) count -= mid / val;
            
            if (count >= k) {
                ans = mid;
                right = mid - 1; 
            } else {
                left = mid + 1;
            }
        }
        
        return ans;
    }
    
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}