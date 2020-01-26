package dp.string;

public class Problem115_DistinctSubsequences {

    public int numDistinct(String s, String t) {
        return dp_iterative_improve_space(s, t);
    }

    private int dp_iterative(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) dp[i][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) != t.charAt(j)) {
                    // the occurrence of s(i) is not helpful to solve entire t(0,j)
                    // so, we will base on the previous result to determine
                    dp[i+1][j+1] = dp[i][j+1];
                }
                else {
                    // now we have a choice, either take s(i) or not

                    // if we do not take s(i), so we will base on the previous result to solve entire t(0,j)
                    dp[i+1][j+1] +=  dp[i][j+1];

                    // if we take s(i), so the t(j) has been solved
                    // we will base on the previous result to solve entire t(0,j-1)
                    dp[i+1][j+1] += dp[i][j];
                }
            }
        }

        return dp[m][n];
    }

    private int dp_iterative_improve_space(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[n+1];

        for (int i = 0; i < m; i++) {
            dp[0] = 1;
            int prev = dp[0];

            for (int j = 0; j < n; j++) {
                int tmp = dp[j+1];
                if (s.charAt(i) == t.charAt(j)) {
                    dp[j+1] += prev;
                }
                prev = tmp;
            }
        }

        return dp[n];
    }
}
