package intervals;

public class Problem546_RemoveBoxes {

    int n, dp[][][];

    public int removeBoxes(int[] A) {
        n = A.length;
        dp = new int[n][n][100];
        return dfs(A, 0, n-1, 0);
    }

    private int dfs(int[] A, int l, int r, int k) {
        if (l > r) return 0;
        if (dp[l][r][k] > 0) return dp[l][r][k];

        dp[l][r][k] = dfs(A, l, r-1, 0) + (k+1)*(k+1);
        for (int i = l; i < r; i++) {
            if (A[i] == A[r]) {
                dp[l][r][k] = Math.max(dp[l][r][k], dfs(A,l,i,k+1) + dfs(A, i+1, r-1, 0));
            }
        }

        return dp[l][r][k];
    }
}
