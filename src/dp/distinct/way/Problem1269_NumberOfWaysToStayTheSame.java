package dp.distinct.way;

public class Problem1269_NumberOfWaysToStayTheSame {

    public int numWays(int K, int N) {
        return dp_solution_improve_space(K, N);
    }

    private int dp_solution_improve_space(int K, int N) {
        N = Math.min(N, K/2+1);
        int[] dp = new int[N];
        dp[0] = 1;

        int mod = (int)1e9 + 7;

        for (int k = 1; k <= K; k++) {
            int prev = 0, cur;
            for (int n = 0; n < N; n++) {
                cur = dp[n];

                if (n >= 1) {
                    dp[n] += prev;
                    dp[n] %= mod;
                }

                if (n+1 < N) {
                    dp[n] += dp[n+1];
                    dp[n] %= mod;
                }

                prev = cur;
            }
        }

        return dp[0];
    }

    private int dp_solution(int K, int N) {
        int[][] dp = new int[K+1][N];
        dp[0][0] = 1;

        int mod = (int)1e9 + 7;

        for (int k = 1; k <= K; k++) {
            for (int n = 0; n < N; n++) {
                dp[k][n] += dp[k-1][n];
                dp[k][n] %= mod;

                if (n >= 1) {
                    dp[k][n] += dp[k-1][n-1];
                    dp[k][n] %= mod;
                }

                if (n+1 < N) {
                    dp[k][n] += dp[k-1][n+1];
                    dp[k][n] %= mod;
                }
            }
        }

        return dp[K][0];
    }
}
