package dp.distinct.way;

import java.util.Arrays;

public class Problem416_PartitionEqualSubsetSum {

    public boolean canPartition(int[] A) {

        int N = A.length, S = 0;
        for (int i = 0; i < N; i++) S += A[i];
        if (S % 2 != 0) return false;

        S /= 2;

        // dp[s][n] is true if we can take first n numbers from A to add up s
        boolean[][] dp = new boolean[S+1][N+1];
        Arrays.fill(dp[0], true);

        for (int s = 1; s <= S; s++) {
            for (int n = 1; n <= N; n++) {
                dp[s][n] = dp[s][n-1];
                if (s >= A[n-1]) {
                    dp[s][n] = dp[s][n] || dp[s-A[n-1]][n-1];
                }
            }
        }

        return dp[S][N];
    }
}
