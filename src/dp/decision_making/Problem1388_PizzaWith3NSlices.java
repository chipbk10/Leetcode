package dp.decision_making;

public class Problem1388_PizzaWith3NSlices {

    public int maxSizeSlices(int[] A) {
        int n = A.length, x = 3, m = n/x;
        return Math.max(findMax(A, 0, n-2, m), findMax(A, 1, n-1, m));
    }

    private int findMax(int[] A, int lo, int hi, int m) {
        int[][] dp = new int[hi-lo+1][m+1];
        for (int i = 0; i <= hi-lo; i++) {
            for (int k = 1; k <= m; k++) {
                int max2 = (i >= 2) ? dp[i-2][k-1] : 0;
                int max1 = (i >= 1) ? dp[i-1][k] : 0;
                dp[i][k] = Math.max(max1, max2 + A[i+lo]);
            }
        }
        return dp[hi-lo][m];
    }
}
