package dp.decision.making;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit.
 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times)
 * with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 */
public class Problems309_BestTimeToBuyAndSellStockWithCooldown {


    public int maxProfit(int[] A) {
        return solution_dp4(A);
    }

    private int solution_dp1(int[] A) {
        int N = A.length;
        if (N < 2) return 0;

        int dp[] = new int[N];
        for (int i = 1; i < N; i++) {
            dp[i] = dp[i-1];
            for (int j = 0; j < i; j++) {
                int prev = (j < 2) ? 0 : dp[j-2];
                dp[i] = Math.max(dp[i], A[i] - A[j] + prev);
            }
        }

        return dp[N-1];
    }

    private int solution_dp2(int[] A) {
        int N = A.length;
        if (N < 2) return 0;

        int dp[] = new int[N];
        for (int i = 1; i < N; i++) {

            int max = -A[0];
            for (int j = 1; j < i; j++) {
                int prev = (j < 2) ? 0 : dp[j-2];
                max = Math.max(max, prev - A[j]);
            }

            dp[i] = Math.max(dp[i-1], A[i] + max);
        }

        return dp[N-1];
    }

    private int solution_dp3(int[] A) {
        int N = A.length;
        if (N < 2) return 0;

        int dp[] = new int[N], max = -A[0];
        for (int i = 1; i < N; i++) {
            int prev = (i < 3) ? 0 : dp[i-3];
            max = Math.max(max, prev - A[i-1]);
            dp[i] = Math.max(dp[i-1], A[i] + max);
        }

        return dp[N-1];
    }

    private int solution_dp4(int[] A) {
        int N = A.length;
        if (N < 2) return 0;

        int dp3 = 0, dp2 = 0, dp1 = 0, dp = 0, max = -A[0];
        for (int i = 1; i < N; i++) {
            max = Math.max(max, dp3 - A[i-1]);
            dp = Math.max(dp1, A[i] + max);
            dp3 = dp2; dp2 = dp1; dp1 = dp;
        }

        return dp;
    }

}
