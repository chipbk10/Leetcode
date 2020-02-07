package dp.distinct_way;

public class Problem377_Combination_Sum_IV {

    public int combinationSum4(int[] A, int N) {
        return dp_solution_improve(A, N);
    }

    private int dp_solution_improve(int[] A, int N) {
        int[] dp = new int[N+1];
        dp[0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < A.length; j++) {
                if (i - A[j] >= 0) {
                    dp[i] += dp[i-A[j]];
                }
            }
        }

        return dp[N];
    }

    private int dp_solution(int[] A, int N) {
        // dp[i][j] the number of possible combinations that add up to i, and the last element to add up is A[j]
        int[][] dp = new int[N+1][A.length];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < A.length; j++) {
                if (i > A[j]) {
                    for (int k = 0; k < A.length; k++) {
                        dp[i][j] += dp[i-A[j]][k];
                    }
                }
                else if (i == A[j]) {
                    dp[i][j] = 1;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < A.length; i++) {
            res += dp[N][i];
        }

        return res;
    }
}
