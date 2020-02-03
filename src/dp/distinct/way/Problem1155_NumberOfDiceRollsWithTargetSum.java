package dp.distinct.way;

public class Problem1155_NumberOfDiceRollsWithTargetSum {

    private int MOD = (int)1e9 + 7;

    public int numRollsToTarget(int D, int F, int N) {
        if (N > D * F) return 0;
        if (N == D * F) return 1;

        int[][] dp = new int[N+1][D+1];
        dp[0][0] = 1;

        for (int d = 1; d <= D; d++) {
            for (int n = 1; n <= N; n++) {
                for (int f = 1; f <= F; f++) {
                    if (n < f) break;
                    dp[n][d] = (dp[n][d] + dp[n-f][d-1]) % MOD;
                }
            }
        }

        return dp[N][D];
    }

}
