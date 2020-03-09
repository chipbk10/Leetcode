package dp;

import java.util.Arrays;

public class Problem1340_JumpGameV {
    public int maxJumps(int[] A, int d) {
        int n = A.length, dp[] = new int[n];
        Arrays.fill(dp, -1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, jump(A, d, i, dp));
        }
        return res;
    }

    public int jump(int[] A, int d, int index, int[] dp) {
        if (dp[index] != -1) return dp[index];

        dp[index] = 1;
        int r = index+1;
        while (r < A.length && A[r] < A[index] && r-index <= d) {
            dp[index] = Math.max(dp[index], jump(A, d, r, dp)+1);
            r++;
        }

        int l = index-1;
        while (l >= 0 && A[l] < A[index] && index-l <= d) {
            dp[index] = Math.max(dp[index], jump(A, d, l, dp)+1);
            l--;
        }

        return dp[index];
    }
}
