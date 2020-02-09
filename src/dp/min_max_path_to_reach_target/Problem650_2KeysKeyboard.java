package dp.min_max_path_to_reach_target;

public class Problem650_2KeysKeyboard {

    public int minSteps(int N) {
        int[] dp = new int[N+1];

        for (int n = 2; n <= N; n++) {
            dp[n] = Integer.MAX_VALUE;
            for (int i = 1; i <= n/2; i++) {
                if (n % i == 0) {
                    dp[n] = Math.min(dp[n], dp[i] + (n/i));
                }
            }
        }

        return dp[N];
    }
}
