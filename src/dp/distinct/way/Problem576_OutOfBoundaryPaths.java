package dp.distinct.way;

public class Problem576_OutOfBoundaryPaths {

    public int findPaths(int m, int n, int K, int r, int c) {
        if (K == 0 || m == 0 || n == 0 || r < 0 || r >= m || c < 0 || c >= n) return 0;
        int mod = (int)1e9+7;
        int[][][] dp = new int[K][m][n];
        int[][] dirs = new int[][] {{-1,0}, {0,1}, {1,0}, {0,-1}};
        dp[0][r][c] = 1;

        for (int k = 1; k < K; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] d : dirs) {
                        if (isValid(m, n, i, j, d[0], d[1])) {
                            dp[k][i][j] += dp[k-1][i-d[0]][j-d[1]];
                            dp[k][i][j] %= mod;
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int k = 0; k < K; k++) {
            for (int i = 0; i < m; i++) {
                res += dp[k][i][0];
                res %= mod;
                res += dp[k][i][n-1];
                res %= mod;
            }
        }

        for (int k = 0; k < K; k++) {
            for (int j = 0; j < n; j++) {
                res += dp[k][0][j];
                res %= mod;
                res += dp[k][m-1][j];
                res %= mod;
            }
        }
        return res;
    }

    private boolean isValid(int m, int n, int i, int j, int x, int y) {
        if (i-x < 0 || i-x >= m) return false;
        if (j-y < 0 || j-y >= n) return false;
        return true;
    }
}
