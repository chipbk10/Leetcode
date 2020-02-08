package dp.min_max_path_to_reach_target;

public class Problem64_MinimumPathSum {

    public int minPathSum(int[][] A) {
        return dp_solution_improve_space(A);
    }

    private int dp_solution_improve_space(int[][] A) {
        int m = A.length, n = A[0].length;

        if (m > n) {
            int dp[] = new int[n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (j > 0) {
                        if (i == 0) dp[j] += dp[j-1];
                        else dp[j] = Math.min(dp[j], dp[j-1]);
                    }
                    dp[j] += A[i][j];
                }
            }
            return dp[n-1];
        }
        else {
            int dp[] = new int[m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (j > 0) {
                        if (i == 0) dp[j] += dp[j-1];
                        else dp[j] = Math.min(dp[j], dp[j-1]);
                    }
                    dp[j] += A[j][i];
                }
            }
            return dp[m-1];
        }

    }

    private int dp_solution(int[][] A) {
        int m = A.length, n = A[0].length;
        int dp[][] = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                if (j >= 1) min = Math.min(min, dp[i][j-1]);
                if (i >= 1) min = Math.min(min, dp[i-1][j]);
                if (min != Integer.MAX_VALUE) dp[i][j] += min;
                dp[i][j] += A[i][j];
            }
        }

        return dp[m-1][n-1];
    }
}
