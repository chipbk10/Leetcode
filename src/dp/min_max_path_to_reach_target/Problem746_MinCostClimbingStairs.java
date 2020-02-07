package dp.min_max_path_to_reach_target;

public class Problem746_MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n < 3) return 0;

        int[] dp = new int[n+1];
        dp[0] = dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);
        }

        return dp[n];
    }

    // TODO: improve the space complexity

}
