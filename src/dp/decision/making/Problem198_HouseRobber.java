package dp.decision.making;

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
        if (nums.length == 0) return 0;
        int prev1 = 0;
        int prev2 = 0;
        for (int num : nums) {
            int tmp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = tmp;
        }
        return prev1;
    }
}
