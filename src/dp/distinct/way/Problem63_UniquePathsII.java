package dp.distinct.way;

public class Problem63_UniquePathsII {

    public int uniquePathsWithObstacles(int[][] A) {
        int m = A.length, n = A[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        if (A[0][0] == 1) return 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    dp[i][j] = 0;
                }
                else {
                    if (i >= 1) dp[i][j] += dp[i-1][j];
                    if (j >= 1) dp[i][j] += dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }
}
