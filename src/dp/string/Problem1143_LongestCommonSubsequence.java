package dp.string;

public class Problem1143_LongestCommonSubsequence {

    public int longestCommonSubsequence(String s1, String s2) {
        return solution_dp2(s1, s2);
    }

    private int solution_dp1(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                }
                else {
                    dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }
        return dp[m][n];
    }

    private int solution_dp2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (n > m) return solution_dp2(s2, s1);

        int dp[] = new int[n+1];
        for (int i = 0; i < m; i++) {
            int prev = dp[0];

            for (int j = 0; j < n; j++) {

                int tmp = dp[j+1];

                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[j+1] = prev + 1;
                }
                else {
                    dp[j+1] = Math.max(dp[j+1], dp[j]);
                }

                prev = tmp;
            }
        }
        return dp[n];
    }

}
