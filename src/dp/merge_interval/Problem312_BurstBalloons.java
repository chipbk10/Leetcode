package dp.merge_interval;

public class Problem312_BurstBalloons {

    public int maxCoins(int[] nums) {
        // int n = nums.length;
        // return dfs(nums, 0, n-1, 1, 1);
        // return dfs_memo(nums, 0, n-1, 1, 1, new int[n][n]);
        return dp_bottom_up(nums);
    }

    private int dfs(int[] A, int i, int j, int left, int right) {
        if (i == j) return left * A[i] * right;
        if (i > j) return 0;

        int res = 0;

        for (int k = i; k <= j; k++) {
            int sum = left * A[k] * right;
            sum += dfs(A, i, k-1, left, A[k]);
            sum += dfs(A, k+1, j, A[k], right);
            res = Math.max(res, sum);
        }

        return res;
    }

    private int dfs_memo(int[] A, int i, int j, int left, int right, int[][] memo) {
        if (i > j) return 0;
        if (memo[i][j] != 0) return memo[i][j];

        if (i == j) memo[i][j] = left * A[i] * right;
        else {
            for (int k = i; k <= j; k++) {
                int sum = left * A[k] * right;
                sum += dfs_memo(A, i, k-1, left, A[k], memo);
                sum += dfs_memo(A, k+1, j, A[k], right, memo);
                memo[i][j] = Math.max(memo[i][j], sum);
            }
        }

        return memo[i][j];
    }

    private int dp_bottom_up(int[] A) {
        int n = A.length;
        if (n == 0) return 0;

        int dp[][] = new int[n][n];
        for (int d = 0; d < n; d++) {
            for (int i = 0; i < n-d; i++) {
                int j = i+d;
                int left = (i == 0) ? 1 : A[i-1];
                int right = (j == n-1) ? 1 : A[j+1];
                for (int k = i; k <= j; k++) {
                    int sum = left * A[k] * right;
                    if (k > 0) sum += dp[i][k-1];
                    if (k < n-1) sum += dp[k+1][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }

        return dp[0][n-1];
    }
}
