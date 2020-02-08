package dp.min_max_path_to_reach_target;

public class Problem931_MinimumFaillingPathSum {

    public int minFallingPathSum(int[][] A) {
        int n = A.length, dp[] = new int[n], res = Integer.MAX_VALUE;
        int prev, cur, next;

        for (int i = 0; i < n; i++) {
            prev = Integer.MAX_VALUE;
            cur = dp[0];
            for (int j = 0; j < n; j++) {
                next = (j < n-1) ? dp[j+1] : Integer.MAX_VALUE;
                dp[j] = Math.min(dp[j], prev);
                dp[j] = Math.min(dp[j], next);
                dp[j] += A[i][j];
                prev = cur;
                cur = next;
            }
        }

        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[i]);
        }

        return res;
    }
}
