package dp.distinct_way;

public class ReadMe {

    /**
     * Statement:
     *  Given a target find a number of distinct ways to reach the target
     *
     * Approach:
     *  Sum all possible ways to reach the current state.
     *  routes[i] = routes[i-1] + routes[i-2], ... , + routes[i-k]
     *
     *  Generate sum for all values from 0 to the target and finally return the value for the target
     *
     *  for (int i = 1; i <= target; ++i) {
     *    for (int j = 0; j < ways.size(); ++j) {
     *        if (ways[j] <= i) {
     *            dp[i] += dp[i - ways[j]];
     *        }
     *    }
     *  }
     *
     *  Be careful when we swap 2 loops, and handle when the element can be repeated.
     *  The sum-up should be written.
     *
     * Problems:
     *  70. Climbing Stairs easy
     *  62. Unique Paths Medium
     *  1155. Number of Dice Rolls With Target Sum Medium
     *  688. Knight Probability in Chessboard Medium
     *  576. Out of boundary paths
     *  494. Target Sum Medium
     *  377. Combination Sum IV Medium
     *  935. Knight Dialer Medium
     X  1223. Dice Roll Simulation Medium
     *  416. Partition Equal Subset Sum Medium
     *  518. Coin Change 2
     X  808. Soup Servings Medium
     *  790. Domino and Tromino Tiling Medium
     X  801. Minimum Swaps To Make Sequences Increasing
     *  673. Number of Longest Increasing Subsequence Medium
     *  63. Unique Paths II Medium
     *  576. Out of Boundary Paths Medium
     *  1269. Number of Ways to Stay in the Same Place After Some Steps Hard
     *  1220. Count Vowels Permutation Hard
     */
}
