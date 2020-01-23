package dp.string;

public class Problem516_LongestPalindromicSubsequence {

    public int longestPalindromeSubseq(String s) {
        int N = s.length(), res = 0;
        int[][] dp = new int[N][N];

        for (int d = 0; d < N; d++) {
            for (int i = 0; i < N-d; i++) {
                int j = i+d;
                if (s.charAt(i) == s.charAt(j)) {
                    if (i == j) dp[i][j] = 1;
                    else dp[i][j] = dp[i+1][j-1] + 2;
                }
                else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
                res = Math.max(res, dp[i][j]);
            }
        }

        return res;
    }
}
