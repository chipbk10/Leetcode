package dp.string;

public class Problem72_EditDistance {

    public int minDistance(String s1, String s2) {
        return dp_iterative(s1, s2);
    }

    private int dp_iterative(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1]);
                }
            }
        }

        return dp[m][n];
    }

    public int dp_iterative_space_improve(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[] dp = new int[n+1];

        for (int i = 0; i <= n; i++) dp[i] = i;

        for (int i = 1; i <= m; i++) {

            int prev = dp[0];
            dp[0] = i;

            for (int j = 1; j <= n; j++) {
                int tmp = dp[j];

                if (s1.charAt(i-1) == s2.charAt(j-1))
                    dp[j] = prev;
                else
                    dp[j] = 1 + Math.min(Math.min(tmp, prev), dp[j-1]);
                prev = tmp;
            }
        }

        return dp[n];
    }
}
