package dp.min_max_path_to_reach_target;

public class Problem322_CoinChange {

    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount+1];
        dp[0] = 1;

        for (int j = 0; j < coins.length; j++) {
            for (int i = coins[j]; i <= amount; i++) {
                int prev = dp[i-coins[j]];
                if (prev > 0) {
                    if (dp[i] == 0) dp[i] = prev+1;
                    else dp[i] = Math.min(dp[i], prev+1);
                }
            }
        }

        return dp[amount]-1;
    }
}
