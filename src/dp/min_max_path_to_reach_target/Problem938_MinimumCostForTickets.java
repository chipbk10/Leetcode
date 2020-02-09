package dp.min_max_path_to_reach_target;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-cost-for-tickets/discuss/504403/DP-it's-not-easy-as-you-thought
 */
public class Problem938_MinimumCostForTickets {

    public static void run() {
        Problem938_MinimumCostForTickets solution = new Problem938_MinimumCostForTickets();
        int[] days = {1,4,6,7,8,20}, costs = {2,7,15};
        int res = solution.mincostTickets(days, costs);
        System.out.println(res);
    }

    public int mincostTickets(int[] days, int[] costs) {
        return dp_bottom_up(days, costs);
//        return dfs_simplify_memo(days, costs, 0, new int[days.length]);
//        return dfs_simplify(days, costs, 0);
//        return dfs(days, costs, 0, 0);
    }

    private int dfs(int[] days, int[] costs, int i, int finalValidDay) {
        if (i == days.length) return 0;

        // pay nothing, still a valid pass
        if (days[i] <= finalValidDay) return dfs(days, costs, i+1, finalValidDay);

        // buy a day-pass, or week-pass, or month-pass
        int buyDayPass = costs[0] + dfs(days, costs, i+1, days[i]);
        int buyWeekPass = costs[1] + dfs(days, costs, i+1, days[i]+6);
        int buyMonthPass = costs[2] + dfs(days, costs, i+1, days[i]+29);

        // choose the minimum cost
        return Math.min(buyDayPass, Math.min(buyWeekPass, buyMonthPass));
    }

    private int dfs_simplify(int[] days, int[] costs, int i) {
        if (i == days.length) return 0;

        // buy a day-pass, or week-pass, or month-pass
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < costs.length; j++) {
            int nextIndex = nextDayIndex(days, i, j);
            res = Math.min(res, costs[j] + dfs_simplify(days, costs, nextIndex) );
        }

        // choose the minimum cost
        return res;
    }

    private int nextDayIndex(int[] days, int i, int j) {
        int next = nextDay(days, i, j);
        while (i < days.length && days[i] < next) i++;
        return i;
    }

    private int nextDay(int[] days, int i, int j) {
        if (j == 0) return days[i]+1;
        if (j == 1) return days[i]+7;
        return days[i]+30;
    }

    private int dfs_simplify_memo(int[] days, int[] costs, int i, int[] memo) {
        if (i == days.length) return 0;
        if (memo[i] != 0) return memo[i];

        // buy a day-pass, or week-pass, or month-pass
        memo[i] = Integer.MAX_VALUE;
        for (int j = 0; j < costs.length; j++) {
            int nextIndex = nextDayIndex(days, i, j);
            memo[i] = Math.min(memo[i], costs[j] + dfs_simplify_memo(days, costs, nextIndex, memo) );
        }

        // choose the minimum cost
        return memo[i];
    }

    private int dp_bottom_up(int[] days, int[] costs) {
        Set<Integer> set = new HashSet<>();
        for (int day : days) set.add(day);

        int lastDay = days[days.length-1], dp[] = new int[lastDay+1];
        for (int i = 1; i <= lastDay; i++) {
            if (!set.contains(i)) {
                dp[i] = dp[i-1];
            }
            else {
                dp[i] = dp[i-1]+costs[0];

                int j = (i >= 7) ? i-7 : 0;
                dp[i] = Math.min(dp[i], dp[j] + costs[1]);

                j = (i >= 30) ? i-30 : 0;
                dp[i] = Math.min(dp[i], dp[j] + costs[2]);
            }
        }

        return dp[lastDay];
    }
}
