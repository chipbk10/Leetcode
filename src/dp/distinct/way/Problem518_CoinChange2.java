package dp.distinct.way;

public class Problem518_CoinChange2 {

    public int change(int N, int[] coins) {
        int[] dp = new int[N+1];
        dp[0] = 1;

        for (int coin: coins) {
            for (int i = coin; i <= N; i++) {
                dp[i] += dp[i-coin];
            }
        }

        return dp[N];
    }

}
