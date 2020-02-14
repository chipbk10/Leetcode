package dp.min_max_path_to_reach_target;

public class Problem474_Ones_And_Zeros {

    public int findMaxForm(String[] S, int M, int N) {
//        return dp_solution(S, M, N);
        return dp_solution_improve_space(S, M, N);
    }

    private int dp_solution_improve_space(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for(String str : strs){
            int ones = 0;
            int zeros = 0;
            for(char c : str.toCharArray()){
                if(c == '1') ones++;
                else zeros++;
            }
            for(int i = m; i >= zeros; i--){
                for(int j = n; j >= ones; j--){
                    if(ones <= j && zeros <= i)
                        dp[i][j] = Math.max(dp[i][j],dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int dp_solution(String[] S, int M, int N) {
        int dp[][][] = new int[S.length][M+1][N+1];
        int[][] A = new int[S.length][2];
        for (int i = 0; i < S.length; i++) {
            A[i] = new int[2];
            for (char c : S[i].toCharArray()) {
                if (c == '0') A[i][0]++;
                else A[i][1]++;
            }
        }

        for (int i = 0; i < S.length; i++) {
            for (int m = 0; m <= M; m++) {
                for (int n = 0; n <= N; n++) {
                    boolean b = (m >= A[i][0] && n >= A[i][1]);
                    if (i == 0) {
                        if (b) dp[i][m][n] = 1;
                    }
                    else {
                        dp[i][m][n] = dp[i-1][m][n];
                        if (b) {
                            dp[i][m][n] = Math.max(dp[i][m][n], 1 + dp[i-1][m-A[i][0]][n-A[i][1]]);
                        }
                    }
                }
            }
        }

        return dp[S.length-1][M][N];
    }

}
