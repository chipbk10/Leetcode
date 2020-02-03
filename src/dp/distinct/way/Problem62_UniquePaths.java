package dp.distinct.way;

public class Problem62_UniquePaths {

    public int uniquePaths(int m, int n) {
        return dp_solution_improve_space(m,n);
    }

    private int dp_solution_improve_space(int m, int n) {
        if (n > m) return dp_solution_improve_space(n, m);
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) dp[i] = 1;
        for (int i = 1; i < m; i++) {
            int prev = 1;
            for (int j = 1; j < n; j++) {
                dp[j] += prev;
                prev = dp[j];
            }
        }
        return dp[n-1];
    }

    private int dp_solution(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
