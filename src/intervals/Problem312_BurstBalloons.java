package intervals;

public class Problem312_BurstBalloons {

    public int maxCoins(int[] A) {
        int n = A.length, dp[][] = new int[n][n];
        return dfs(A, dp, 0, n-1);
    }

    private int dfs(int[] A, int[][] dp, int l, int r) {
        if (l > r) return 0;
        if (dp[l][r] != 0) return dp[l][r];
        for (int i = l; i <= r; i++) {
            int p = A[i];
            if (l > 0) p *= A[l-1];
            if (r < A.length-1) p *= A[r+1];
            dp[l][r] = Math.max(dp[l][r], p + dfs(A, dp, l, i-1) + dfs(A, dp, i+1, r));
        }
        return dp[l][r];
    }
}
