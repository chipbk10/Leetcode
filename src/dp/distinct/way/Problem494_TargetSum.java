package dp.distinct.way;

public class Problem494_TargetSum {

    public int findTargetSumWays(int[] nums, int S) {

        // sum from -1000 to 1000
        // dp[i][j] the first i element can make up the sum of j.
        if(S > 1000 || S < -1000) return 0;
        int n = nums.length;

        int[][] dp = new int[n+1][2001];
        // 0 -> -1000 + 1000;
        // 1 -> -999 + 1000;
        // 1000 -> 0 + 1000;
        // 2000 -> 1000 + 1000;
        dp[0][1000] = 1;

        for(int i = 1; i <= n; i++){
            for(int j = 0; j < 2001; j++){
                if(j - nums[i-1] >= 0){
                    dp[i][j] += dp[i-1][j-nums[i-1]];
                }
                if(j + nums[i-1] < 2000){
                    dp[i][j] += dp[i-1][j+nums[i-1]];
                }
            }
        }
        return dp[n][S+1000];
    }
}
