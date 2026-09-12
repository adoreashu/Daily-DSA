import java.util.List;
import java.util.Arrays;

class Solution {
    class Interval implements Comparable<Interval> {
        int start, end, id;
        long weight;

        public Interval(int start, int end, long weight, int id) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.id = id;
        }

        @Override
        public int compareTo(Interval other) {
            if (this.start != other.start) {
                return Integer.compare(this.start, other.start);
            }
            return Integer.compare(this.end, other.end);
        }
    }

    class Result {
        long score;
        int[] ids;

        public Result(long score, int[] ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            arr[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }
        
        Arrays.sort(arr);

        Result[][] dp = new Result[n + 1][5];
        
        // Base case initialization
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new Result(0L, new int[0]);
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int next_i = findNext(arr, arr[i].end);
            
            for (int k = 1; k <= 4; k++) {
                Result skip = dp[i + 1][k];
                
                Result nextRes = dp[next_i][k - 1];
                long newScore = arr[i].weight + nextRes.score;
                
                int[] newIds = Arrays.copyOf(nextRes.ids, nextRes.ids.length + 1);
                newIds[newIds.length - 1] = arr[i].id;
                Arrays.sort(newIds); 
                
                Result take = new Result(newScore, newIds);

                if (isBetter(take, skip)) {
                    dp[i][k] = take;
                } else {
                    dp[i][k] = skip;
                }
            }
        }

        return dp[0][4].ids;
    }

    private int findNext(Interval[] arr, int endValue) {
        int low = 0, high = arr.length - 1;
        int ans = arr.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid].start > endValue) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean isBetter(Result r1, Result r2) {
        if (r1.score > r2.score) return true;
        if (r1.score < r2.score) return false;
        
        int len = Math.min(r1.ids.length, r2.ids.length);
        for (int i = 0; i < len; i++) {
            if (r1.ids[i] < r2.ids[i]) return true;
            if (r1.ids[i] > r2.ids[i]) return false;
        }
        return r1.ids.length < r2.ids.length;
    }
}