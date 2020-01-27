package dp.merge.interval;

public class Problem1130_MinimumCostTreeFromLeafValues {


    public int mctFromLeafValues(int[] A) {
        int n = A.length, dp[][] = new int[n][n], max[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                max[i][j] = (i == j) ? A[i] : Math.max(A[j], max[i][j-1]);
            }
        }

        for (int d = 0; d < n; d++) {
            for (int i = 0; i < n-d; i++) {
                int j = i+d;
                int sum = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int left = (i == k) ? 0 : dp[i][k];
                    int right = (k+1 == j) ? 0 : dp[k+1][j];
                    int prod = max[i][k] * max[k+1][j];
                    sum = Math.min(sum, left + right + prod);
                }
                dp[i][j] = sum;
            }
        }

        return dp[0][n-1];
    }
}
