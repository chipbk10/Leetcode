package dp.distinct.way;

public class Problem688_KnightProbabilityInChessboard {

    public double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[N][N][K+1];
        int[][] dirs = new int[][] {{-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, {1,-2}, {-1,-2}, {-2,-1}};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j][0] = 1;
            }
        }

        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int[] d : dirs) {
                        if (isValid(N, i, j, d[0], d[1])) {
                            dp[i][j][k] += 0.125 * dp[i-d[0]][j-d[1]][k-1];
                        }
                    }
                }
            }
        }
        return dp[r][c][K];
    }

    private boolean isValid(int N, int i, int j, int x, int y) {
        if (i-x < 0 || i-x >= N) return false;
        if (j-y < 0 || j-y >= N) return false;
        return true;
    }

}
