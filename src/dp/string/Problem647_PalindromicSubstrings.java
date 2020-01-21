package dp.string;

public class Problem647_PalindromicSubstrings {

    public int countSubstrings(String s) {
        return dp_solution(s);
    }

    private int dp_solution(String s) {
        int count = 0, n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int d = 0; d < n; d++) {
            for (int i = 0; i+d < n; i++) {
                int j = i + d;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = (i+1 >= j-1) ? true : dp[i+1][j-1];
                    if (dp[i][j]) count++;
                }
            }
        }
        return count;
    }

    private int expand_around_center2(String s) {
        int n = s.length(), res = 0;

        for (int i = 0; i < 2*n-1; i++) {
            int left = i / 2;
            int right = left + (i%2);
            for (int d = 0; left -d >= 0 && right + d < n; d++) {
                if (s.charAt(left - d) != s.charAt(right + d)) break;
                res++;
            }
        }

        return res;
    }

    private int expand_around_center1(String s) {
        int n = s.length(), res = 0;

        for (int i = 0; i < n; i++) {

            for (int d = 0; i-d >= 0 && i+d < n; d++) {
                if (s.charAt(i-d) != s.charAt(i+d)) break;
                res++;
            }

            for (int d = 0; i-d >= 0 && i+1+d < n; d++) {
                if (s.charAt(i-d) != s.charAt(i+1+d)) break;
                res++;
            }
        }

        return res;
    }
}
