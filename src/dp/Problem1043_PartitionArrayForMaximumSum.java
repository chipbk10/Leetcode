package dp;

public class Problem1043_PartitionArrayForMaximumSum {

    public int maxSumAfterPartitioning(int[] A, int K) {
        int n = A.length, dp[] = new int[n];
        if (n == 0) return 0;
        dp[0] = A[0];
        for (int i = 1; i < n; i++) {
            int max = Integer.MIN_VALUE;
            for (int d = 1; d <= Math.min(K, i+1); d++) {
                max = Math.max(max, A[i-d+1]);
                int prev = (i<d) ? 0 : dp[i-d];
                dp[i] = Math.max(dp[i], prev + max*d);
            }
        }
        return dp[n-1];
    }
}
