package dp.merge.interval;

public class Problem1000_MinimumCostToMergeStones {

    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        int[] prefix = new int[n+1];
        for (int i = 0; i < n; i++) {
            prefix[i+1] = prefix[i] + stones[i];
        }
        return dfs(stones, K, prefix, 0, n-1);
    }

    private int dfs(int[] A, int K, int[] prefix, int i, int j) {
        int n = j-i+1;
        if (n < K) return 0;
        int res = Integer.MAX_VALUE;

        for (int m = i+1; m <= j; m++) {
            int sum = dfs(A, K, prefix, i, m-1);
            sum += dfs(A, K, prefix, m, j);
            if (sum != 0) res = Math.min(res, sum);
        }

        if ((n-1) % (K-1) == 0) res += prefix[j+1]-prefix[i];
        return res;
    }

    // K
    // 1 (K-1) = K + (K-1)
    // 1 (K-1) = K + (K-1) + (K-1)
    // n = K + m * (K-1)
    // (n-1) % (K-1) = 0

    // [i]-----[m-1][m]------[j]
    // dp[i][j] = the minimum cost to merge [i,j] as much as possible ( if the length > K, then should > 0)
    // dp[i][m-1] = the cost + (the remaining unmerged elements on left side = L)
    // dp[m][j] = the cost + (the remaining unmerged elements on left side = R)
    // dp[i][j] = dp[i][m-1] + dp[m][j]
    // if (L + R == K) -> dp[i][j] += Sum[i,j]

    public int dp_bottom_up(int[] stones, int K) {
        if (K == 0) return 0;
        int n = stones.length;
        if ((n - 1) % (K - 1) != 0) return -1;

        int[] prefixSum = new int[n+1];
        for (int i = 1; i <= n; ++i) {
            prefixSum[i] = stones[i-1] + prefixSum[i-1];
        }

        int[][] dp = new int[stones.length][stones.length];
        for (int r = 0; r < n; ++r) {
            for (int l = r - K + 1; l >= 0; --l) {
                int max = Integer.MAX_VALUE;
                for (int p = r; p > l; p -= K - 1) {
                    max = Math.min(max, dp[l][p-1] + dp[p][r]);
                    dp[l][r] = max;
                }
                if ((r-l) % (K-1) == 0)
                    dp[l][r] += prefixSum[r+1] - prefixSum[l];
            }
        }
        return dp[0][n-1];
    }
}
