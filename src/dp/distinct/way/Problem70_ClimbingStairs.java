package dp.distinct.way;

public class Problem70_ClimbingStairs {

    public int climbStairs(int n) {
        if (n < 2) return n;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= 2; j++) {
                dp[i] += dp[i-j];
            }
        }
        return dp[n];
    }
}
