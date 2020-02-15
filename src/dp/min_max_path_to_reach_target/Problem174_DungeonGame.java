package dp.min_max_path_to_reach_target;

public class Problem174_DungeonGame {

    public static void run() {
        int[][] A = {{-2,-3,3},{-5,-10,1},{10,30,-5}};
        Problem174_DungeonGame solution = new Problem174_DungeonGame();
        int res = solution.calculateMinimumHP(A);
        System.out.println(res);
    }

    public int calculateMinimumHP(int[][] dungeon) {
        return going_backward(dungeon);
    }

    public int going_backward(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;

        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] health = new int[m][n];

        health[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);

        for (int i = m - 2; i >= 0; i--) {
            health[i][n - 1] = Math.max(health[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }

        for (int j = n - 2; j >= 0; j--) {
            health[m - 1][j] = Math.max(health[m - 1][j + 1] - dungeon[m - 1][j], 1);
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int down = Math.max(health[i + 1][j] - dungeon[i][j], 1);
                int right = Math.max(health[i][j + 1] - dungeon[i][j], 1);
                health[i][j] = Math.min(right, down);
            }
        }

        return health[0][0];
    }

    // not working
    public int going_forward(int[][] A) {
        int M = A.length, N = A[0].length;
        int[][] dp = new int[M][N];
        A[0][0] -= 1;
        dp[0][0] = A[0][0];

        for (int i = 1; i < M; i++) {
            A[i][0] += A[i-1][0];
            dp[i][0] = Math.min(dp[i-1][0], A[i][0]);
        }

        for (int j = 1; j < N; j++) {
            A[0][j] += A[0][j-1];
            dp[0][j] = Math.min(dp[0][j-1], A[0][j]);
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (dp[i-1][j] == dp[i][j-1]) {
                    A[i][j] += Math.max(A[i-1][j], A[i][j-1]);
                    dp[i][j] = Math.min(dp[i-1][j], A[i][j]);
                }
                if (dp[i-1][j] > dp[i][j-1]) {
                    A[i][j] += A[i-1][j];
                    dp[i][j] = Math.min(dp[i-1][j], A[i][j]);
                }
                else {
                    A[i][j] += A[i][j-1];
                    dp[i][j] = Math.min(dp[i][j-1], A[i][j]);
                }
            }
        }

        int res = dp[N-1][N-1];
        return (res >= 1) ? res : -res;
    }

}
