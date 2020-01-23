package dp.string;

/**
 * Given a string S, find the number of different non-empty palindromic subsequences in S,
 * and return that number modulo 10^9 + 7.
 *
 * A subsequence of a string S is obtained by deleting 0 or more characters from S.
 *
 * A sequence is palindromic if it is equal to the sequence reversed.
 *
 * Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.
 */
public class Problem730_CountDifferentPalindromicSubsequences {

    // http://bit.ly/30QDxuf
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        int[] rightNext = rightNext(s, new int[] {n, n, n, n});
        int[] leftNext = leftNext(s, new int[] {-1, -1, -1, -1});
        int[][] dp = new int[n][n];
        for (int d = 0; d < n; d++) {
            for (int i = 0, j = i+d; j < n; i++, j++) {
                if (i == j) dp[i][j] = 1;
                else if (s.charAt(i) != s.charAt(j)) dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1];
                else {
                    int r = rightNext[i], l = leftNext[j];
                    int extra = (r < l) ? - dp[r+1][l-1] : (r == l) ? 1 : 2;
                    dp[i][j] = 2*dp[i+1][j-1] + extra;
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + 1000000007 : dp[i][j] % 1000000007;
            }
        }
        return dp[0][n - 1];
    }

    private int[] leftNext(String s, int[] rec) {
        int n = s.length(), res[] = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = rec[s.charAt(i) - 'a'];
            rec[s.charAt(i) - 'a'] = i;
        }
        return res;
    }

    private int[] rightNext(String s, int[] rec) {
        int n = s.length(), res[] = new int[n];
        for (int i = n-1; i >= 0; i--) {
            res[i] = rec[s.charAt(i) - 'a'];
            rec[s.charAt(i) - 'a'] = i;
        }
        return res;
    }

}
