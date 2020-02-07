package dp.min_max_path_to_reach_target;

public class ReadMe {

    /**
     * Statement:
     *  Given a target find minimum (maximum) cost / path / sum to reach the target.
     *
     * Approach:
     *  Choose minimum (maximum) path among all possible paths before the current state,
     *  then add value for the current state.
     *
     *  routes[i] = min(routes[i-1], routes[i-2], ... , routes[i-k]) + cost[i]
     *
     * Generate optimal solutions for all values in the target and return the value for the target.
     *
     *  for (int i = 1; i <= target; ++i) {
     *    for (int j = 0; j < ways.size(); ++j) {
     *        if (ways[j] <= i) {
     *            dp[i] = min(dp[i], dp[i - ways[j]]) + cost / path / sum;
     *        }
     *    }
     *  }
     *
     *  return dp[target]
     *
     * Problems:
     *
     *  746. Min Cost Climbing Stairs Easy
     *  64. Minimum Path Sum Medium
     *  322. Coin Change Medium
     *  931. Minimum Falling Path Sum Medium
     *  983. Minimum Cost For Tickets Medium
     *  650. 2 Keys Keyboard Medium
     *  279. Perfect Squares Medium
     *  1049. Last Stone Weight II Medium
     *  120. Triangle Medium
     *  474. Ones and Zeroes Medium
     *  221. Maximal Square Medium
     *  1240. Tiling a Rectangle with the Fewest Squares Hard
     *  174. Dungeon Game Hard
     *  871. Minimum Number of Refueling Stops Hard
     *
     */

}
