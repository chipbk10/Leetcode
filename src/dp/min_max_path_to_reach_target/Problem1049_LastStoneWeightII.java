package dp.min_max_path_to_reach_target;

public class Problem1049_LastStoneWeightII {

    public static void run() {
        Problem1049_LastStoneWeightII solution = new Problem1049_LastStoneWeightII();
        int[] A = {2,7,4,1,8,1};
        int res = solution.lastStoneWeightII(A);
        System.out.println(res);
    }

    public int lastStoneWeightII(int[] A) {
        int sum = 0, n = A.length;
        for (int a : A) sum += a;

        int[][] dp = new int[n+1][sum/2+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum/2; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= A[i-1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-A[i-1]] + A[i-1]);
                }
            }
        }

        return sum - 2 * dp[n][sum/2];
    }
}
