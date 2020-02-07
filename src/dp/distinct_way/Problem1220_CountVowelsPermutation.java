package dp.distinct_way;

import java.util.Arrays;

public class Problem1220_CountVowelsPermutation {

    public int countVowelPermutation(int n) {
        int[][] dp = new int[n+1][5];
        int mod = (int)1e9 + 7;
        int a = 0, e = 1, i = 2, o = 3, u = 4;
        Arrays.fill(dp[1], 1);

        for (int j = 2; j <= n; j++) {
            dp[j][a] += (((dp[j-1][e] + dp[j-1][i]) % mod) + dp[j-1][u]) % mod;
            dp[j][e] += (dp[j-1][a] + dp[j-1][i]) % mod;
            dp[j][i] += (dp[j-1][e] + dp[j-1][o]) % mod;
            dp[j][o] += dp[j-1][i] % mod;
            dp[j][u] += (dp[j-1][i] + dp[j-1][o]) % mod;
        }

        int res = 0;
        for (int j = 0; j < 5; j++) {
            res += dp[n][j];
            res %= mod;
        }

        return res;
    }
}
