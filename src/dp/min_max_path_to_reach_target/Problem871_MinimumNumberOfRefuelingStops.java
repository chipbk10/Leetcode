package dp.min_max_path_to_reach_target;

public class Problem871_MinimumNumberOfRefuelingStops {

    public static void run() {
        Problem871_MinimumNumberOfRefuelingStops solution = new Problem871_MinimumNumberOfRefuelingStops();
//        int target = 100, startFuel = 1, stations[][] = {{10,100}};
        int target = 100, startFuel = 10, stations[][] = {{10,60},{20,30},{30,30},{60,40}};
        int res = solution.minRefuelStops(target, startFuel, stations);
        System.out.println(res);
    }

    int res = Integer.MAX_VALUE;

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int N = stations.length;
//        dfs(target, startFuel, stations, 0, 0);
//        res = dfs_simplify(target, startFuel, stations, 0);
//        res = dfs_memo(target, startFuel, stations, 0, new int[N+1][target+1]);
//        res = bottom_up(target, startFuel, stations);
        res = bottom_up_improve_space(target, startFuel, stations);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void dfs(int target, int canReach, int[][] stations, int i, int num) {
        if (num >= res) return;

        if (canReach >= target) {
            res = num;
            return;
        }

        if (i >= stations.length) {
            return;
        }

        if (canReach < stations[i][0]) {
            return;
        }

        dfs(target, canReach, stations, i+1, num);
        dfs(target, canReach+stations[i][1], stations, i+1, num+1);
    }

    // TLE
    private int dfs_simplify(int target, int canReach, int[][] stations, int i) {
        if (canReach >= target) return 0;
        if (i >= stations.length) return -1;
        if (canReach < stations[i][0]) return -1;

        int no_select = dfs_simplify(target, canReach, stations, i+1);
        int select = dfs_simplify(target, canReach+stations[i][1], stations, i+1);
        if (select == -1) return no_select;
        if (no_select == -1) return select+1;
        return Math.min(no_select, select+1);
    }

    // MLE
    private int dfs_memo(int target, int canReach, int[][] stations, int i, int[][] dp) {
        if (canReach >= target) return 0;
        if (i >= stations.length) return -1;
        if (canReach < stations[i][0]) return -1;
        if (dp[i][canReach] != 0) return dp[i][canReach];

        int no_select = dfs_memo(target, canReach, stations, i+1, dp);
        int select = dfs_memo(target, canReach+stations[i][1], stations, i+1, dp);

        if (select == -1) dp[i][canReach] = no_select;
        else if (no_select == -1) dp[i][canReach] = select+1;
        else dp[i][canReach] = Math.min(no_select, select+1);

        return dp[i][canReach];
    }

    private int bottom_up(int target, int startFuel, int[][] stations) {

        if (startFuel >= target) return 0;

        int N = stations.length;

        // MLE
        // dp[i][t] - the fewest number of refueling times in the first i stations to reach the distance t
        // int dp[][] = new int[N+1][target+1];

        // dp[i][j] - the furthest distance can reach if we use j refueling times in the first i stations (j <= i)
        int dp[][] = new int[N+1][N+1];

        // dp[i][j] = dp[i-1][j]
        // dp[i][j] = dp[i-1][j-1] + stations[i-1][1];

        for (int i = 0; i <= N; i++) {
            dp[i][0] = startFuel;
        }


        // S0000
        // Sx
        // Sxx
        // Sxxx
        // Sxxxx

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = dp[i-1][j];
                if (dp[i-1][j-1] >= stations[i-1][0]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + stations[i-1][1]);
                }
                if (dp[i][j] >= target) {
                    ans = Math.min(ans, j);
                }
            }
        }

        return ans;
    }

    private int bottom_up_improve_space(int target, int startFuel, int[][] stations) {

        if (startFuel >= target) return 0;

        int N = stations.length;
        int dp[] = new int[N+1];
        dp[0] = startFuel;

        // S0000
        // Sx
        // Sxx
        // Sxxx
        // Sxxxx

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int prev = dp[0];
            for (int j = 1; j <= i; j++) {
                int cur = dp[j];
                if (dp[j-1] >= stations[i-1][0]) {
                    dp[j] = Math.max(cur, prev + stations[i-1][1]);
                }
                prev = cur;
                if (dp[j] >= target) {
                    ans = Math.min(ans, j);
                }
            }
        }

        return ans;
    }

}
