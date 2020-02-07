package dp.decision_making;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class Problem198_HouseRobber {

    /**
     * Strategies:
     *      Recursive (top-down or bottom-up)
     *      Recursive + memo
     *      Iterative + memo
     *      Iterative + memo (improve space complexity)
     *
     *      Iterative + dfs
     *      Iterative + bfs
     *
     *      http://bit.ly/38lDDwL
     */

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // return rob_top_down(nums, n-1);

        // int[] memo = new int[n];
        // Arrays.fill(memo, -1);
        // return rob_top_down_memo(nums, n-1, memo);

        return rob_iterative_bottom_up_dp(nums);
    }

    private int rob_top_down(int[] nums, int i) {
        if (i == 0) return nums[0];
        if (i == 1) return Math.max(nums[0], nums[1]);
        return Math.max(nums[i] + rob_top_down(nums, i-2), rob_top_down(nums, i-1));
    }

    private int rob_top_down_memo(int[] nums, int i, int[] memo) {
        if (memo[i] != -1) return memo[i];
        if (i == 0) memo[i] = nums[0];
        else if (i == 1) memo[i] = Math.max(nums[0], nums[1]);
        else memo[i] = Math.max(nums[i] + rob_top_down_memo(nums, i-2, memo), rob_top_down_memo(nums, i-1, memo));
        return memo[i];
    }

    private int rob_iterative_bottom_up_dp(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[n];
        // dp[i] = maximum amount of money we can rob in a list of houses [0..i];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // can improve space complexity, because dp[i] depends only on dp[i-1] and dp[i-2];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
        }
        return dp[n-1];
    }
}
