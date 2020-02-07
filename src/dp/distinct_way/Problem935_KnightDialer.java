package dp.distinct_way;

import java.util.Arrays;

public class Problem935_KnightDialer {

    public int knightDialer(int N) {
        int[][] dp = new int[N][10];
        int[][] reach = new int[][]{
                { 4, 6 }, { 6, 8 }, { 7, 9 }, { 4, 8 }, { 0, 3, 9 },
                { }, { 0, 1, 7 }, { 2, 6 }, { 1, 3 }, { 2, 4 }
        };
        int mod = (int)1e9 + 7;
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                int[] prev = reach[j];
                for (int key : prev) {
                    dp[i][j] += dp[i-1][key];
                    dp[i][j] %= mod;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 10; i++) {
            res += dp[N - 1][i];
            res %= mod;
        }
        return res;
    }
}
